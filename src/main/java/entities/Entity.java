package entities;

public abstract class Entity {

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
