package project;

import doctrina.*;
import doctrina.Canvas;
import math.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World {

    public static final String MAP_PATH = "images/map_1_XL.png";
    private Image background;
    private Camera camera;
    private Player player;
    private Frame frame;
    private GamePad gamePad;
    boolean nearLeftBorder;
    public Vector2f map;




    public World() {
        map = new Vector2f();
        Vector2f.setWorldVariables(map.x, map.y);
        player = new Player(gamePad);
        load();
    }


    public void load() {
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public void draw(Canvas canvas, Camerav2 camerav2) {
        canvas.drawImage(background, (int) (player.position.getWorldVariables().x - camerav2.getPos().x), (int) (player.position.getWorldVariables().y - camerav2.getPos().y));
    }



    public void update() {

    }


}