import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ReadBitsTask extends SwingWorker<Void, String> {
    private final JTextArea result;
    public ReadBitsTask(JTextArea result) {
        this.result = result;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try (FileReader reader = new FileReader("bits.txt")) {
            Scanner sc = new Scanner(reader);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                Thread.sleep(100);
                publish(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        result.append(chunks.get(chunks.size() - 1));
    }

    @Override
    protected void done() {
        InputTestPanel.bitsSeq = result.getText();
    }

   
//    private String readBitSequence()  {
//        StringBuilder result = new StringBuilder();
//        try (FileReader reader = new FileReader("bits.txt")) {
//            Scanner sc = new Scanner(reader);
//            BufferedReader br = new BufferedReader(reader);
//            String line;
//            while ((line = br.readLine()) != null) {
//                result.append(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return result.toString().trim();
//    }
}
