package bubbleshooter;

import javax.swing.*;


public class GameStart {
    public static void main(String[] args) {
        GamePanel panel = new GamePanel();
        JFrame startFrame = new JFrame("Bubble Shooter 2D");
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
        panel.start();

    }
}
