package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class Ball extends Circle {

    private Color colour;
    private double posX;
    private double posY;
    private double velX;
    private double velY;
    private double mass;



    public Ball(double centerX, double centerY, double radius, Color colour) {
        super(centerX, centerY, radius);
        this.colour = colour;
        this.setCenterX(centerX);
        this.setCenterY(centerY);
        this.setRadius(radius);
        this.setFill(colour);

    }

    public Color getColor() {
        return colour;
    }

    public void setColor(Color colour) {
        this.colour = colour;

    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {

        this.posX = posX;
        this.setCenterX(posX);
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
        this.setCenterY(posY);
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getTV() {
        double tv = Math.sqrt(pow(this.velX,2) + pow(this.velY, 2));
        return tv;
    }


    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }


}
