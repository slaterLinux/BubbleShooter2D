package bubbleshooter;

import java.awt.*;


class Bullet {

    //Fields
    private double x;
    private double y;
    private final double bulletDX;
    private final double bulletDY;


    private final int r;
    private final Color color;

    //constructor

    Bullet() {
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        r = 2;
        color = Color.WHITE;
        double speed = 10;

        double distX = GamePanel.mouseX - x;
        double distY = GamePanel.mouseY - y;
        double dist = Math.sqrt((distX * distX) + (distY * distY));
        bulletDX = distX / dist * speed;
        bulletDY = distY / dist * speed;

    }

    //functions

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    int getR() {
        return r;
    }

    void update() {
        y += bulletDY;
        x += bulletDX;
    }

    boolean remove() {
        return y < 0 || y > GamePanel.HEIGHT ||
                x < 0 || x > GamePanel.WIDTH;
    }

    void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)x, (int)y, r, 2 * r);

    }
}
