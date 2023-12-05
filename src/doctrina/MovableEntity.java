package doctrina;

import math.Vector2f;

import java.awt.*;

public abstract class MovableEntity extends StaticEntity {

    private int speed = 1;
    private Direction direction = Direction.UP;
    private  Collision collision;
    public int lastX = Integer.MAX_VALUE;
    public int lastY = Integer.MIN_VALUE;
    public int lastEnemyX = Integer.MAX_VALUE;
    public int lastEnemyY = Integer.MIN_VALUE;
    private boolean moved = false;
    public int worldX;
    public int worldY;



    protected float maxSpeed = 4f;
    protected float acceleration = 2f;
    protected float deacceleration = 0.3f;
    protected float force = 25f;






    public void update() {
        moved = false;
    }



    public MovableEntity() {
        collision = new Collision(this);
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void move() {
        int allowedSpeed = collision.getAllowedSpeed(direction);
        this.worldX += direction.calculateVelocityX(allowedSpeed);
        this.worldY += direction.calculateVelocityY(allowedSpeed);
        moved = (worldX != lastX || worldY != lastY);
        lastX = worldX;
        lastY = worldY;
    }

    public void moveEnemy() {
        int allowedSpeed = collision.getAllowedSpeed(direction);
        worldX += direction.calculateVelocityX(allowedSpeed);
        worldY += direction.calculateVelocityY(allowedSpeed);
        moved = (worldX != lastEnemyX || worldY != lastEnemyY);
        lastEnemyX = worldX;
        lastEnemyY = worldY;
    }



    public boolean hasMoved() {
        return moved;
    }

    public void move(Direction direction) {
        this.direction = direction;
        move();
   }

    public void moveEnemy(Direction direction) {
        this.direction = direction;
        moveEnemy();
    }

    public void moveUp() {
        move(Direction.UP);
    }

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    public Rectangle getHitBox() {
        return switch (direction) {
            case UP -> getUpperHitBox();
            case DOWN -> getLowerHitBox();
            case LEFT -> getLeftHitBox();
            case RIGHT -> getRightHitBox();
        };
    }






    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void setAcceleration(float f) { acceleration = f; }
    public void setDeacceleration(float f) { deacceleration = f; }

    public float getDeacceleration() { return deacceleration; }
    public float getAcceleration() { return acceleration; }
    public float getMaxSpeed() { return maxSpeed; }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean hitBoxIntersectWith(StaticEntity other) {
        if (other == null) {
            return false;
        }
        return getHitBox().intersects(other.getBounds());
    }

    public void drawHitBox(Canvas canvas) {
        Rectangle rectangle = getHitBox();
        Color color = new Color(255, 0, 0, 200);
        canvas.drawRectangle(rectangle.x, rectangle.y,
                rectangle.width, rectangle.height, color);
    }



    private Rectangle getUpperHitBox() {
        return new Rectangle(x, y - speed, width, speed);
    }

    private Rectangle getLowerHitBox() {
        return new Rectangle(x, y + height, width, speed);
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle(x - speed, y, speed, height);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y, speed, height);
    }
}