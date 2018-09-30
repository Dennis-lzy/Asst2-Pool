package sample;

public abstract class GameObjectCreation {
    protected abstract GameObject makeGameObject(String type);

    public GameObject createGameObject(String type) {
        GameObject obj = makeGameObject(type);

        return obj;
    }


}
