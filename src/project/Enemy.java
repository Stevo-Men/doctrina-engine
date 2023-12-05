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
    public int life;
    public  int screenX;
    public  int screenY;
    public int worldX,worldY;


    public Enemy(int x, int y) {
        super(32, 32, 3, SPRITE_PATH);
        worldX = x;
        worldY = y;
        life = 100;
        setDimension(32, 32);
        setSpeed(3);
        CollidableRepository.getInstance().registerEntity(this);
        bounds.setWidth(32);
        bounds.setHeight(32);
    }





    public void update(Player player, Camerav2 camerav2) {
        super.update();
        screenX = (int) (worldX - player.worldX + player.screenX + camerav2.getPos().x);
        screenY = (int) (worldY - player.worldY + player.screenY + camerav2.getPos().y);


        handleAnimationEnemy();


            if (cooldown <= 0) {

                chase(player);
                cooldown = 10;
                System.out.println("Enemy WorldX: " + worldX + "Y: " + worldY + "Enemy ScreenX:" + screenX + "ScreenY" + screenY);

            } else {
                cooldown--;
            }



    }


    public void chase(Player player) {

        float enemyX = this.worldX;
        float enemyY = this.worldY;

        float playerX = player.worldX;
        float playerY = player.worldY;

        float dx = playerX - enemyX;
        float dy = playerY - enemyY;

        double angle = Math.atan2(dy, dx);

        if (angle >= -Math.PI / 4 && angle < Math.PI / 4) {
             move(Direction.RIGHT);
        } else if (angle >= Math.PI / 4 && angle < 3 * Math.PI / 4) {
            move(Direction.DOWN);
        } else if ((angle >= 3 * Math.PI / 4 && angle <= Math.PI) || (angle >= -Math.PI && angle < -3 * Math.PI / 4)) {
            move(Direction.LEFT);
        } else {
            move(Direction.UP);
        }
    }

        public void getShoot() {
            life -= 50;
        }

    @Override
    protected void loadAnimationFrames() {
        directionFramesMap.put(Direction.DOWN, loadFrames(0));
        directionFramesMap.put(Direction.LEFT, loadFrames(32));
        directionFramesMap.put(Direction.RIGHT, loadFrames(64));
        directionFramesMap.put(Direction.UP, loadFrames(96));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this.worldX,this.worldY,64,64,Color.ORANGE);
        drawEnemyImage(canvas,this);
        drawHealthBar(canvas);
        canvas.drawString("World: X: " + worldX + "Y: " + worldY,worldX,worldY,Color.ORANGE);
    }


    public void drawHealthBar(Canvas canvas) {
        canvas.drawRectangle(x,y-5,33,2,Color.RED);
        canvas.drawRectangle(x, y - 5, life/3, 2, Color.GREEN);
    }
}
