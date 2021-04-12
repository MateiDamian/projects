package FlappyBird;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static JFrame window;//declaram fereastra
    public static Timer timer;//declaram timerul

    private Main(){
        window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(GamePanel.WIDTH,GamePanel.HEIGHT);
        window.setLocationRelativeTo(null);
        window.setTitle("FLAPPY BIRD 2020");
        window.setResizable(true);
    }
    // in aceasta metoda, fiind cea mai importanta pt functionare corecta a jocului, vom redesena si actualiza pozitia eroului
    // toate acestea se vor face la un interval f scurt, 20 de ms
    //

    private void rendering(){
        MenuPanel mp= new MenuPanel();
        GamePanel gp=new GamePanel();

        timer= new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gp.repaint();
                gp.Move();
                if(GamePanel.score>=2)
                    gp.newLevel();
            }
        });
        // se va adauga imaginea de inceput
        window.add(mp);
        window.setVisible(true);
        while(mp.StartingPoint==false){
            try{
                Thread.sleep(10);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        //while-ul se va termina atunci cand dam click pe ecram
        //atunci va disparea imaginea initiala, schimbandu-se cu imaginea jocului

        window.remove(mp);
        window.add(gp);
        gp.setVisible(true);
        window.revalidate();

        timer.start();// porneste timerul
    }
    public static JFrame getWindow(){
        return window;
    }
    public static void main(String[] args){

        Main bird=new Main();
        bird.rendering();
    }
}
