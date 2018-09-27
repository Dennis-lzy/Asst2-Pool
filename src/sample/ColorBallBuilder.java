package sample;

import javafx.scene.paint.Color;

public class ColorBallBuilder extends BallBuilder{

    public void buildColor() {
        ball.setColor(Color.RED);
    }
    public void buildPosX() {
        ball.setPosX(50.0);
    }
    public void buildPosY() {
        ball.setPosY(200.0);
    }
    public void buildVelX() {
        ball.setVelX(0.0);
    }
    public void buildVelY() {
        ball.setVelY(1.0);
    }
    public void buildMass() {
        ball.setMass(1.0);
    }
}
