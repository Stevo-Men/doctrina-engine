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
    private Player player;
    private GamePad gamePad;
    public Vector2f map;


    public World() {
        player = new Player(gamePad);
        load();
        map.setMapDimensions(3200,3200);
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
        int worldX = 0;
        int worldY = 0;
        int screenX = worldX - player.worldX + player.screenX;
        int screenY = worldY - player.worldY + player.screenY;

        canvas.drawImage(background, screenX - (int) camerav2.getPos().x, (int) (screenY - camerav2.getPos().y));

    }

    public void update() {


    }
}