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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.scene.shape.Line;

public class Main extends Application {

    private Line currentLine;
    private Ball CueBall;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        primaryStage.setTitle("Pool Game");

        ConfigReader cf = new ConfigReader();
        cf.parse("config.json");

        Table table = cf.table;
        //border offset of table image
        double borderW = 0.056*table.getWidth();
        double borderH = 0.105*table.getHeight();

        //window size offset based on table size
        double w = table.getWidth()+2*borderW; //+ 200 + 2*borderW;
        double h = table.getHeight()+2*borderH; //+ 200 + 2*borderH;

        //sets table position
        table.setX(borderW);
        table.setY(borderH);

        // table image
        ImageView tableI = new ImageView(new Image(getClass().getResourceAsStream("/table.png")));
        tableI.setX(0);
        tableI.setY(0);
        tableI.setFitWidth(table.getWidth()+ (2*borderW)); //adjust size to accommodate for borders
        tableI.setFitHeight(table.getHeight()+(2*borderH));

        root.getChildren().addAll(tableI, table);

        //Adds all balls to group

        ArrayList balls = cf.balls;

        for(int i = 0; i<balls.size(); i++ ){
            Ball newBall = (Ball) balls.get(i);
            if (newBall.getColor()== Color.WHITE){
                CueBall = newBall;
            }
            root.getChildren().add(newBall);
        }





        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, w, h));

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>() {

                    double dx; //Step on x or velocity
                    double dy; //Step on y
                    CollisionHandler ch = new CollisionHandler();



                    @Override
                    public void handle(ActionEvent t) {

                        //---cue drag

                        root.setOnMousePressed(e -> {
                            currentLine = new Line(CueBall.getPosX(), CueBall.getPosY(), CueBall.getPosX(), CueBall.getPosY());
                            root.getChildren().add(currentLine);
                        });

                        root.setOnMouseDragged(e -> {
                            currentLine.setEndX(e.getX());
                            currentLine.setEndY(e.getY());
                        });

                        //-----
                        for(Object ball: balls){
                            Ball b = (Ball)ball;
                            dx = b.getVelX();
                            dy = b.getVelY();



                            if(dx>0) {
                                dx *= table.getFriction();
                                System.out.println(dx);
                            } else if (dx<0){
                               dx *= table.getFriction();

                            }
                            if(dy>0){
                                dy *=  table.getFriction();
                            } else if (dy<0){
                                dy *= table.getFriction();
                            }

                            if(ch.checkWallCollisionX(b, table)){
                                dx = -dx;
                            }

                            if(ch.checkWallCollisionY(b, table)){
                                dy = -dy;
                            }
                            b.setPosX(b.getPosX() + dx);
                            b.setPosY(b.getPosY() + dy);

                            b.setVelX(dx);
                            b.setVelY(dy);




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
