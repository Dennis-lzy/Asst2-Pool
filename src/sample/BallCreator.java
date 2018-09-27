package sample;

public class BallCreator {
    private BallBuilder ballBuilder;

    public void setBallBuilder(BallBuilder bb){
        ballBuilder = bb;
    }

    public Ball getBall() {
        return ballBuilder.getBall();
    }

    public void contrsuctBall() {
        ballBuilder.createNewBall();
        ballBuilder.buildColor();
        ballBuilder.buildPosX();
        ballBuilder.buildPosY();
        ballBuilder.buildVelX();
        ballBuilder.buildVelY();
        ballBuilder.buildMass();
    }
}
