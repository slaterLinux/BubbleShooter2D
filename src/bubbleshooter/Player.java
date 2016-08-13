package bubbleshooter;

import java.awt.*;

/**
 * Created by slater on 10.08.16.
 */
public class Player {

    //Fields
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;

    private int speed;
    private int health;

    private Color color1;
    private Color color2;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean isFiring;


    public Player() {
        x = GamePanel.HEIGHT / 2;
        y = GamePanel.WIDTH / 2;

        r = 5;
        color1 = Color.WHITE;

        speed = 5;

        up = false;
        down = false;
        left = false;
        right = false;
        isFiring = false;
        dx = 0;
        dy = 0;
        health = 3;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getHealth() {
        return health;
    }

    //Functions
    public void update() {
        if (up && y > r) {
            dy = -speed;
        }
        if (down && y < GamePanel.HEIGHT - r) {
            dy = speed;
        }
        if (left && x > r) {
            dx = -speed;
        }
        if (right && x < GamePanel.WIDTH - r) {
            dx = speed;
        }
        if (up && left || up && right || down && left || down && right) {
            double angle = Math.toRadians(45);
            dy = dy * Math.sin(angle);
            dx = dx * Math.cos(angle);
        }
        y += dy;
        x += dx;
        dy = 0;
        dx = 0;

        if (isFiring) {
            GamePanel.bullets.add(new Bullet());
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color1);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
        g.setColor(color1.darker());
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }

    public void hit(){
        health--;
    }


}
