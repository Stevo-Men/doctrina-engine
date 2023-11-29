package project;

import doctrina.Canvas;
import doctrina.CollidableRepository;
import doctrina.MovableEntity;
import doctrina.StaticEntity;

import java.awt.*;

public class Enemy2 extends StaticEntity {

    private int cooldown = 0;

    MovableEntity movableEntity;

    public Enemy2(int x, int y) {
        setDimension(32, 32);

        this.x = x;
        this.y = y;
        CollidableRepository.getInstance().registerEntity(this);
    }



    public void update() {


        // Implement enemy behavior here
        // For example, you might make the enemy move left and right:
        if (cooldown <= 0) {
            x = x+10; // Move right
            cooldown = 50; // Set a cooldown before changing direction again
        } else {
            cooldown--;
        }
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(x,y, 20,20,Color.GREEN);
    }


}
