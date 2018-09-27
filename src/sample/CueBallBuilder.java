package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
