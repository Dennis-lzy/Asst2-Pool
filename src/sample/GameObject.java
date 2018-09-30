package sample;

public abstract class GameObject {

    private Type type;
    protected double posX, posY;

    public GameObject(double x, double y, Type type){
        this.posX = x;
        this.posY = y;
        this.type = type;

    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public abstract void renderObject();
}
