package doctrina;

import math.AABB;
import math.Vector2f;
import project.Enemy;
import project.GamePad;
import project.Player;
import project.World;

import java.awt.*;

public class Camerav2 extends StaticEntity {

    private AABB collisionCam;
    private Player player;
    private  Direction playerDirection;
    int screenWidth,  screenHeight;



    public Camerav2(AABB collisionCam) {
        this.collisionCam = collisionCam;
        screenWidth = 850;
        screenHeight = 530;
        setLimit(screenWidth, screenHeight);

    }

    public void setLimit(int widthLimit, int heightLimit) {
        collisionCam.setWidth(widthLimit);
        collisionCam.setHeight(heightLimit);
    }

    public Vector2f getPos() {
        return collisionCam.getPosition();
    }

    public void update(Player player) {
        playerDirection = player.getDirection();

       // target(player);

        if (player.hasMoved()  ) {
            switch (playerDirection) {
                case RIGHT:
                    collisionCam.getPosition().x++;
                    break;
                case LEFT:
                    collisionCam.getPosition().x--;
                    break;
                case DOWN:
                    collisionCam.getPosition().y++;
                    break;
                case UP:
                    collisionCam.getPosition().y--;
                    break;
            }
        }

    }

    public void draw(Canvas canvas) {
       update(player);
    }
}

