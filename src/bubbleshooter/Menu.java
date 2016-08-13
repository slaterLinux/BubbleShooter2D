package bubbleshooter;

import java.awt.*;

/**
 * Created by slater on 12.08.16.
 */
public class Menu {

    //Fields
    private int buttonWidth;
    private int buttonHeight;
    private Color color1;
    private String s;
    private int transp = 0;

    //Constructor

    public Menu() {
        buttonWidth = 120;
        buttonHeight = 60;

        color1 = Color.WHITE;
        s = "Play!";

    }


    //Functions

    public void draw(Graphics2D g) {
        g.setColor(color1);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanel.WIDTH/ 2 - buttonWidth/2,
                GamePanel.HEIGHT/2 - buttonHeight/2, buttonWidth, buttonHeight);
        g.setColor(new Color(255, 255,255,transp));
        g.fillRect(GamePanel.WIDTH/ 2 - buttonWidth/2,
                GamePanel.HEIGHT/2 - buttonHeight/2, buttonWidth, buttonHeight);
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.orange);
        g.setFont(new Font("Consolas", Font.BOLD, 40));
        long length = (int) g.getFontMetrics().getStringBounds(s, g).getHeight();
        g.drawString(s, (int) (GamePanel.WIDTH / 2 - length * 1.2),
                (int) (GamePanel.HEIGHT / 2 + buttonHeight / 4));
    }

    public void update(){
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2 &&
                GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2 &&
                GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2 &&
                GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
            transp = 60;
            if (GamePanel.leftMouse) {
                GamePanel.state = GamePanel.STATES.PLAY;
            }
        } else {
            transp = 0;
        }
    }
}
