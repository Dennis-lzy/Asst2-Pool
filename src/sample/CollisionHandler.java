package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class CollisionHandler {

    private double radius = 10;

    //handles collisions modified from provided collision code

    public void handleCollisions(ArrayList<Ball> balls){
        double xDist, yDist;
        for(int i = 0; i < balls.size(); i++){
            Ball A = balls.get(i);
            for(int j = i+1; j < balls.size(); j++){
                Ball B = balls.get(j);
                xDist = A.getCenterX() - B.getCenterX();
                yDist = A.getCenterY() - B.getCenterY();
                double distSquared = xDist*xDist + yDist*yDist;
                //checks for intersection of balls
                if(distSquared <= (2*radius)*(2*radius)){
                    Point2D positionA = new Point2D(A.getPosX(), A.getPosY());
                    Point2D velocityA = new Point2D(A.getVelX(), A.getVelY());
                    Point2D positionB = new Point2D(B.getPosX(), B.getPosY());
                    Point2D velocityB = new Point2D(B.getVelX(), B.getVelY());
                    double massA = A.getMass();
                    double massB = B.getMass();

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
                        velocityA = new Point2D(A.getVelX(), A.getVelY());
                        velocityB = new Point2D(B.getVelX(), B.getVelY());

                    }

                    // This is the optimisation function described in the gamasutra link. Rather than handling the full quadratic
                    // (which as we have discovered allowed for sneaky typos) this is a much simpler - and faster - way of obtaining
                    // the same results.
                    double optimizedP = (2.0 * (vA - vB)) / (massA + massB);

                    // Now we apply that calculated function to the pair of balls to obtain their final velocities
                    Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
                    Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));

                    double newAVelX = velAPrime.getX();
                    double newAVelY = velAPrime.getY();
                    double newBVelX = velBPrime.getX();
                    double newBVelY = velBPrime.getY();

                    A.setVelX(newAVelX);
                    A.setVelY(newAVelY);
                    B.setVelX(newBVelX);
                    B.setVelY(newBVelY);

                    }
                }
            }
        }




    //checks collision with left and right of table
    public boolean checkWallCollisionX(Ball ball, Table table) {
        double left = table.bounds.getMinX();// + borderW;
        double right = table.bounds.getMaxX();// - borderW;
        //System.out.println("Max X: " + right);
        //System.out.println("Min X: " + left);
        if (abs(right - ball.getPosX()) <= radius || abs( ball.getPosX()-left) <= radius) {

            return true;
        }
        else
            return false;
    }

    //checks collision with top and bottom of table
    public boolean checkWallCollisionY(Ball ball, Table table) {
        double top = table.bounds.getMinY();
        double bot = table.bounds.getMaxY();
        //System.out.println("Max X: " + bot);
        //System.out.println("Min X: " + top);
        if (abs(bot - ball.getPosY()) <= radius || abs(ball.getPosY() - top) <= radius) {

            return true;
        }
        else
            return false;
    }


    }



