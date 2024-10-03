import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsPanel extends JPanel {
    public InputTestPanel panelI;
    public ResultsPanel(){
        setLayout(null);
        setBackground(MainFrame.cFon);

        JLabel text1 = new JLabel();
        text1.setText("Результаты");
        text1.setForeground(MainFrame.cText);
        text1.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 24));
        text1.setBounds((int) MainFrame.screen[0]/4 - 75,15,
                (int) MainFrame.screen[0]/4, 30);
        add(text1);

        JLabel text2 = new JLabel();
        text2.setText("Статистики");
        text2.setForeground(MainFrame.cText);
        text2.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 18));
        text2.setBounds((int) MainFrame.screen[0]/4 - 50,50,
                (int) MainFrame.screen[0]/6, 30);
        add(text2);

        String str = printResults();
        JTextArea res = new JTextArea();
        res.setText(str);
        res.setBounds((int) MainFrame.screen[0]/8 - 100,(int) MainFrame.screen[1]/10,
                (int) MainFrame.screen[0]/4 + 130, (int) MainFrame.screen[1]/2 + 80);
        res.setFont(new java.awt.Font("TimesRoman",Font.BOLD, 17));
        res.setLineWrap(true);
        res.setWrapStyleWord(true);
        res.setEditable(false);
        res.setBackground(MainFrame.cFon);
        res.setForeground(MainFrame.cText);
        add(res);

        JButton butBack = new JButton("Начать заного");
        butBack.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 18));
        butBack.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        butBack.setBackground(MainFrame.cBackBut);
        butBack.setForeground(MainFrame.cText);
        butBack.setBounds( (int) MainFrame.screen[0]/4 - 100, (int) MainFrame.screen[1]/2 + 175,
                (int) MainFrame.screen[0]/8, 30);
        add(butBack);

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

    public static String printResults(){
        StringBuilder str = new StringBuilder();
        str.append("Частотный тест: ").append(FrequenceTestPanel.statistics[0]).append("\n");
        str.append("Тест на последовательность одинаковых бит: ").append(FrequenceTestPanel.statistics[1]).append("\n");
        str.append("Расширенный тест на произвольные отклонения:\n");
        for(int i = 2; i < FrequenceTestPanel.statistics.length; i++){
            str.append(i-1).append(": ").append(FrequenceTestPanel.statistics[i]).append("\n");
        }
        return str.toString();
    }
}
