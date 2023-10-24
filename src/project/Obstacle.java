package project;

import doctrina.Canvas;
import doctrina.CollidableRepository;
import doctrina.StaticEntity;

import java.awt.*;

public class Obstacle extends StaticEntity {

    public Obstacle(int x, int y) {
        setDimension(16, 16);
        teleport(x, y);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.WHITE);
    }
}
