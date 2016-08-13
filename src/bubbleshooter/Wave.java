package bubbleshooter;

import java.awt.*;

/**
 * Created by slater on 11.08.16.
 */
public class Wave {

    //Fields
    private int waveNumber;

    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;

    private int waveMultiplier;

    private String waveText;

    //Constructor


    public Wave() {
        waveNumber = 1;
        waveMultiplier = 5;
        waveTimer = 0;
        waveDelay = 5000;
        waveTimerDiff = 0;
        waveText = " В О Л Н А -";
    }

    //Functions
    public void update(){
        if (GamePanel.enemies.size() == 0 && waveTimer == 0) {
            waveTimer = System.nanoTime();
        }
        if (waveTimer > 0) {
            waveTimerDiff += (System.nanoTime() - waveTimer) / 1_000_000;
            waveTimer = System.nanoTime();
        }
        if (waveTimerDiff > waveDelay) {
            createEnemies();
            waveTimer = 0;
            waveTimerDiff = 0;
        }
    }

    public boolean showWave(){
        if (waveTimer != 0) {
            return true;
        } else return false;
    }

    public void createEnemies(){
        int enemyCount = waveNumber * waveMultiplier;
        if (waveNumber <= 2) {
            while (enemyCount > 0) {
                int type = 1;
                int rank = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }

        if (waveNumber >= 3 && waveNumber <= 5) {
            while (enemyCount > 0) {
                int type = 1;
                int rank = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                if (enemyCount %2 ==0) {
                    type = 1;
                    rank = 2;
                    GamePanel.enemies.add(new Enemy(type, rank));
                }
                enemyCount -= type * rank;
            }
        }

        if (waveNumber >= 6 && waveNumber <= 8) {
            while (enemyCount > 0) {
                int type = 1;
                int rank = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                if (enemyCount %2 ==0) {
                    type = 1;
                    rank = 2;
                    GamePanel.enemies.add(new Enemy(type, rank));
                }
                if (enemyCount %3 ==0) {
                    type = 2;
                    rank = 1;
                    GamePanel.enemies.add(new Enemy(type, rank));
                }
                if (enemyCount %4 ==0) {
                    type = 2;
                    rank = 2;
                    GamePanel.enemies.add(new Enemy(type, rank));
                }
                enemyCount -= type * rank;

            }
        }

        if (waveNumber >= 9 && waveNumber <= 11) {
            while (enemyCount > 0) {
                int type = 1;
                int rank = 2;
                GamePanel.enemies.add(new Enemy(type, rank));
                if (enemyCount %2 ==0) {
                    type = 2;
                    rank = 1;
                    GamePanel.enemies.add(new Enemy(type, rank));
                }
                if (enemyCount %3 ==0) {
                    type = 2;
                    rank = 2;
                    GamePanel.enemies.add(new Enemy(type, rank));
                }
                enemyCount -= type * rank;
            }
        }
        waveNumber++;


    }



    public void draw(Graphics2D g){
        double divider = waveDelay / 180;
        double alpha = waveTimerDiff / divider;
        alpha = 255 * Math.sin(Math.toRadians(alpha));
        if (alpha < 0) alpha = 0;
        if (alpha > 255) alpha = 255;
        g.setFont(new Font("consolas", Font.PLAIN, 20));
        g.setColor(new Color(255, 255, 255, (int)alpha));
        String s = " -  " + waveNumber + "-ая" + waveText;
        long length = (long)(g.getFontMetrics().getStringBounds(s, g).getWidth());
        g.drawString(s, GamePanel.WIDTH / 2 - (int) (length / 2), GamePanel.HEIGHT / 2);
    }
}
