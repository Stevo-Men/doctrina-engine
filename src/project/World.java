package project;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World {

    private static final String MAP_PATH = "images/map_1_XL.png";
    private Image background;
    private Camera camera;
    private Player player;
    private Frame frame;
    private GamePad gamePad;
    boolean nearLeftBorder;
    private int WorldX,WorldY;




    public World() {
        player = new Player(gamePad);
        load();
        camera = new Camera(player, 500, 500);
    }


    public void load() {
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public void draw(Canvas canvas, Camera camera) {
        camera.updateCamera();
        canvas.drawImage(background, player.getX() - camera.getLastX(), player.getY() - camera.getLastY());



    }

    public void update() {

    }

    public void drawBorderTest(Canvas canvas) {
        camera.update();
        WorldX = player.getX() - camera.getLastX();
        WorldY = player.getY() - camera.getLastY();
        Color color = new Color(255, 181, 0, 200);
        canvas.drawRectangle(WorldX, WorldY,
                50, 50, color);
    }
}