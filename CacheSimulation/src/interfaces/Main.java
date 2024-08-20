package interfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    private static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {

        playMusic("/resources/telainicial.mp3");

        Parent root = FXMLLoader.load(getClass().getResource("/resources/TelaInicial.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/resources/estilo.css").toExternalForm());

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icone.png")));

        primaryStage.setTitle("Cache Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void playMusic(String musicFile) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        URL resource = Main.class.getResource(musicFile);
        if (resource != null) {
            Media sound = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } else {
            System.out.println("Arquivo de música não encontrado!");
        }
    }

    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
