package interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class TelaInicialController {

    public void iniciarAplicacao(ActionEvent event) {
        try {

            Main.playMusic("/resources/segundatela.mp3");

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent mainRoot = FXMLLoader.load(getClass().getResource("/resources/cachefx.fxml"));
            Scene mainScene = new Scene(mainRoot);
            stage.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
