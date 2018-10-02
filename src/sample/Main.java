package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

public class Main extends Application {

    private Cue cue;
    private Ball CueBall;
    private DoubleProperty mouseX = new SimpleDoubleProperty();
    private DoubleProperty mouseY = new SimpleDoubleProperty();

    private List<Circle> nodes;
    private Line currentLine = null;
    private boolean dragActive = false;
    Group root = new Group();
    boolean checkMovement = false;


    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Pool Game");

        ConfigReader cf = new ConfigReader();
        cf.parse("config2.json");

        //creates table
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

        //declares cue that lets you drag and release to shoot;
        cue = new Cue(CueBall);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, w, h));

        //Game loop

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>() {
                    //velocity
                    double dx;
                    double dy;

                    //instantiates collision handler
                    CollisionHandler ch = new CollisionHandler();

                    //instantiates pockets
                    Pocket p = new Pocket(table);



                    @Override
                    public void handle(ActionEvent t) {

                        //balls arraylist iterator;
                        for(Object ball: balls){
                            Ball b = (Ball)ball;
                            dx = b.getVelX();
                            dy = b.getVelY();

                            //implements deceleration with friction
                            if(dx>0) {
                                dx *= table.getFriction();
                                //System.out.println(dx);
                            } else if (dx<0){
                               dx *= table.getFriction();

                            }
                            if(dy>0){
                                dy *=  table.getFriction();
                            } else if (dy<0){
                                dy *= table.getFriction();
                            }

                            //checks to make sure cue ball is not sunk
                            if(p.checkIfPocketed(b) && b!=CueBall){
                                root.getChildren().remove(ball);
                                continue;
                            }

                            //repositions cue ball if sunk
                            if(p.checkIfPocketed(b) && b == CueBall){
                                b.setPosX(200);
                                b.setVelY(150);
                            }

                            //handles wall collisions
                            if(ch.checkWallCollisionX(b, table)){
                                dx = -dx;
                            }
                            if(ch.checkWallCollisionY(b, table)){
                                dy = -dy;
                            }

                            //updates postions
                            b.setPosX(b.getPosX() + dx);
                            b.setPosY(b.getPosY() + dy);

                            //set threshold for velocity to fall to zero
                            if(dx>-0.05&&dx < 0.05){
                                dx =0.0;
                            }
                            if(dy>-0.05&&dy < 0.05){
                                dy=0.0;
                            }

                            //updates velocity
                            b.setVelX(dx);
                            b.setVelY(dy);
                            System.out.println(b.getTV());

                            //checks to makes sure balls have stopped moving
                            if(b.getTV()==0){
                                checkMovement = true;
                            } else {
                                checkMovement =false;
                            }
                            }

                        //handles all ball collisions
                        ch.handleCollisions(balls);

                        if(checkMovement){
                            attachHandlers(root);
                            System.out.println("motion still");
                        }
                    }}));


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();




        primaryStage.show();


    }

    //Implements click and drag to shoot cue ball

    private void startDrag(Ball node) {
        if (dragActive)
            return;

        dragActive = true;
        currentLine = new Line();
        currentLine.setUserData(node);
        currentLine.setStartX(node.getCenterX());
        currentLine.setStartY(node.getCenterY());
        currentLine.endXProperty().bind(mouseX);
        currentLine.endYProperty().bind(mouseY);

        root.getChildren().add(currentLine);
    }

    private void stopDrag(Circle node) {
        dragActive = false;

        stopDrag();

    }

    private void stopDrag() {
        dragActive = false;
        cue.setPower(currentLine);
        cue.shoot(CueBall,currentLine);
        System.out.println(cue.power);

        currentLine.endXProperty().unbind();
        currentLine.endYProperty().unbind();
        root.getChildren().remove(currentLine);

        currentLine = null;
    }

    private void attachHandlers(Group group) {
        group.setOnMouseMoved(e -> {
            mouseX.set(e.getSceneX());
            mouseY.set(e.getSceneY());
        });

        group.setOnMouseDragged(e -> {
            mouseX.set(e.getSceneX());
            mouseY.set(e.getSceneY());
        });

        group.setOnMousePressed(e -> {
            startDrag(CueBall);
        });

        group.setOnMouseReleased(e -> {
                stopDrag();
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}
