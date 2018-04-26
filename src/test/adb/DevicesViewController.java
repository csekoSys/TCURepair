package test.adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class DevicesViewController implements Initializable {

    @FXML
    private TextArea adbDevicesTextArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAdbDevices();
    }

    public void getAdbDevices() {
        String line = null;

        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            Pattern pattern = Pattern.compile("^([a-zA-Z0-9\\-]+)(\\s+)(device)");
            Matcher matcher;

            while ((line = in.readLine()) != null) {
                if (line.matches(pattern.pattern())) {
                    matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        System.out.println(matcher.group(1));
                        adbDevicesTextArea.appendText(matcher.group(1) + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
