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
        move(playerDirection);
        if (x >= 820) {
            x = -20;
        }
        if (y >= 620) {
            y = -20;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.YELLOW);
    }

    private void initialize(Player player) {
        if (playerDirection == Direction.RIGHT) {
            teleport(player.getX() + player.getWidth() + 1,
                    player.getY() + 15 - 2);
            setDimension(8, 4);
        } else if (playerDirection == Direction.LEFT) {
            teleport(player.getX() - 9, player.getY() + 15 - 2);
            setDimension(8, 4);
        } else if (playerDirection == Direction.DOWN) {
            teleport(player.getX() + 15 - 2,
                    player.getY() + player.getHeight() + 1);
            setDimension(4, 8);
        } else if (playerDirection == Direction.UP) {
            teleport(player.getX() + 15 - 2, player.getY() - 9);
            setDimension(4, 8);
        }
    }
}
