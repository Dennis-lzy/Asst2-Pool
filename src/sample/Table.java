package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Table extends Rectangle {
    private Color color;
    private double friction;
    private double width;
    private double height;

    public Table() {
        this.color = getColor();
        this.friction = getFriction();
        this.height = getHeight();
        this.width = getWidth();
        this.setX(this.width/2);
        this.setY(this.height/2);

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

    public void setFriction(double friction) {
        this.friction = friction;
    }


}
