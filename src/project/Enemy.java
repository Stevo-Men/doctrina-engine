package project;

import doctrina.*;
import doctrina.Canvas;
import math.AABB;
import math.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Enemy extends AnimatedEntity {



    private static final String SPRITE_PATH = "images/enemy1.png";
    private int cooldown = 0;

    Camerav2 camerav2;
    Direction enemyDirection;


    public Enemy() {
        super(32, 32, 3, SPRITE_PATH);
        x = 400;
        y = 300;
        setDimension(32, 32);
        setSpeed(3);
        CollidableRepository.getInstance().registerEntity(this);
    }





    public void update() {
        super.update();


            handleAnimationEnemy();

        if (cooldown <= 0) {
            moveEnemy(Direction.UP);

            cooldown = 25;
            System.out.println("PosEnemy X: " + x + "Y: " + y + "Enemy Direction: " + this.getDirection());
        } else {
            cooldown--;
        }



    }








    @Override
    protected void loadAnimationFrames() {
        directionFramesMap.put(Direction.DOWN, loadFrames(0));
        directionFramesMap.put(Direction.LEFT, loadFrames(32));
        directionFramesMap.put(Direction.RIGHT, loadFrames(64));
        directionFramesMap.put(Direction.UP, loadFrames(96));
    }

/*    @Override
    protected void loadAnimationFrames() {
        if (this.getDirection() == Direction.RIGHT) {
            directionFramesMap.put(Direction.RIGHT, loadFrames(64));
        } else if (this.getDirection() == Direction.LEFT) {
            directionFramesMap.put(Direction.LEFT, loadFrames(32));
        } else if (this.getDirection() == Direction.DOWN) {
            directionFramesMap.put(Direction.DOWN, loadFrames(0));
        } else if (this.getDirection() == Direction.UP) {
            directionFramesMap.put(Direction.UP, loadFrames(96));
        }
    }*/


    @Override
    public void draw(Canvas canvas) {
        update();
        //canvas.drawRectangle(this,Color.WHITE);
        drawEnemyImage(canvas);

    }


}
