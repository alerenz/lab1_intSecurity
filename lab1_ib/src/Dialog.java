import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class Dialog extends SwingWorker<Component, Void> {
    private final JDialog dialog;

    public Dialog(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    protected Component doInBackground() throws Exception {
       dialog.setVisible(true);
       return dialog;
    }

    @Override
    protected void done() {
        dialog.dispose();
    }
}
