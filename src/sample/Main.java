package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


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
        Circle ball = bs.createCueBall();
        root.getChildren().add(ball);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>() {

                    double dx =3; //Step on x or velocity
                    double dy = 4; //Step on y

                    @Override
                    public void handle(ActionEvent t) {
                        //move the ball
                        ball.setLayoutX(ball.getLayoutX() + dx);
                        ball.setLayoutY( ball.getLayoutY() + dy);

                        Bounds bounds = canvas.getBoundsInLocal();



                        if(( ball.getLayoutY() >= (bounds.getMaxY() -  ball.getRadius()))){

                            dy = -dy;

                        }

                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();




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
