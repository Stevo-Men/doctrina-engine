package doctrina;

import project.Player;

import java.awt.*;

// À partir  du modèle de Gab Laroche

public class Camera extends StaticEntity {

    private ControllableEntity entity;
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

    public Camera(ControllableEntity entity, int width, int height) {
        this.entity = entity;
        this.width = width;
        this.height = height;
    }


    public void drawCamera(Canvas canvas) {
        screen = new Screen();
        Color color = new Color(0, 38, 255, 200);
        canvas.drawRectangle(x / 2,y / 2,
                800, 600, color);
    }


    @Override
    public int getX() {
        return x;
    }

    public int halfX() {
       return entity.getX() - 200;
    }

    public int halfY() {
        return entity.getY() - 150;
    }

    public void updateCamera() {
        lastX = entity.getX();
        lastY =  entity.getY();
        update();
    }

    public void update() {

//        int cameraX = entity.getX() - (width / 2);
//        int cameraY = entity.getY() - (height / 2);
//
//        // Adjust the camera's position to ensure it doesn't go beyond game boundaries
//        // (You can add bounds checking here)
//
//        setX(cameraX);
//        setY(cameraY);
    }


    @Override
    public void draw(Canvas canvas) {

    }
}
