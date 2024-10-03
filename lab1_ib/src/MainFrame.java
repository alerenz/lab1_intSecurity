import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
   public InputTestPanel panel1;
    public static double [] screen;
    public static Color cBorders = new Color(96, 28, 48);
    public static Color cBackBut = new Color(243,215,202);
    public static Color cText = new Color(124, 56, 74);
    public static Color cFon = new Color(245,238,230);
    public MainFrame() {
        super("lab1");
        screen = new double[2];
        screen = screenWH();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBackground(cFon);
        setSize((int) screen[0]/2, (int) screen[1] - 50);
        setMinimumSize(new Dimension((int) screen[0]/2, (int) screen[1] - 50));
        setLocationRelativeTo(null);

        panel1 = new InputTestPanel();
        panel1.setBounds(0,0,(int) screen[0]/2,(int) screen[1] - 100);

        getContentPane().add(panel1);
        setResizable(false);
        setVisible(true);
    }
    public static double[] screenWH(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        screen[0] = width;
        screen[1] = height;
        return screen;
    }
}
