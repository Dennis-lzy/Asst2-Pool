package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {

    public ArrayList<Ball> balls = new ArrayList<>();
    public Table table = new Table();


    public void parse(String path) {

        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(path));

            // convert Object to JSONObject
            JSONObject jsonObject = (JSONObject) object;

            // reading the Table section:
            JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

            // reading a value from the table section
            Color tableColour = (Color) Paint.valueOf((String) jsonTable.get("colour"));

            // reading a coordinate from the nested section within the table
            // note that the table x and y are of type Long (i.e. they are integers)
            Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
            Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");

            // getting the friction level.
            // This is a double which should affect the rate at which the balls slow down
            Double tableFriction = (Double) jsonTable.get("friction");

            table.setColor(tableColour);
            table.setWidth(tableX);
            table.setHeight(tableY);
            table.setFriction(tableFriction);
            double borderW = 0.056*table.getWidth();
            double borderH = 0.105*table.getHeight();
            table.setX(borderW);
            table.setY(borderH);
            table.bounds = table.getBoundsInLocal();




            //---------------------------------------


            // reading the "Balls" section:
            JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");

            // reading the "Balls: ball" array:
            JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");

            // reading from the array:
            for (Object obj : jsonBallsBall) {
                JSONObject jsonBall = (JSONObject) obj;



                // the ball colour is a String
                Color ballColour = (Color) Paint.valueOf((String) jsonBall.get("colour"));

                // the ball position, velocity, mass are all doubles
                Double posX = (Double) ((JSONObject) jsonBall.get("position")).get("x");
                Double posY = (Double) ((JSONObject) jsonBall.get("position")).get("y");

                Double velX = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
                Double velY = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");

                Double mass = (Double) jsonBall.get("mass");

                Ball ball = new Ball(posX, posY, 10, ballColour);

                ball.setColor(ballColour);

//                if(posX>table.bounds.getMaxX()){
//                    posX = table.bounds.getMaxX();
//                }
//                if(posX<table.bounds.getMinX()){
//                    posX = table.bounds.getMinX();
//                }
//                if(posY>table.bounds.getMaxY()){
//                    posY = table.bounds.getMaxY();
//                }
//                if(posY<table.bounds.getMinY()){
//                    posY = table.bounds.getMinY();
//                }
                ball.setPosX(posX);
                ball.setPosY(posY);
                ball.setVelX(velX);
                ball.setVelY(velY);
                ball.setMass(mass);

                balls.add(ball);


                System.out.println(ball.toString());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Your main method will probably be in another file!
     *
     * @param args First argument is the path to the config file
     */
//    public static void main(String[] args) {
//        // if a command line argument is provided, that should be used as the path
//        // if not, you can hard-code a default. e.g. "config.json"
//        // this makes it easier to test your program with different config files
//        ConfigReader cf = new ConfigReader();
//        String configPath;
//        if (args.length > 0) {
//            configPath = args[0];
//        } else {
//            configPath = "config.json";
//        }
//        // parse the file:
//        cf.parse(configPath);
//    }

}
