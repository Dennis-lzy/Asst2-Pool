package sample;

public abstract class BallBuilder {

    protected Ball ball;

    public Ball getBall() {
        return ball;
    }
    public void createNewBall() {
        ball = new Cueball();
    };
    public abstract void buildColor();
    public abstract void buildPosX();
    public abstract void buildPosY();
    public abstract void buildVelX();
    public abstract void buildVelY();
    public abstract void buildMass();
}
