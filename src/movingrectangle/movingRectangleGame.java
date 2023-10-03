package movingrectangle;

import Doctrina.Canvas;
import Doctrina.Controller;
import Doctrina.Game;

import java.awt.*;

public class movingRectangleGame extends Game {

    private GamePad gamePad;
    private Player player;
    private NPC npc;


    @Override
    protected void initiliaze() {

    gamePad = new GamePad();
    super.addKeyListener(gamePad);
    player = new Player(gamePad);
    npc = new NPC();
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
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
