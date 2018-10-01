package sample;

public abstract class BallBuilder {

    protected Ball ball;

    public Ball getBall() {
        return ball;
    }
    public void createNewBall() {
        ConfigReader cf = new ConfigReader();
        cf.parse("config.json");
        ball = new Cueball(ball.getPosX(), ball.getPosY(), 10, ball.getColor());
    }
    public abstract void buildColor();
    public abstract void buildPosX();
    public abstract void buildPosY();
    public abstract void buildVelX();
    public abstract void buildVelY();
    public abstract void buildMass();
}
