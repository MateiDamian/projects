package FlappyBird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;


public class MenuPanel extends JPanel {

    private static final long serialVersionUID=1L;



    private BufferedImage img=null;
    public boolean StartingPoint=false;
    

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



    private void LoadImage() {

        try{
            img= ImageIO.read(new File("/Users/User/FlappyBirdV4/Resources/mockup.png"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    public void paint(Graphics g){
        super.paint(g);

        g.drawImage(img, 0,0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
    }
}
