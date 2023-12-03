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
    private Camerav2 camerav2;
    private Player player;
    private Frame frame;
    private GamePad gamePad;
    boolean nearLeftBorder;
    public Vector2f map;
    private Screen screen;




    public World() {
        player = new Player(gamePad);
        load();
        map.setMapDimensions(background.getWidth(null),background.getHeight(null));
        map = new Vector2f();
        map.setVectorMap(400, 300);
        screen = new Screen();

      //  player.setPosition(-1650, -2300);
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
        int startX = (int) (player.position.getWorldVariables().x += camerav2.getPos().x);
        int startY = (int) (player.position.getWorldVariables().y += camerav2.getPos().y);

        int secondX = (int) (startX);
        int secondY = (int) (startY);


        //canvas.drawImage(background, (int) (player.position.getWorldVariables().x - secondX), (int) player.position.getWorldVariables().y - secondY);
        canvas.drawImage(background, (int) (player.position.getWorldVariables().x - secondX), (int) player.position.getWorldVariables().y - secondY);
    }








    public void update() {



    }




}