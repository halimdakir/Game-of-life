package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        View view = new View();
        Scene scene = new Scene(view, 400, 458);
        stage.setScene(scene);
        stage.show();
        view.drawOnCanvas();
    }

    public static void main(String[] args) {
        launch();
    }

}