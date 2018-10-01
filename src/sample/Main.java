package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        primaryStage.setTitle("Hello World");

        ConfigReader cf = new ConfigReader();
        cf.parse("config.json");

        Table table = cf.table;
        double w = table.getWidth() + 200;
        double h = table.getHeight() + 200;
        table.setX(100);
        table.setY(100);

        root.getChildren().add(table);

        ArrayList balls = cf.balls;

        for(int i = 0; i<balls.size(); i++ ){
            Ball newBall = (Ball) balls.get(i);
            root.getChildren().add(newBall);
        }



        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, w, h, Color.GREEN));

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>() {

                    double dx; //Step on x or velocity
                    double dy; //Step on y

                    @Override
                    public void handle(ActionEvent t) {
                        for(Object ball: balls){
                            Ball b = (Ball)ball;
                            double dx = b.getVelX();
                            double dy = b.getVelY();


                            ((Ball) ball).setPosX(((Ball) ball).getPosX() + dx);
                            ((Ball) ball).setPosY( ((Ball) ball).getPosY() + dy);

                            Bounds bounds = root.getBoundsInLocal();


//Bounces off the ball
                            if(( ((Ball) ball).getPosX() >= (bounds.getMaxY() -  10))){

                                dy = -dy;



                            }

                        }
                    }}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
