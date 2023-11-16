package doctrina;

import java.awt.*;

public abstract class MovableEntity extends StaticEntity {

    private int speed = 1;
    private Direction direction = Direction.UP;
    private  Collision collision;
    public int lastX = Integer.MAX_VALUE;
    public int lastY = Integer.MIN_VALUE;
    private boolean moved = false;
    private ControllableEntity entity;
    private Camera camera;





    public void update() {
        moved = false;

        Camera camera = getCamera();
        camera.update();
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
        x += direction.calculateVelocityX(allowedSpeed);
        y += direction.calculateVelocityY(allowedSpeed);
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
    }



    public boolean hasMoved() {
        return moved;
    }

    public void move(Direction direction) {
        this.direction = direction;
        move();
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
        switch (direction) {
            case UP: return getUpperHitBox();
            case DOWN: return getLowerHitBox();
            case LEFT: return getLeftHitBox();
            case RIGHT: return getRightHitBox();
        }
        return getBounds();
    }

  public Camera getCamera() {
        camera = new Camera(entity, width, height );
       return camera;
    }




    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

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

    public void drawCamera(Canvas canvas) {
        Camera camera = getCamera();
        Color color = new Color(255, 0, 0, 200);
        canvas.drawCamera(camera, width, height, color);
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