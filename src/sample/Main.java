package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout,800, 400);
        scene.setFill(Color.GREEN);
        BallSetter bs = new BallSetter();
        Circle cueBall = bs.createCueBall();
        layout.getChildren().add(cueBall);


        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        BallCreator bc = new BallCreator();
        BallBuilder cueBallBuilder = new CueBallBuilder();
        BallBuilder colorBallBuilder = new ColorBallBuilder();

        bc.setBallBuilder(cueBallBuilder);
        bc.contrsuctBall();

        Ball cueB = bc.getBall();


    }

}
