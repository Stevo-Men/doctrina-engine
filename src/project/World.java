package project;

import doctrina.Camera;
import doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World {

    private static final String MAP_PATH = "images/map_1_lvl1.png";
    private Image background;
    private Camera camera;
    private Player player;
    private Frame frame;
    private GamePad gamePad;




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


}