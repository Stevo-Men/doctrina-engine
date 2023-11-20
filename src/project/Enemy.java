package project;

import doctrina.Canvas;
import doctrina.*;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Enemy extends MovableEntity {

    private static final String SPRITE_PATH = "images/Skeleton.png";
    private static final int ANIMATION_SPEED = 8;
    private Map<Direction, Image[]> directionFramesMap = new HashMap<>();
    private BufferedImage spriteSheet;

    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private int worldX;
    private int worldY;
    private int initialX,initialY;
    private int cooldown = 0;
    private Camera camera;

    public Enemy(int initialX, int initialY) {
        setDimension(32, 32);
        setSpeed(3);
        load();

        this.x = initialX;
        this.y = initialY;
        this.worldX = x;
        this.worldY = y;
        camera = new Camera(this, 100, 100);
        teleport(initialX, initialY);
    }


    public boolean canAttack() {
        return cooldown == 0;
    }

    @Override
    public void update() {
        super.update();
        worldX = x;
        worldY = y;
        handleAnimation();
      //  System.out.println("Position + " + x + y );
    }

    @Override
    public void draw(Canvas canvas) {
        drawEnemyInfo(canvas);
        int cooldownWidth = cooldown * width / 50;
        canvas.drawRectangle(x, y - 5, cooldownWidth, 2, Color.GREEN);
        if (hasMoved()) {
            drawHitBox(canvas);
        }
        drawEnemyImage(canvas);
    }

    private void load() {
        loadSpriteSheet();
      //  loadAnimationFrames();
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println("Error loading sprite sheet: " + e.getMessage());
        }
    }

    private void loadAnimationFrames() {
        directionFramesMap.put(Direction.DOWN, loadFrames(0));
        directionFramesMap.put(Direction.LEFT, loadFrames(32));
        directionFramesMap.put(Direction.RIGHT, loadFrames(64));
        directionFramesMap.put(Direction.UP, loadFrames(96));
    }

    private Image[] loadFrames(int startY) {
        Image[] frames = new Image[3];
        frames[0] = spriteSheet.getSubimage(0, startY, width, height);
        frames[1] = spriteSheet.getSubimage(32, startY, width, height);
        frames[2] = spriteSheet.getSubimage(64, startY, width, height);
        return frames;
    }

    private void handleAnimation() {
        if (hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= directionFramesMap.get(Direction.LEFT).length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1;
        }
    }

    private void drawEnemyInfo(Canvas canvas) {
        canvas.drawRectangle(this, Color.GREEN);
    }

    private void drawEnemyImage(Canvas canvas) {
        Direction direction = getDirection();
        Image[] frames = directionFramesMap.get(direction);

        if (frames != null) {
            canvas.drawImage(frames[currentAnimationFrame], x, y);
        }
    }
}
