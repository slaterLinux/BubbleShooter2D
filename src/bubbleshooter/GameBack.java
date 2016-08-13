package bubbleshooter;

import java.awt.*;

/**
 * Created by slater on 10.08.16.
 */
public class GameBack {

    //Field
    private Color color;


    //Constructor
    public GameBack() {
        color = Color.BLUE;
    }

    //FUnctions
    public void update(){

    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}
