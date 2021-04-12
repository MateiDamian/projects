package FlappyBird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

//in aceasta clasa vom crea imaginea de inceput a jocului

public class MenuPanel extends JPanel {

    private static final long serialVersionUID=1L;



    private BufferedImage img=null;// imaginea este inital nula
    public boolean StartingPoint=false;// se va folosi pentru inceperea jocului propriu-zis
    // cat timp este False, pe ecran va aparea imaginea initala

    // cand vom da click oriunde in imagine, va incepe jocul
    public MenuPanel(){

        LoadImage();
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StartingPoint=true;
            }
        });
    }

    // in aceasta metoda este incarcata imaginea de inceput a jocului

    private void LoadImage() {

        try{
            img= ImageIO.read(new File("/Users/User/FlappyBirdV4/Resources/mockup.png"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    // metoda de desenare

    @Override
    public void paint(Graphics g){
        super.paint(g);

        g.drawImage(img, 0,0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
    }
}