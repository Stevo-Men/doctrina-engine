package project;

import doctrina.Canvas;
import doctrina.CollidableRepository;
import doctrina.Direction;
import doctrina.MovableEntity;

import java.awt.*;

public class Bullet extends MovableEntity {

    private final Direction playerDirection;


    public Bullet(Player player) {
        setSpeed(5);
        playerDirection = player.getDirection();
        initialize(player);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        if (playerDirection == Direction.RIGHT) {
            x += getSpeed();
        } else if (playerDirection == Direction.LEFT) {
            x -= getSpeed();
        } else if (playerDirection == Direction.DOWN) {
            y += getSpeed();
        } else if (playerDirection == Direction.UP) {
            y -= getSpeed();
        }


        if (x >= 820 || x < 0) {
            x = -20;
        }
        if (y >= 620 || y < 0) {
            y = -20;
        }
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.YELLOW);
    }

    private void initialize(Player player) {
        if (playerDirection == Direction.RIGHT) {
            teleport((int) player.position.x + player.getWidth() + 1,
                    (int) player.position.y + 15 - 2);
            setDimension(8, 4);
        } else if (playerDirection == Direction.LEFT) {
            teleport((int) player.position.x - 9, (int) player.position.y + 15 - 2);
            setDimension(8, 4);
        } else if (playerDirection == Direction.DOWN) {
            teleport((int) player.position.x + 15 - 2,
                    (int) player.position.y + player.getHeight() + 1);
            setDimension(4, 8);
        } else if (playerDirection == Direction.UP) {
            teleport((int) player.position.x + 15 - 2, (int) player.position.y - 9);
            setDimension(4, 8);
        }
    }

    public void enableDebug() {
        System.out.println("Player Direction: " + playerDirection);
    }


}


