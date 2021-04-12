package FlappyBird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BirdImage {

    private BufferedImage img=null;
    private static int bird_dia=36;// diametrul pasarii/eroului

    // coordonatele unde va fi plasat eroul la inceptul jocului

    public static int x=(GamePanel.WIDTH/2-bird_dia/2);
    public static int y=GamePanel.HEIGHT/2;

    public static int speed=2;//viteza cu care se va deplasa eroul
    private int acce=1;//acceleratia

    public BirdImage(){
        LoadImage();
    }

    // in functia LoadImage vom incarca imaginea eroului

    private void LoadImage() {
        try{
            img= ImageIO.read(new File("/Users/User/FlappyBirdV4/Resources/jump.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //in functia drawBird vom afisa pe ecranul jocului eroul

    public void drawBird(Graphics g){
        g.drawImage(img, x, y, null);
    }

    //In functia birdMovement vom seta felul in care misca eroul
    //tot in aceasta functie verificam daca eroul este in cazul favorabil(adica y intre 0 si latimea imaginii, adica va fi inca in aer)
    //in caz contrar, va aparea casuta de dialog, cu optiunea YES or NO
    //daca optiunea este YES, jocul va porni din nou, eroul si peretii vor fi readusi in pozitii initale
    // daca optiunea este NO, jocul se va inchide

    public void birdMovement(){
        if(y>=0&&y<=GamePanel.HEIGHT){
            speed+=acce;
            y+=speed;
        }else{
            boolean option=GamePanel.popUpMessage();

            if(option)
            {
                try{
                    Thread.sleep(10);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                reset();
            }
            else{
                JFrame frame=Main.getWindow();
                frame.dispose();
                Main.timer.stop();
            }
        }
    }
    public void goUpwards(){
        speed=-16;
    }

    // functia de reset care ne va folosi in BirdMovement in cazul in care s-a selectat optiunea NO

    public static void reset() {
        speed=2;
        y=GamePanel.HEIGHT/2;
        GamePanel.GameOver=true;
    }

    // aceasta functie ne va folosi pt detectarea coliziunilor cu peretii
    // functia se va apela in wallMovement

    public static Rectangle getBirdRect(){
        Rectangle birdRect=new Rectangle(x,y,bird_dia,30);
        return birdRect;
    }

}