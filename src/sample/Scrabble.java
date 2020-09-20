package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class Scrabble extends Application {
    Player playerOne = new Player("Player1");
    Player playerTwo = new Player("Player2");

    UI ui = new UI(playerOne, playerTwo);
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("ScrabbleGame");
        Scene scene = new Scene(ui.contentInWindow());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        ui.playGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
