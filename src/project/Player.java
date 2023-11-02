package project;

import doctrina.Canvas;
import doctrina.*;
import tank.Missile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {
    ///
    private static final String SPRITE_PATH = "images/player2.png";
    private static final int ANIMATION_SPEED = 8;

    private BufferedImage spriteSheet;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    ///

    private Camera camera;

    private int cooldown = 0;
    public int worldX, worldY;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        load();
        teleport(100, 100);
        worldX = 1;
        worldY = 1;
       camera = new Camera(this,100,100);

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
        camera.update();
        moveWithController();
        if (hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= leftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {currentAnimationFrame = 1;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.GREEN);
        int cooldownWidth = cooldown * width / 50;
        canvas.drawRectangle(x, y - 5, cooldownWidth, 2, Color.GREEN);
        if (hasMoved()) {
            drawHitBox(canvas);
        }

        if (getDirection() == Direction.RIGHT) {
            canvas.drawImage(rightFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            canvas.drawImage(leftFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.UP) {
            canvas.drawImage(upFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.DOWN) {
            canvas.drawImage(downFrames[currentAnimationFrame], x, y);
        }
        if (GameConfig.isDebugEnabled()) {
            drawHitBox(canvas);
            drawCamera(canvas);
        }


    }

    private void load() {
        loadSpriteSheet();
        loadAnimationFrames();
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationFrames() {
        downFrames = new Image[3];
        downFrames[0] = spriteSheet.getSubimage(0, 0, width, height);
        downFrames[1] = spriteSheet.getSubimage(32, 0, width, height);
        downFrames[2] = spriteSheet.getSubimage(64, 0, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(0, 32, width, height);
        leftFrames[1] = spriteSheet.getSubimage(32, 32, width, height);
        leftFrames[2] = spriteSheet.getSubimage(64, 32, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(0, 64, width, height);
        rightFrames[1] = spriteSheet.getSubimage(32, 64, width, height);
        rightFrames[2] = spriteSheet.getSubimage(64, 64, width, height);

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(0, 96, width, height);
        upFrames[1] = spriteSheet.getSubimage(32, 96, width, height);
        upFrames[2] = spriteSheet.getSubimage(64, 96, width, height);
    }
}