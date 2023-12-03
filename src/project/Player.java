package project;

import doctrina.*;
import doctrina.Canvas;
import math.AABB;
import math.Vector2f;

import java.awt.*;

public class Player extends AnimatedEntity {

    private static final String SPRITE_PATH = "images/player2.png";

    private Camera camera;
    private int cooldown = 0;
    protected int maxHealth = 100;
    protected int health = 100;
    protected int damage = 25;

    public boolean xCol = false;
    public boolean yCol = false;
    private MovementController movementController;
    private Screen screen;
    private Camerav2 camerav2;




    public Player(MovementController controller) {
        super(32, 32, 3, SPRITE_PATH);
        position.x = 400;
        position.y = 300;
        worldX = 400;
        worldY = 300;
        setDimension(32, 32);
        setSpeed(1);
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

        cooldown--;
        if (cooldown < 0) {
            cooldown = 0;
        }

        //System.out.println("Position + " + "X" + getPos().getWorldVariables().x + "Y" + getPos().getWorldVariables().y );

    }

    @Override
    public void draw(Canvas canvas) {
        update();


        int cooldownWidth = cooldown * width / 50;
        canvas.drawRectangle((int) position.x, (int) (position.y - 5), cooldownWidth, 2, Color.GREEN);
        if (hasMoved()) {
            drawHitBox(canvas);
        }
        drawPlayerImage(canvas);

        if (GameConfig.isDebugEnabled()) {
            canvas.drawRectangle((int) getPos().x, (int) getPos().y,20,20, Color.RED);
           System.out.println("PLAYER: X: " + getPos().x + "Y: " + getPos().y);
        }
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
