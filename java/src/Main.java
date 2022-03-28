import javax.swing.*;

public class Main extends JFrame {
    JMenu moveR = new JMenu("Начать движение - клавиша (R)un");
    JMenu moveS = new JMenu("Движение текста по кнопке - (S)top");

    JMenuBar menuBar = new JMenuBar();

    public Main(){
        setTitle("Параллелизм и потоки");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(800, 600);
        setLocation(400, 400);
        Habitat habitatPanel = new Habitat();
        add(habitatPanel);
        setVisible(true);

        moveR.setEnabled(false);
        moveS.setEnabled(false);
        
        add(menuBar);
        menuBar.add(moveR);
        menuBar.add(moveS);
        setJMenuBar(menuBar);

    }

    public static void main(String[] args){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main mw = new Main();
    }

}
