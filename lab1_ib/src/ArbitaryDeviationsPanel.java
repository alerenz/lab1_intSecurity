import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ArbitaryDeviationsPanel extends JPanel {
    private final JLabel text2;
    private final JTextArea answer;
    private final JTextField textAns;
    private final JButton butCont;
    private final JButton butBack;
    public InputTestPanel panelI;
    public ResultsPanel panelR;
    public static boolean check;
    private static final BigDecimal mainCheck = new BigDecimal("1.82138636");
    public ArbitaryDeviationsPanel(){
        setLayout(null);
        setBackground(MainFrame.cFon);

        JLabel nameTest = new JLabel();
        nameTest.setText("Расширенный тест на произвольные отклонения");
        nameTest.setForeground(MainFrame.cText);
        nameTest.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 24));
        nameTest.setBounds((int) MainFrame.screen[0]/8 - 100,30,
                (int) MainFrame.screen[0]/2, 40);
        add(nameTest);

        JButton startTestBut = new JButton("Запустить");
        startTestBut.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 16));
        startTestBut.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        startTestBut.setBackground(MainFrame.cBackBut);
        startTestBut.setForeground(MainFrame.cText);
        startTestBut.setBounds( (int) MainFrame.screen[0]/4 - 100, 85,
                (int) MainFrame.screen[0]/8, 30);
        add(startTestBut);

        text2 = new JLabel();
        text2.setText("Статистики: ");
        text2.setForeground(MainFrame.cText);
        text2.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 16));
        text2.setBounds((int) MainFrame.screen[0]/4 - 50,125,
                (int) MainFrame.screen[0]/8, 20);


        answer = new JTextArea();
        answer.setFont(new java.awt.Font("TimesRoman",Font.BOLD, 16));
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setEditable(false);
        answer.setBackground(MainFrame.cFon);
        answer.setForeground(MainFrame.cText);
        answer.setBounds((int) MainFrame.screen[0]/4 - 100,
                155,
                (int) MainFrame.screen[0]/4 + 100, (int) MainFrame.screen[1]/2 - 30);

        textAns = new JTextField();
        textAns.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 24));
        textAns.setEditable(false);
        textAns.setForeground(Color.RED);
        textAns.setBackground(MainFrame.cFon);
        textAns.setBorder(BorderFactory.createEmptyBorder());
        textAns.setBounds((int) MainFrame.screen[0]/4 - 70 ,(int) MainFrame.screen[1]/2 + 135,
                (int) MainFrame.screen[0]/4, 40);


        butCont = new JButton("Результаты");
        butCont.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 18));
        butCont.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        butCont.setBackground(MainFrame.cBackBut);
        butCont.setForeground(MainFrame.cText);
        butCont.setBounds( (int) MainFrame.screen[0]/4 - 100, (int) MainFrame.screen[1]/2 + 185,
                (int) MainFrame.screen[0]/8, 30);


        butBack = new JButton("Начать заного");
        butBack.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 18));
        butBack.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        butBack.setBackground(MainFrame.cBackBut);
        butBack.setForeground(MainFrame.cText);
        butBack.setBounds( (int) MainFrame.screen[0]/4 - 100, (int) MainFrame.screen[1]/2 + 185,
                (int) MainFrame.screen[0]/8, 30);

        startTestBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check = false;
                String str = arbitraryDeviationsTest();
                answer.setText(str);
                add(text2);
                add(answer);
                if(!check){
                    textAns.setText("Тест пройден!");
                    add(textAns);
                    add(butCont);
                }else{
                    textAns.setText("Тест завален!");
                    add(textAns);
                    add(butBack);
                }
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

        butCont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                panelR = new ResultsPanel();
                panelR.setBounds(0,0,(int) MainFrame.screen[0]/2,
                        (int) MainFrame.screen[1] - 100);
                add(panelR);
                repaint();
                revalidate();
            }
        });
    }



    // Расширенный тест на произвольные отклонения
    public static String arbitraryDeviationsTest(){
        double [] seq = new double[FrequenceTestPanel.sequence.length];
        for(int i = 0; i < seq.length; i++){
            seq[i] = 2 * FrequenceTestPanel.sequence[i] - 1;
        }
        double [] si = new double[seq.length];
        double [] s = new double[si.length + 2];
        si[0] = seq[0];
        s[0] = 0;
        s[1] = si[0];
        int k = 2;
        for(int i = 1; i < si.length; i++){
            si[i] = si[i - 1] + seq[i];
            s[k] = si[i];
            k++;
        }
        s[k] = 0;
        k = 0;
        double [][] arr = {{-9, 0}, {-8, 0}, {-7, 0}, {-6, 0}, {-5, 0}, {-4, 0}, {-3, 0}, {-2, 0}, {-1, 0},
                {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}};
        for (double v : s) {
            if (v == 0) {
                k++;
            }
            for (int j = 0; j < arr.length; j++) {
                if (v == 0) {
                    break;
                }
                if (v == arr[j][0]) {
                    arr[j][1]++;
                }
            }
        }

        double l = k - 1;
        ArrayList<BigDecimal> stat = new ArrayList<>();
        for (double[] doubles : arr) {
            stat.add(new BigDecimal((Math.abs(doubles[1] - l)) /
                    (Math.sqrt(2 * l * (4 * Math.abs(doubles[0]) - 2)))).
                    setScale(8, RoundingMode.CEILING));
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 2; i < FrequenceTestPanel.statistics.length; i++) {
            FrequenceTestPanel.statistics[i] = stat.get(i-2).toString();
            ans.append("Статистика ").append(i - 1).append(": ").
                    append(FrequenceTestPanel.statistics[i]).append("\n");
            if (stat.get(i - 2).compareTo(mainCheck) > 0) {
                check = true;
                break;
            }

        }
        return ans.toString();
    }

}
