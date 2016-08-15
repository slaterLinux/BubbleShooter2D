package bubbleshooter;

import java.awt.*;

class Enemy {

    //Fields
    private double x;
    private double y;
    private int r;
    private double dx;
    private double dy;
    private double health;


    private Color color;

    //Constructor
    Enemy(int type, int rank) {
        switch (type) {
            case (1):
                color = Color.GREEN;
                double speed;
                switch (rank) {
                    case (1):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 7;
                        speed = 2;
                        health = 2;
                        double angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        break;
                    case (2):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 10;
                        speed = 3;
                        health = 2;
                        angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        break;
                } break;
            case (2):
                color = Color.RED;
                switch (rank) {
                    case (1):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 12;
                        speed = 4;
                        health = 4;
                        double angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        break;
                    case (2):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 12;
                        speed = 5;
                        health = 4;
                        angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        break;
                }

        }
    }

    //Functions

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    int getR() {
        return r;
    }

    void hit() {
        health--;
    }

    boolean remove() {
        return health <= 0;
    }

    void update() {
        x += dx;
        y += dy;
        if (x < 0 && dx < 0) dx = -dx;
        if (x > GamePanel.WIDTH && dx > 0) dx = -dx;
        if (y < 0 && dy < 0) dy = -dy;
        if (y > GamePanel.WIDTH && dy > 0) dy = -dy;
    }

    void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(r / 3));
        g.setColor(color.darker());
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }
}
