package doctrina;

import java.awt.*;

// À partir  du modèle de Gab Laroche

public class Camera extends StaticEntity {

    private ControllableEntity entity;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int x;
    public int y;
    public int lastX;
    public int lastY;
    private RenderingEngine renderingEngine;
 //   int ScreenWidth = renderingEngine.getScreen().getWidth();
  //  int ScreenHeight = renderingEngine.getScreen().getHeight();



    public Camera(ControllableEntity entity, int width, int height) {
        this.entity = entity;
        this.width = width;
        this.height = height;

    }


    public void drawCamera(Canvas canvas) {
        Color color = new Color(0, 38, 255, 200);
        canvas.drawRectangle(entity.x, entity.y,
                100, 100, color);
    }


    public void update() {
        lastX = x;
        lastY = y;
        setX(x);
        setY(y);

    }


    @Override
    public void draw(Canvas canvas) {

    }
}
