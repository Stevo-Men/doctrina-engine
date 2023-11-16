package project;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class Player extends AnimatedEntity {

    private static final String SPRITE_PATH = "images/player2.png";

    private Camera camera;
    private int cooldown = 0;
    private MovementController movementController;

    public Player(MovementController controller) {
        super(32, 32, 2, SPRITE_PATH);
        setDimension(32, 32);
        setSpeed(2);
        worldX = 400;
        worldY = 300;
        camera = new Camera(this, 100, 100);
        this.movementController = controller;
    }

    public Bullet fire() {
        cooldown = 50;
        return new Bullet(this);
    }

    public boolean canFire() {
        return cooldown == 0;
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        handleAnimation();
    }

    @Override
    public void draw(Canvas canvas) {
        drawPlayerInfo(canvas);
        int cooldownWidth = cooldown * width / 50;
        canvas.drawRectangle(x, y - 5, cooldownWidth, 2, Color.GREEN);
        if (hasMoved()) {
            drawHitBox(canvas);
        }
        drawPlayerImage(canvas);
    }

    private void moveWithController() {
        // Update the player's position based on the controller's input
        if (movementController.isRightPressed()) {
            move(Direction.RIGHT);
        } else if (movementController.isLeftPressed()) {
            move(Direction.LEFT);
        }

        if (movementController.isDownPressed()) {
            move(Direction.DOWN);
        } else if (movementController.isUpPressed()) {
            move(Direction.UP);
        }
    }

    @Override
    protected void loadAnimationFrames() {
        directionFramesMap.put(Direction.DOWN, loadFrames(0));
        directionFramesMap.put(Direction.LEFT, loadFrames(32));
        directionFramesMap.put(Direction.RIGHT, loadFrames(64));
        directionFramesMap.put(Direction.UP, loadFrames(96));
    }

}
