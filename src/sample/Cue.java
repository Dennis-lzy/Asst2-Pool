package sample;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Cue extends Line {

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

    //sets power based on how far you drag
    public void setPower(Line line){
        Point2D start = new Point2D(line.getStartX(), line.getStartY());
        Point2D end = new Point2D(line.getEndX(), line.getEndY());
        power = start.distance(end)*3;
        if(power>900){
            power=900;
        }
    }

    //shoots ball
    void shoot(Ball A, Line B) {
        double x1 = A.getPosX();
        double x2 = B.getEndX();
        double y1 = A.getPosY();
        double y2 = B.getEndY();

        double velX = -(x2-x1)/40;
        double velY = -(y2-y1)/40;

        if(A.getVelX() ==0 && A.getVelY()==0) {
            A.setVelX(velX);
            A.setVelY(velY);
        }


    }

    static void updatePath(Ball cb) {
        pathLine.setStartX(cb.getCenterX());
        pathLine.setStartY(cb.getCenterY());
        pathLine.setEndX(pathLine.getStartX() + 800);
        pathLine.setEndY(pathLine.getStartY());
    }

}






