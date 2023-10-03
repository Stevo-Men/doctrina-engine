package movingrectangle;

import Doctrina.Canvas;
import Doctrina.Controller;
import Doctrina.Game;

import java.awt.*;

public class movingRectangleGame extends Game {

    private Controller controller;
    private Player player;
    private NPC npc;


    @Override
    protected void initiliaze() {

    controller = new Controller();
    super.addKeyListener(controller);
    player = new Player(controller);
    npc = new NPC();
    }

    @Override
    protected void update() {
    player.update();
    npc.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawRectangle(0,0,800,600, Color.BLUE);
        player.draw(canvas);
        npc.draw(canvas);
    }
}
