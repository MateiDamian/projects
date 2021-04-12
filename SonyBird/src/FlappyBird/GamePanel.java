package FlappyBird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

// in aceasta clasa vom seta imaginea de jocului, tot aici vom si desena elementele si vom apela metodele pentru miscarea lor

public class GamePanel extends JPanel {

    private static final long serialVersionUID=1L;

    public static boolean GameOver=false;
    // scorul
    public static int score=0;
    //nivelul
    public static int level=1;

    // am setat latimea
    public static final int WIDTH=600;

    //am setat inaltimea
    public static final int HEIGHT=800;

    private int xCoor=0;
    private BufferedImage img;


    BirdImage bi= new BirdImage();

    WallImage wi = new WallImage(GamePanel.WIDTH);
    WallImage wii= new WallImage(GamePanel.WIDTH+(GamePanel.WIDTH/2));
    public GamePanel(){
        LoadImage();

        // de fiecare data cand va fi apasat clickul, eroul se va deplasa in sus

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                bi.goUpwards();
            }
        });

    }

    // In metoda LoadImage() se incarca imaginea de fundal(jungla)
    private void LoadImage() {

        try{
            img= ImageIO.read(new File("/Users/User/FlappyBirdV4/Resources/Jungle.png"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //In metoda paint vom incarca pe ecranul jocului toate componentele necesare(eroul, cei doi pereti)
    //Tot in metoda aceasta vom si afisa scorul pe ecran si mesajul "LEVEL2", daca jucatorul a atins scorul de 2

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(img,xCoor, 0, null);
        g.drawImage(img,xCoor+2400, 0, null);

        bi.drawBird(g);
        wi.drawWall(g);
        wii.drawWall(g);

        g.setFont(new Font("Calibri",Font.BOLD,40));
        g.drawString("Score "+score,WIDTH/2-100,100);
        if(score>=2){
            g.drawString("LEVEL 2",WIDTH/2-100,200);
        }
    }

    // In metoda Move() vom apela functiile de miscare a elementelor pe care le-am incarcat pe ecran in metoda paint()
    // Tot in metoda asta vom reseta coordonatele imaginii de fundal in caz ca jucatorul a piedut
    // Scorul se ca calcula tot in aceasta metoda
    public void Move(){

        bi.birdMovement();
        wi.wallMovement();
        wii.wallMovement();

        if(GameOver){
            wi.X=GamePanel.WIDTH;
            wii.X=GamePanel.WIDTH+(GamePanel.WIDTH/2);
            score=0;
            GameOver=false;
        }
        xCoor+=WallImage.speed;

        if(xCoor==-2400)
            xCoor=0;
        if(wi.X==BirdImage.x||wii.X==BirdImage.x)
        {
            score+=1;
        }
    }

    //In metoda popUpMessage setam o casuta de dialog pentru atunci cand jucatorul va pierde
    public static boolean popUpMessage(){
        int result= JOptionPane.showConfirmDialog(null,"Game Over \n Your score is "+score+"\n Doriti sa jucati din nou?","Game Over",JOptionPane.YES_NO_OPTION);

        if(result==JOptionPane.YES_OPTION){
            return true;}
        else
            return false;
    }

    // Al doilea nivel
    public void newLevel(){

        if(score>=2)
            WallImage.speed=-9;

        if(GameOver)

            WallImage.speed=-6;
    }

    // Se reseteaza viteza
    public static void speedReset(){
        WallImage.speed=-6;
    }


}

