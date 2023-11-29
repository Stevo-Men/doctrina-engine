package doctrina;

import doctrina.*;



import math.*;

import java.awt.*;

/*
public abstract class GameObject {

    protected SpriteSheet sprite;
    protected Sprite image;
    protected AABB bounds;
    protected Vector2f pos;
    protected int size;
    protected int spriteX;
    protected int spriteY;

    // used for moving objects like boxes and such
    protected float dx;
    protected float dy;

    protected float maxSpeed = 4f;
    protected float acceleration = 2f;
    protected float deacceleration = 0.3f;
    protected float force = 25f;

    protected boolean teleported = false;
	//protected TileCollision tc;
	protected String name = "";

    public GameObject(SpriteSheet sprite, Vector2f origin, int spriteX, int spriteY, int size) {
        this(origin, size);
        this.sprite = sprite;
    }

    public GameObject(Sprite image, Vector2f origin, int size) {
        this(origin, size);
        this.image = image;
	}

	private GameObject(Vector2f origin, int size) {
		this.bounds = new AABB(origin, size, size);
		this.pos = origin;
		this.size = size;
	}

    public void setPos(Vector2f pos) {
        this.pos = pos;
        this.bounds = new AABB(pos, size, size);
        teleported = true;
    }

    public Sprite getImage() { return image; }

	public void setName(String name) { this.name = name; }
    public void setSprite(SpriteSheet sprite) { this.sprite = sprite; }
    public void setSize(int i) { size = i; }
    public void setMaxSpeed(float f) { maxSpeed = f; }
    public void setAcc(float f) { acceleration = f; }
    public void setDeacceleration(float f) { deacceleration = f; }

    public float getDeacceleration() { return deacceleration; }
    public float getAcceleration() { return acceleration; }
    public float getMaxSpeed() { return maxSpeed; }
    public float getDx() { return dx; }
    public float getDy() { return dy; }
    public AABB getBounds() { return bounds; }
    public Vector2f getPos() { return pos; }
    public int getSize() { return size; }

    public void addForce(float a, boolean vertical) {
        if(!vertical) {
            dx -= a;
        } else {
            dy -= a;
        }
    }

    public void update() {

    }

    public void render(Graphics2D g) {
        g.drawImage(image.image, (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
	}

	public String toString() {
		return "$" + name;
	}

}*/
