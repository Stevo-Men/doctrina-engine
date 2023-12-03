package doctrina;
import math.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AnimatedEntity extends MovableEntity {

    protected Map<Direction, Image[]> directionFramesMap = new HashMap<>();
    protected BufferedImage spriteSheet;
    protected static final int ANIMATION_SPEED = 8;
    protected int currentAnimationFrame = 1;
    protected int nextFrame = ANIMATION_SPEED;
    protected int worldX;
    protected int worldY;
    protected int cooldown = 0;


    protected int width;
    protected int height;

    public AnimatedEntity(int width, int height, int speed,  String spritePath) {
        super();
        this.width = width;  // Set width
        this.height = height;  // Set height
        //position = new Vector2f(this.position.x, this.position.y);
        loadSpriteSheet(spritePath);
        loadAnimationFrames();
    }

    protected void loadSpriteSheet(String spritePath) {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(spritePath));
        } catch (IOException e) {
            System.out.println("Error loading sprite sheet: " + e.getMessage());
        }
    }

    protected abstract void loadAnimationFrames();

    protected Image[] loadFrames(int startY) {
        Image[] frames = new Image[3];
        frames[0] = spriteSheet.getSubimage(0, startY, width, height);
        frames[1] = spriteSheet.getSubimage(32, startY, width, height);
        frames[2] = spriteSheet.getSubimage(64, startY, width, height);
        return frames;
    }

    protected void handleAnimation() {
        if (hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= directionFramesMap.get(getDirection()).length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1;
        }
    }

    protected void handleAnimationEnemy() {
        if (!hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= directionFramesMap.get(getDirection()).length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED*2;
            }
        } else {
            currentAnimationFrame = 1;
        }
    }



    protected void drawPlayerImage(Canvas canvas) {
        Direction direction = getDirection();
        Image[] frames = directionFramesMap.get(direction);


        if (frames != null) {
            canvas.drawImage(frames[currentAnimationFrame], (int) this.position.x, (int) this.position.y);
          //  System.out.println("Player Coordinates: x = " + (int) this.position.x + ", y = " + (int) this.position.y);

        }
    }

    protected void drawEnemyImage(Canvas canvas) {
        Direction direction = getDirection();
        Image[] frames = directionFramesMap.get(direction);


        if (frames != null) {
            canvas.drawImage(frames[currentAnimationFrame],  this.x,  this.y);
            //  System.out.println("Player Coordinates: x = " + (int) this.position.x + ", y = " + (int) this.position.y);

        }
    }
}
