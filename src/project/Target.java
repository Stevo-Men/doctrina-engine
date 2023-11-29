package project;

import doctrina.Blockade;
import doctrina.Canvas;
import doctrina.CollidableRepository;
import doctrina.StaticEntity;

import java.awt.*;

public class Target extends StaticEntity {

    private Blockade blockade;

    public Target(int x, int y) {
        teleport(x, y);
        CollidableRepository.getInstance().registerEntity(this);
    }


    public void blockade() {
        blockade.teleport(x+16,y+16);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(x,y,width,height,Color.RED);
    }
}
