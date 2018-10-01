package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TableArea extends Rectangle {
    private Color color;
    private double friction;
    private double width;
    private double height;
    double borderW;
    double borderH;
    public double top;
    public double right;
    public double bottom;
    public double left;


    public TableArea() {
        this.color = getColor();
        this.friction = getFriction();
        this.height = getHeight();
        this.width = getWidth();
        borderW = 0.056*this.getWidth();
        borderH = 0.105*this.getHeight();
        this.top = 100+borderH;
        this.left = 100+borderW;
        this.bottom = 100 + borderH + this.height;
        this.right = 100 + borderW + this.width;
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
