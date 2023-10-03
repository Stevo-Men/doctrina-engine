package movingrectangle;

import Doctrina.Canvas;
import Doctrina.Game;

import java.awt.*;

public class movingRectangleGame extends Game {

    private Player player;
    private NPC npc;

    @Override
    protected void initiliaze() {
    player = new Player();
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
