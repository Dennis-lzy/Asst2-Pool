package sample;

import javafx.scene.shape.Circle;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Collision {

    private double r = 10;

    public boolean checkCollision(Ball ball1, Ball ball2) {
        double X1 = ball1.getCenterX();
        double Y1 = ball1.getCenterY();
        double X2 = ball2.getCenterX();
        double Y2 = ball2.getCenterY();
        if (sqrt(pow(abs(X1-X2),2)+pow(abs(Y1-Y2),2)) <= (2*r)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkWallCollisionX(Ball ball, Table table) {
        if (abs(table.right - ball.getPosX()) <= r || abs(ball.getPosX() - table.left) <= r) {
            return true;
        }
        else
            return false;
    }

    public boolean checkWallCollisionY(Ball ball, Table table) {
        if (abs(table.bottom - ball.getPosY()) <= r || abs(ball.getPosY() - table.top) <= r) {
            return true;
        }
        else
            return false;
    }
}
