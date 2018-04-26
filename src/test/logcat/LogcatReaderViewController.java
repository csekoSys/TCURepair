package test.logcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class LogcatReaderViewController implements Initializable {

    @FXML
    private TextArea logcatTextArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logcatReader("9JT7N17222001032");
    }

    private void logcatReader(String imsi) {
        String cmd = "adb -s " + imsi + " shell logcat";

        try {
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder logcat = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                //logcat.append(line).append("\n");
                logcatTextArea.appendText(line + "\n");
                process.waitFor();
                bufferedReader.close();
                process.destroy();
            }

        } catch (IOException ex) {
        } catch (InterruptedException ex) {
        }
    }

}
