package doctrina;

import math.AABB;
import math.Vector2f;
import project.GamePad;
import project.Player;
import project.World;

import java.awt.*;

public class Camerav2 extends StaticEntity {

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
    private GamePad gamePad;
    private  Direction playerDirection;






    public Camerav2(AABB collisionCam) {
        this.collisionCam = collisionCam;

        screen = new Screen();

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


    public void findPlayer(Player player) {
        collisionCam.getPosition().x = player.position.x;
        collisionCam.getPosition().y = player.position.y;
        System.out.println("CollisonCam X: " + collisionCam.getPosition().x + " Y: " + collisionCam.getPosition().y + "Player X :" + player.position.x + " Y: " + player.position.y);
    }



    public void update(Player player) {
        playerDirection = player.getDirection();

        if (player.hasMoved()) {
            switch (playerDirection) {
                case RIGHT:
                    collisionCam.getPosition().x  += 1;
                    player.setPosition((int) (collisionCam.getPosition().x + 400), (int) (collisionCam.getPosition().y +300));
                    break;
                case LEFT:
                    collisionCam.getPosition().x  -= 1;
                    player.setPosition((int) (collisionCam.getPosition().x + 400), (int) (collisionCam.getPosition().y +300));
                    break;
                case DOWN:
                    collisionCam.getPosition().y  += 1;
                    player.setPosition((int) (collisionCam.getPosition().x + 400), (int) (collisionCam.getPosition().y +300));
                    break;
                case UP:
                    collisionCam.getPosition().y  -= 1;
                    player.setPosition((int) (collisionCam.getPosition().x + 400), (int) (collisionCam.getPosition().y +300));
                    break;
            }
//            if (collisionCam.getPosition().x != player.position.x +) {
//                collisionCam.getPosition().x = player.position.x;
//            }

            System.out.println("CollisonCam X: " + collisionCam.getPosition().x + " Y: " + collisionCam.getPosition().y + "Player X :" + player.position.x + " Y: " + player.position.y);
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

    public void setPos(float x, float y) {
        collisionCam.getPosition().setVector(x , y );
    }







    public void draw(Canvas canvas) {
        if (player != null) {
            Direction direction = player.getDirection();
            update(player);
        }

        Color color = new Color(255, 0, 0, 113);
        canvas.drawRectangle((int) collisionCam.getPosition().x, (int) collisionCam.getPosition().y, screen.getScreenWidth(), screen.getScreenHeight(), color);
    }



}


//     float cameraSpeed = 0.5f;  // Adjust the speed as needed
// Interpolate the camera's position towards the player's position
//        collisionCam.getPosition().x += (player.worldX*3 - collisionCam.getPosition().x) * cameraSpeed;
//        collisionCam.getPosition().y += (player.worldY*3 - collisionCam.getPosition().y) * cameraSpeed;

    /*   private void move() {
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
    }*/