package bubbleshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    //Field
    public final static int WIDTH = 600;
    public final static int HEIGHT = 600;

    public static int mouseX;
    public static int mouseY;

    private Thread thread = new Thread(this);

    private BufferedImage image;
    private Graphics2D g;

    private int fps;
    private double millisToFPS;
    private long timerFPS;
    private int sleepTime;

    public enum STATES{
        MENU,
        PLAY
    }

    public static STATES state = STATES.MENU;

    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    public static Menu menu;
    public static boolean leftMouse;


    //Constructor
    public GamePanel() {
        super();
        //задать размеры
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());

    }

    public void start() {
        thread.start();
    }

    //Functions
    @Override
    public void run() {

        fps = 30;
        millisToFPS = 1000 / fps;
        sleepTime = 0;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        wave = new Wave();
        menu = new Menu();
        leftMouse = false;

        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage buffered = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) buffered.getGraphics();
        g3.setColor(new Color(255, 255, 255));
        g3.drawOval(0, 0, 16, 16);
        g3.drawLine(8, 0, 8, 16);
        g3.drawLine(0, 8, 16, 8);
        Cursor cursor = kit.createCustomCursor(buffered, new Point(3, 3), "myCursor");
        g3.dispose();

        while (true) {

            this.setCursor(cursor);
            if (state.equals(STATES.MENU)) {
                background.update();
                background.draw(g);
                menu.update();
                menu.draw(g);
                gameDraw();
            }
            if (state.equals(STATES.PLAY)) {

                timerFPS = System.nanoTime();

                gameUpdate();
                gameRender();
                gameDraw();
            }

            timerFPS = (System.nanoTime() - timerFPS) / 1_000_000;
            if (millisToFPS > timerFPS) {
                sleepTime = (int) (millisToFPS - timerFPS);
            } else sleepTime = 1;
            try {
                thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;
        }
    }

    public void gameUpdate() {
        //Background update
        background.update();

        //player update
        player.update();

        //Bullets update
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if (remove) {
                bullets.remove(i);
                i--;
            }
        }

        //enemis update
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

        //bullets-enemies collide
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            double ex = enemy.getX();
            double ey = enemy.getY();
            for (int j = 0; j < bullets.size(); j++) {
                Bullet bullet = bullets.get(j);
                double bx = bullet.getX();
                double by = bullet.getY();
                double dx = ex - bx;
                double dy = ey - by;
                double distance = Math.sqrt(dx * dx + dy * dy);
                if ((int) distance <= enemy.getR() + bullet.getR()) {
                    enemy.hit();
                    bullets.remove(j);
                    j--;
                    boolean remove = enemy.remove();
                    if (remove) {
                        enemies.remove(i);
                        i--;
                    }
                    break;
                }
            }
        }

        //player-enemy collides
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            double ex = enemy.getX();
            double ey = enemy.getY();

            double px = player.getX();
            double py = player.getY();
            double pr = player.getR();
            double dx = ex - px;
            double dy = ey - py;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if ((int) dist <= enemy.getR() + player.getR()) {
                enemy.hit();
                player.hit();
                System.out.println(player.getHealth());
                boolean remove = enemy.remove();
                if (remove) {
                    enemies.remove(i);
                    i--;
                }
            }


        }
        wave.update();
    }



    public void gameRender() {
        //Background draw
        background.draw(g);

        //Player draw
        player.draw(g);

        //bullets draw
        for (Bullet bul : bullets) {
            bul.draw(g);
        }

        for (Enemy en :
                enemies) {
            en.draw(g);
        }

        //Wave draw
        if (wave.showWave()) {
            wave.draw(g);
        }
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}
