package footprint;

import Doctrina.Canvas;
import Doctrina.Game;

import java.awt.*;
import java.util.ArrayList;

public class footprintGame extends Game {

    private ArrayList<footprint> footprints;
    private Player player;
    private GamePad gamePad;
    @Override
    protected void initiliaze() {
        footprints = new ArrayList<>();
        gamePad =new GamePad();
        player = new Player(gamePad);
    }

    @Override
    protected void update() {
    if(gamePad.isQuitPressed()) {
    stop();
    }
    player.update();
    if (gamePad.isMoving()) {
        footprints.add(player.layFootprint());
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawRectangle(0,0,800,600, Color.BLUE);
        for (footprint footprint : footprints) {
            footprint.draw(canvas);
        }
            player.draw(canvas);
    }
}
