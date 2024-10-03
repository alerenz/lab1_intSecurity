import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class InputTestPanel extends JPanel {
    private final JTextField inpField;
    private final JTextField textErr;

    private final JTextArea result;
    private final JButton butRes;
    public FrequenceTestPanel panelF;
    public static String bitsSeq;
    private final JDialog dialog;
    private final JLabel textGen;
    private final JButton butPrint;
    private int len = 0;

    public InputTestPanel(){
        setLayout(null);
        setBackground(MainFrame.cFon);

        JLabel text1 = new JLabel();
        text1.setText("Введите число не меньше 10000: ");
        text1.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 16));
        text1.setBounds(130,20,(int) MainFrame.screen[0]/6+40,20);
        text1.setForeground(MainFrame.cText);
        add(text1);

        inpField = new JTextField(10);
        inpField.setForeground(MainFrame.cText);
        inpField.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 16));
        inpField.setBounds((int) MainFrame.screen[0]/6 + 150,20,
                (int) MainFrame.screen[0]/12,(int) MainFrame.screen[1]/32);
        add(inpField);

        JButton butInp = new JButton("Ввод");
        butInp.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 16));
        butInp.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        butInp.setBackground(MainFrame.cBackBut);
        butInp.setForeground(MainFrame.cText);
        butInp.setBounds( (int) MainFrame.screen[0]/6 + (int) MainFrame.screen[0]/12 + 170, 20,
                (int) MainFrame.screen[0]/20, 30);
        add(butInp);

        textErr = new JTextField();
        textErr.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 20));
        textErr.setEditable(false);
        textErr.setForeground(Color.RED);
        textErr.setBackground(MainFrame.cFon);
        textErr.setBorder(BorderFactory.createEmptyBorder());


        textGen = new JLabel();
        textGen.setText("Последовательность сгенерирована!");
        textGen.setForeground(MainFrame.cText);
        textGen.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 18));
        textGen.setBounds((int) MainFrame.screen[0]/4 - (int) MainFrame.screen[0]/8 ,80,
                (int) MainFrame.screen[0]/4,20);

        butPrint = new JButton("Вывести");
        butPrint.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 18));
        butPrint.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        butPrint.setBackground(MainFrame.cBackBut);
        butPrint.setForeground(MainFrame.cText);
        butPrint.setBounds( (int) MainFrame.screen[0]/4 - 50, 105,
                (int) MainFrame.screen[0]/16, 40);

        result = new JTextArea();
        result.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 14));
        result.setBackground(MainFrame.cFon);
        result.setForeground(MainFrame.cText);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        result.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(result);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds((int) MainFrame.screen[0]/3 - (int) MainFrame.screen[0]/5 ,155,
                (int) MainFrame.screen[0]/4, (int) MainFrame.screen[1]/2 + 50);

        butRes = new JButton("Далее");
        butRes.setFont(new java.awt.Font("TimesRoman", Font.PLAIN, 18));
        butRes.setBorder(BorderFactory.createLineBorder(MainFrame.cBorders,1));
        butRes.setBackground(MainFrame.cBackBut);
        butRes.setForeground(MainFrame.cText);
        butRes.setBounds( (int) MainFrame.screen[0]/4 - 50, (int) MainFrame.screen[1]/2 + 215,
                (int) MainFrame.screen[0]/16, 40);

        dialog = new JDialog(Main.frame,"Вывод");
        dialog.add(new JLabel("        Вывод последовательности"));
        dialog.setSize(300,150);
        //dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);


        butInp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inpField.getText().trim();
                if (!input.isEmpty()) {
                    try {
                        len = Integer.parseInt(input);
                        if (len < 0){
                            throw new NumberFormatException();
                        }
                        if (len >= 10000){
                            generateAndSaveBitSequence(len);
                            remove(textErr);
                            add(textGen);
                            add(butPrint);
                            repaint();
                            revalidate();
                        }else {
                            textErr.setText("Введите число не меньше 10000!!");
                            textErr.setBounds((int) MainFrame.screen[0]/4 - 160,80,
                                    (int) MainFrame.screen[0]/4, 30);
                            add(textErr);
                            revalidate();
                        }
                    } catch (NumberFormatException ex) {
                        textErr.setText("Введите целое число!");
                        textErr.setBounds((int) MainFrame.screen[0]/4 - 100,80,
                                (int) MainFrame.screen[0]/4, 30);
                        add(textErr);
                        revalidate();
                    }
                }else{
                    textErr.setText("Введите целое число!");
                    textErr.setBounds((int) MainFrame.screen[0]/4 - 100,80,
                            (int) MainFrame.screen[0]/4, 30);
                    add(textErr);
                    revalidate();
                }
            }
        });

        butPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(true);
                ReadBitsTask task = new ReadBitsTask(result);
                task.execute();
                try {
                    task.get();
                } catch (InterruptedException | ExecutionException ex) {
                    throw new RuntimeException(ex);
                }
                if(task.isDone()){
                    add(scrollPane);
                    add(butRes);
                    dialog.dispose();
                    repaint();
                    revalidate();
                }
            }
        });


        butRes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                panelF = new FrequenceTestPanel();
                panelF.setBounds(0,0,(int) MainFrame.screen[0]/2,
                        (int) MainFrame.screen[1] - 100);
                add(panelF);
                repaint();
                revalidate();
            }
        });
   }

    public void generateAndSaveBitSequence(int n){
        boolean [] bits = new boolean[n];
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            bits[i] = rand.nextBoolean();
        }
        try (FileWriter writer = new FileWriter("bits.txt")) {
            for (boolean bit : bits) {
                writer.write(bit ? "1 " : "0 ");
            }
            System.out.println("Save done!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
