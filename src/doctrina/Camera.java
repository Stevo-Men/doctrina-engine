package doctrina;

import project.Player;

import java.awt.*;

// À partir  du modèle de Gab Laroche

public class Camera extends StaticEntity {

    private MovableEntity movableEntity;
    public int lastX;
    public int lastY;
    private RenderingEngine renderingEngine;
    private Screen screen;
    private Player player;


    public void setX(int x) {
        this.x = x;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Camera(MovableEntity movableEntity, int width, int height) {
        this.width = width;
        this.height = height;
        this.movableEntity = movableEntity;
    }


    public void drawCamera(Canvas canvas) {
        Color color = new Color(0, 38, 255, 200);
        canvas.drawRectangle(x / 2,y / 2,
                800, 600, color);
    }


    @Override
    public int getX() {
        return x;
    }



    public void update() {
        lastX = movableEntity.getX();
        lastY =  movableEntity.getY();
    }




    @Override
    public void draw(Canvas canvas) {

    }
}
