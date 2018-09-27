package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;;

public class BallSetter {
    public Ball cueBall;
    public List<Ball> colorBalls = new ArrayList<Ball>();
    public Ball colorBall;
    public BallCreator bc = new BallCreator();
    public BallBuilder cueBallBuilder = new CueBallBuilder();
    public BallBuilder colorBallBuilder = new ColorBallBuilder();


    public Circle createCueBall() {
        bc.setBallBuilder(cueBallBuilder);
        bc.contrsuctBall();

        cueBall = bc.getBall();
        Color color = cueBall.getColor();
        double posX  = cueBall.getPosX();
        double posY  = cueBall.getPosY();
        double velX  = cueBall.getVelX();
        double velY  = cueBall.getVelY();
        double mass  = cueBall.getMass();

        Circle newBall = new Circle(posX, posY, 10, color);
        return newBall;

    }

    public void createColorBall() {
        bc.setBallBuilder(colorBallBuilder);
        bc.contrsuctBall();

       // colorBalls.add(bc.getBall());
        colorBall = bc.getBall();
        Color color = colorBall.getColor();
        double posX  = colorBall.getPosX();
        double posY  = colorBall.getPosY();
        double velX  = colorBall.getVelX();
        double velY  = colorBall.getVelY();
        double mass  = colorBall.getMass();

    }
}
