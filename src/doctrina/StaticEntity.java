package doctrina;

import math.AABB;
import math.Vector2f;

import java.awt.*;

public abstract class StaticEntity {

    public int x;
    public int y;
    protected int width;
    protected int height;
    protected int size;
    public Vector2f position;
    public AABB bounds;
    protected boolean teleported = false;


    public abstract void draw(Canvas canvas);

    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
        bounds = new AABB(this.position, width, height);
    }

    public boolean intersectWith(StaticEntity other) {
        return getBounds().intersects(other.getBounds());
    }

    protected Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() { return size; }
    public Vector2f getPos() { return position; }

    public void setPosition(Vector2f pos) {
        this.position = position;
        this.bounds = new AABB(pos, size, size);
        teleported = true;
    }
}
