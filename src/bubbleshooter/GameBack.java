package bubbleshooter;

import java.awt.*;


class GameBack {

    //Field
    private final Color color;


    //Constructor
    GameBack() {
        color = Color.BLUE;
    }

    //FUnctions
    void update() {

    }

    void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}
