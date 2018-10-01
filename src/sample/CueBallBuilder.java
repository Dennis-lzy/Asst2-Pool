package sample;

import javafx.scene.paint.*;

public class CueBallBuilder extends BallBuilder  {

    private ConfigReader cf = new ConfigReader();
    private String configPath = "config.json";

    public void buildColor() {
        ball.setColor(Color.WHITE);
    }
    public void buildPosX() {
        ball.setPosX(10.0);
    }
    public void buildPosY() {
        ball.setPosY(200.0);
    }
    public void buildVelX() {
        ball.setVelX(1.0);
    }
    public void buildVelY() {
        ball.setVelY(0.0);
    }
    public void buildMass() {
        ball.setMass(1.0);
    }

}
