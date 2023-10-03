package footprint;

import Doctrina.Canvas;
import java.awt.*;

public class Player {
private GamePad gamePad;
    private int x;
    private int y;
    private int speed;
    public Player(GamePad gamePad) {
        x = 200;
        y = 200;
        speed = 3;
        this.gamePad = gamePad;
    }

    public footprint layFootprint() {
        return new footprint(x,y);
    }

    public void update() {
        if (gamePad.isDownPressed()){
            y+= speed;
        }
        if (gamePad.isUpPressed()){
            y-= speed;
        }
        if (gamePad.isLeftPressed()){
            x-= speed;
        }
        if (gamePad.isRightPressed()){
            x+= speed;
        }

    }

    public void draw(Canvas canvas) {
        canvas.drawRectangle(x,y,20,20, Color.WHITE);
    }


}
