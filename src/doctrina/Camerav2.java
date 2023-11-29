package doctrina;

import math.AABB;
import math.Vector2f;
import project.Player;
import project.World;

import java.awt.*;

public class Camerav2  {

    private AABB collisionCam;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private float dx;
    private float dy;
    private float maxSpeed = 8f;
    private float acceleration = 3f;
    private float deacceleration = 0.3f;
    private int widthLimit;
    private int heightLimit;

    private int tileSize = 64;

    private Player player;
    private Screen screen;
    private World world;

    public Camerav2(AABB collisionCam) {
        this.collisionCam = collisionCam;
    }

    public void setLimit(int widthLimit, int heightLimit) {
        this.widthLimit = widthLimit;
        this.heightLimit = heightLimit;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public Player getTarget() {
        return player;
    }

    public Vector2f getPos() {
        return collisionCam.getPosition();
    }

    public AABB getBounds() {
        return collisionCam;
    }

    public void update(Player player) {
        float cameraSpeed = 0.5f;  // Adjust the speed as needed

        // Interpolate the camera's position towards the player's position
        collisionCam.getPosition().x += (player.worldX*3 - collisionCam.getPosition().x) * cameraSpeed;
        collisionCam.getPosition().y += (player.worldY*3 - collisionCam.getPosition().y) * cameraSpeed;

        System.out.println("Position + " + "X" + collisionCam.getPosition().x + "Y" + collisionCam.getPosition().y);
    }


    private void move() {
        if (up) {
            dy -= acceleration;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deacceleration;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (down) {
            dy += acceleration;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deacceleration;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (left) {
            dx -= acceleration;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacceleration;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (right) {
            dx += acceleration;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacceleration;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    public void target(Player player) {
        this.player = player;
        if (player != null) {
            acceleration = player.getAcceleration();
            deacceleration = player.getDeacceleration();
            maxSpeed = player.getMaxSpeed();
        } else {
            acceleration = 3;
            deacceleration = 0.3f;
            maxSpeed = 8;
        }
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }



    public void draw(Canvas canvas) {


    }
}
