package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Cue extends Line {

    double cueTheta = 0;
    boolean cuePresent;
    double power = 0;
    double MAXPOWER = 900;
    public static Line pathLine = new Line();

    public Cue(Ball cb) {
        super();
        super.setStrokeWidth(5);
        super.setStartX(cb.getCenterX() - 150);
        super.setStartY(cb.getCenterY());
        super.setEndX(cb.getCenterX());
        super.setEndY(cb.getCenterY());
    }


    void increasePower() {
        if(this.power < MAXPOWER) this.power += 25;
        System.out.println("Power: " + this.power);
    }

    void decreasePower() {
        if(this.power > 0) this.power -= 25;
        System.out.println("Power: " + this.power);
    }

    double getCueTheta() {
        return cueTheta;
    }
    void setPresent(boolean state) {
        cuePresent = state;
    }

    boolean getPresent() {
        return cuePresent;
    }

    void setCueTheta(double newCueTheta) {
        cueTheta = newCueTheta;
        return;
    }

    void resetPower() {
        this.power = 0;
    }

    void reposition(Ball cb) {
        this.setStartX(cb.getCenterX() - 150);
        this.setStartY(cb.getCenterY());
        this.setEndX(cb.getCenterX());
        this.setEndY(cb.getCenterY());
    }

    void shoot(Ball ball1) {

        Point2D transformedEnd = this.localToScene(this.getStartX(), this.getStartY());

        double diffX = (ball1.getCenterX() - transformedEnd.getX());
        double diffY = (ball1.getCenterY() - transformedEnd.getY());

        System.out.println("diffX " + diffX + " diffY " + diffY);

        if(abs(diffX) < .001) diffX = 0;
        if(abs(diffY) < .001) diffY = 0;

        double nMag = sqrt(pow(diffX,2) + pow(diffY,2));

        double nX = diffX/nMag;
        double nY = diffY/nMag;
        System.out.println("nX " + nX + " nY " + nY);

        if(abs(diffX) < .001) nX = 0;
        if(abs(diffY) < .001) nY = 0;

        ball1.setVelX(power*nX);
        ball1.setVelY(power*nY);

    }

    static void updatePath(Ball cb) {
        pathLine.setStartX(cb.getCenterX());
        pathLine.setStartY(cb.getCenterY());
        pathLine.setEndX(pathLine.getStartX() + 800);
        pathLine.setEndY(pathLine.getStartY());
    }

}


//    public void shoot(Ball ball, Line l){
//        double x1 = l.getStartX();
//        double x2 = l.getEndX();
//        double y1 = l.getStartY();
//        double y2 = l.getEndY();
//        Point2D start = new Point2D(x1,y1);
//        Point2D end = new Point2D(x2,y2);
//        double length = start.distance(end);
//        power = length;
//        double velX = ball.getVelX();
//        double velY = ball.getVelY();
//    }




