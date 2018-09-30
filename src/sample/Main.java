package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        StackPane root = new StackPane();

        Canvas canvas = new Canvas(800, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeText("Hello world", 400,400);

        root.getChildren().add(canvas);

        Scene scene = new Scene(root,800, 400);
        scene.setFill(Color.GREEN);
        BallSetter bs = new BallSetter();
        Circle cueBall = bs.createCueBall();
        root.getChildren().add(cueBall);



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
