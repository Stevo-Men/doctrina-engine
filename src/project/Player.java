package project;

import doctrina.*;
import doctrina.Canvas;
import math.AABB;
import math.Vector2f;

import java.awt.*;

public class Player extends AnimatedEntity {

    private static final String SPRITE_PATH = "images/player2.png";
    private int cooldown = 0;
    protected final int maxHealth = 100;
    protected int health = 100;
    public final int screenX;
    public final int screenY;
    private MovementController movementController;



    public Player(MovementController controller) {
        super(32, 32, 3, SPRITE_PATH);
        worldX = 1500;
        worldY = 1500;
        screenX = 400;
        screenY = 300;


        setDimension(32, 32);
        setSpeed(1);
        this.movementController = controller;
        bounds.setWidth(32);
        bounds.setHeight(32);
        bounds.setXOffset(16);
        bounds.setYOffset(40);

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

        cooldown--;
        if (cooldown < 0) {
            cooldown = 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        update();
        cooldownBar(canvas);
        drawPlayerImage(canvas, this);


        if (GameConfig.isDebugEnabled()) {
            debugInfo(canvas);
        }
        canvas.drawString("World: X: " + worldX + "Y: " + worldY,screenX,screenY,Color.RED);
    }

    private void moveWithController() {
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

    private void debugInfo(Canvas canvas) {
        canvas.drawRectangle((int) getPos().x, (int) getPos().y,20,20, Color.RED);
        System.out.println("World: X: " + worldX + "Y: " + worldY);
        System.out.println("Screen: X: " + screenX + "Y: " + screenY);
    }

    private void cooldownBar(Canvas canvas) {
        int cooldownWidth = cooldown * width / 50;
        canvas.drawRectangle(screenX, (screenY - 5), cooldownWidth, 2, Color.GREEN);
    }

}
