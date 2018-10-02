package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class CollisionHandler {



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
        double borderW = 0.056*table.getWidth();
        double borderH = 0.105*table.getHeight();
        double left = table.bounds.getMinX();// + borderW;
        double right = table.bounds.getMaxX();// - borderW;
        //System.out.println("Max X: " + right);
        //System.out.println("Min X: " + left);
        if (abs(right - ball.getPosX()) <= r || abs( ball.getPosX()-left) <= r) {

            return true;
        }
        else
            return false;
    }

    public boolean checkWallCollisionY(Ball ball, Table table) {
        double borderW = 0.056*table.getWidth();
        double borderH = 0.105*table.getHeight();
        double top = table.bounds.getMinY();// + borderH;
        double bot = table.bounds.getMaxY();// - borderH;
        //System.out.println("Max X: " + bot);
        //System.out.println("Min X: " + top);
        if (abs(bot - ball.getPosY()) <= r || abs(ball.getPosY() - top) <= r) {

            return true;
        }
        else
            return false;
    }

          // A quick usage example for the utility you may use to test it is working correctly
//        public static void main(String[] args) {
//            Point2D posA = new Point2D(10, 20);
//            Point2D velA = new Point2D(2, -2);
//            double massA = 1;
//
//            Point2D posB = new Point2D(15, 15);
//            Point2D velB = new Point2D(-2, 2);
//            double massB = 1;
//
//            Pair<Point2D, Point2D> results = calculateCollision(posA, velA, massA, posB, velB, massB);
//
//            System.out.println("A: " + results.getKey());
//            System.out.println("B: " + results.getValue());
//        }

        /*
         * This is an updated collision calculation function for 2 balls colliding in 2D space. You may use it however
         * you wish for your assignment.
         *
         * This uses the optimised physics algorithm discussed here:
         * http://www.gamasutra.com/view/feature/3015/pool_hall_lessons_fast_accurate_.php?page=3
         * which has been converted into Java/JavaFX
         *
         * @param positionA The coordinates of the centre of ball A
         * @param velocityA The delta x,y vector of ball A (how much it moves per tick)
         * @param massA The mass of ball A (for the moment this should always be the same as ball B)
         * @param positionB The coordinates of the centre of ball B
         * @param velocityB The delta x,y vector of ball B (how much it moves per tick)
         * @param massB The mass of ball B (for the moment this should always be the same as ball A)
         *
         * @return A Pair<Point2D, Point2D> in which the first (key) Point2D is the new delta x,y vector for ball A, and the second
         *         (value) Point2D is the new delta x,y vector for ball B.
         */
        public  Pair<Point2D, Point2D> calculateCollision(Ball ballA, Ball ballB) {
            //Point2D positionA, Point2D velocityA, double massA, Point2D positionB, Point2D velocityB, double massB
            Point2D positionA = new Point2D(ballA.getPosX(), ballA.getPosY());
            Point2D velocityA = new Point2D(ballA.getVelX(), ballA.getVelY());
            Point2D positionB = new Point2D(ballB.getPosX(), ballB.getPosY());
            Point2D velocityB = new Point2D(ballB.getVelX(), ballB.getVelY());
            double massA = ballA.getMass();
            double massB = ballB.getMass();

            // Find the angle of the collision - basically where is ball B relative to ball A. We aren't concerned with
            // distance here, so we reduce it to unit (1) size with normalize() - this allows for arbitrary radii
            Point2D collisionVector = positionA.subtract(positionB);
            collisionVector = collisionVector.normalize();

            // Here we determine how 'direct' or 'glancing' the collision was for each ball
            double vA = collisionVector.dotProduct(velocityA);
            double vB = collisionVector.dotProduct(velocityB);

            // If you don't detect the collision at just the right time, balls might collide again before they leave
            // each others' collision detection area, and bounce twice. This stops these secondary collisions by detecting
            // whether a ball has already begun moving away from its pair, and returns the original velocities
            if (vB <= 0 && vA >= 0) {
                return new Pair<>(velocityA, velocityB);
            }

            // This is the optimisation function described in the gamasutra link. Rather than handling the full quadratic
            // (which as we have discovered allowed for sneaky typos) this is a much simpler - and faster - way of obtaining
            // the same results.
            double optimizedP = (2.0 * (vA - vB)) / (massA + massB);

            // Now we apply that calculated function to the pair of balls to obtain their final velocities
            Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
            Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));

            return new Pair<>(velAPrime, velBPrime);
        }
    }



