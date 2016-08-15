package bubbleshooter;

import java.awt.*;


class Player {

    //Fields
    private double x;
    private double y;
    private final int r;

    private double dx;
    private double dy;

    private final int speed;
    private int health;

    private final Color color1;
    private Color color2;

    static boolean up;
    static boolean down;
    static boolean left;
    static boolean right;
    static boolean isFiring;


    Player() {
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

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    int getR() {
        return r;
    }

    int getHealth() {
        return health;
    }

    //Functions
    void update() {
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

    void draw(Graphics2D g) {
        g.setColor(color1);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
        g.setColor(color1.darker());
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }

    void hit() {
        health--;
    }
}
