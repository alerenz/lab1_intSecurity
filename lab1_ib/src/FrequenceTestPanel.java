import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FrequenceTestPanel extends JPanel {
    public static int [] sequence;
    private final JButton butCont;
    private final JButton butBack;
    private final JLabel textStat;
    private final JTextField textAns;
    private final JTextField answer;
    public static String [] statistics;


    public InputTestPanel panelI;
    public SequenceOgIdenticalBitsPanel panelS;

    public static boolean check;
    public static BigDecimal mainCheck = new BigDecimal("1.82138636");

    public FrequenceTestPanel() {
        setLayout(null);
        setBackground(MainFrame.cFon);


        JLabel nameTest = new JLabel();
        nameTest.setText("Частотный тест");
        nameTest.setForeground(MainFrame.cText);
        nameTest.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 24));
        nameTest.setBounds((int) MainFrame.screen[0]/4 - 100,30,
                (int) MainFrame.screen[0]/4, 40);
        add(nameTest);

        JButton startTestBut = new JButton("Запустить");
        startTestBut.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 16));
        startTestBut.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        startTestBut.setBackground(MainFrame.cBackBut);
        startTestBut.setForeground(MainFrame.cText);
        startTestBut.setBounds( (int) MainFrame.screen[0]/4 - 100, 85,
                (int) MainFrame.screen[0]/8, 30);
        add(startTestBut);

        textStat = new JLabel();
        textStat.setText("Статистика теста = ");
        textStat.setForeground(MainFrame.cText);
        textStat.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 24));
        textStat.setBounds((int) MainFrame.screen[0]/6 - (int) MainFrame.screen[0]/18,
                (int) MainFrame.screen[1]/2 - 150,
                (int) MainFrame.screen[0]/6 - 30, 40);

        answer = new JTextField();
        answer.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 24));
        answer.setEditable(false);
        answer.setForeground(MainFrame.cText);
        answer.setBackground(MainFrame.cFon);
        answer.setBorder(BorderFactory.createEmptyBorder());
        answer.setBounds((int) MainFrame.screen[0]/3 - (int) MainFrame.screen[0]/18 - 30 ,(int) MainFrame.screen[1]/2 - 150,
                (int) MainFrame.screen[0]/3, 40);

        butCont = new JButton("Далее");
        butCont.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 18));
        butCont.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        butCont.setBackground(MainFrame.cBackBut);
        butCont.setForeground(MainFrame.cText);
        butCont.setBounds( (int) MainFrame.screen[0]/4 - 50, (int) MainFrame.screen[1]/2 + 160,
                (int) MainFrame.screen[0]/16, 40);

        butBack = new JButton("Начать заного");
        butBack.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 18));
        butBack.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        butBack.setBackground(MainFrame.cBackBut);
        butBack.setForeground(MainFrame.cText);
        butBack.setBounds( (int) MainFrame.screen[0]/4 - 100, (int) MainFrame.screen[1]/2 + 160,
                (int) MainFrame.screen[0]/8, 40);

        textAns = new JTextField();
        textAns.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 24));
        textAns.setEditable(false);
        textAns.setForeground(Color.RED);
        textAns.setBackground(MainFrame.cFon);
        textAns.setBorder(BorderFactory.createEmptyBorder());
        textAns.setBounds((int) MainFrame.screen[0]/4 - 100 ,(int) MainFrame.screen[1]/2 + 20,
                (int) MainFrame.screen[0]/4, 40);

        startTestBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stringToInt(InputTestPanel.bitsSeq);
                statistics = new String[20];
                check = false;
                statistics[0] = frequenceTest();
                answer.setText(statistics[0]);
                add(textStat);
                add(answer);
                if (check){
                    textAns.setText("Тест пройден!!");
                    add(textAns);
                    add(butCont);
                }else{
                    textAns.setText("Тест завален!!");
                    add(textAns);
                    add(butBack);
                }
                repaint();
                revalidate();

            }
        });

        butCont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                panelS = new SequenceOgIdenticalBitsPanel();
                panelS.setBounds(0,0,(int) MainFrame.screen[0]/2,
                        (int) MainFrame.screen[1] - 100);
                add(panelS);
                repaint();
                revalidate();
            }
        });

        butBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                panelI = new InputTestPanel();
                panelI.setBounds(0,0,(int) MainFrame.screen[0]/2,
                        (int) MainFrame.screen[1] - 100);
                add(panelI);
                repaint();
                revalidate();
            }
        });

    }
    public static void stringToInt(String str){
        String [] chars = str.split(" ");
        sequence = new int[chars.length];
        for(int i = 0; i< chars.length; i++){
            sequence[i] = Integer.parseInt(chars[i]);
        }
    }

    public static String frequenceTest(){
        double [] seq = new double[sequence.length];
        double sum = 0;
        for(int i = 0; i < seq.length; i++){
            seq[i] = 2 * (double) sequence[i] - 1;
            sum += seq[i];
        }
        BigDecimal stat = BigDecimal.valueOf(Math.abs(sum) /
                Math.sqrt(seq.length)).setScale(8, RoundingMode.CEILING);
        check = stat.compareTo(mainCheck) == 0 || stat.compareTo(mainCheck) < 0;

            return stat.toString();
    }

}
