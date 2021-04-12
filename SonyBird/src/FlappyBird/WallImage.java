package FlappyBird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

// in aceasta clasa vom seta imaginea peretelui, cum se va misca, coloziunea cu eroul

public class WallImage {

    private Random r = new Random();
    public int X;
    public int Y = r.nextInt(GamePanel.HEIGHT - 400) + 200;// max 600, min 200(inaltimea peretelui va fi setata random)
    private int width_Wall = 45;// latimea peretelui
    private int height = GamePanel.HEIGHT - Y;// inaltimea peretelui
    private int gap = 200;// distanta golului dintre pereti prin care sare eroul

    public static int speed = -6;// viteza cu care se misca peretii

    private BufferedImage img = null;

    public WallImage(int X) {
        this.X = X;

        LoadImage();
    }

    //incarcam imaginea peretelui

    private void LoadImage() {
        try {
            img = ImageIO.read(new File("/Users/User/FlappyBirdV4/Resources/wall.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //setam pozitia initiala a peretelui

    public void drawWall(Graphics g) {
        g.drawImage(img, X, Y, null);// peretele de jos
        g.drawImage(img, X, (-GamePanel.HEIGHT) + (Y - gap), null);// peretele de sus
    }

    // in metoda wallMovement este scris cum se misca peretele paralel cu axa X(X+=speed)
    // tot in aceasta metoda este descrisa si colizinea dintre pereti si erou(pasare)
    // in cazul coliziunii jocul va se va termina, iar pe ecran va aparea casuta de dialog

    public void wallMovement() {
        X += speed;
        if (X <= -width_Wall) {
            X = GamePanel.WIDTH;
            Y = r.nextInt(GamePanel.HEIGHT - 400) + 200;
            height = GamePanel.HEIGHT - Y;
        }
        Rectangle lowerRect = new Rectangle(X, Y, width_Wall, height);
        Rectangle upperRect = new Rectangle(X, 0, width_Wall, GamePanel.HEIGHT - (height + gap));

        if (lowerRect.intersects(BirdImage.getBirdRect()) || upperRect.intersects(BirdImage.getBirdRect())) {
            boolean option = GamePanel.popUpMessage();
            if (option == true) {

                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                BirdImage.reset();
                wall_Reset();
                GamePanel.speedReset();

            } else {
                JFrame frame=Main.getWindow();
                /// goleste resursele de memorie aferente contextului grafic curent
                frame.dispose();
                Main.timer.stop();
            }

        }
    }

    // functia de reset pt perete

    private void wall_Reset() {
        Y = r.nextInt(GamePanel.HEIGHT - 400) + 200;
        height = GamePanel.HEIGHT - Y;
        GamePanel.GameOver = true;
    }

}