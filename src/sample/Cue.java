package sample;

import javafx.geometry.Bounds;
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


    public void setPower(Line line){
        Point2D start = new Point2D(line.getStartX(), line.getStartY());
        Point2D end = new Point2D(line.getEndX(), line.getEndY());
        power = start.distance(end)*3;
        if(power>900){
            power=900;
        }
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

    void shoot(Ball A, Line B) {
        double x1 = A.getPosX();
        double x2 = B.getEndX();
        double y1 = A.getPosY();
        double y2 = B.getEndY();

        double velX = -(x2-x1)/70;
        double velY = -(y2-y1)/70;

        A.setVelX(velX);
        A.setVelY(velY);


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




