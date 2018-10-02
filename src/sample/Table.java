package sample;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Table extends Rectangle {
    private Color color;
    private double friction;
    private double width;
    private double height;
    public Bounds bounds;


    public Table() {
        this.color = getColor();
        this.friction = getFriction();
        this.height = getHeight();
        this.width = getWidth();
        this.bounds = getBoundsInLocal();


    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.setFill(color);
    }

    public double getFriction() {
        return friction;
    }

    //sets friction

    public void setFriction(double friction) {
        double offset;
        if (friction <1){
            offset = 1-(friction/100-0.003) ;
        }

        double d = 0.993;

        this.friction = d;

    }


}
