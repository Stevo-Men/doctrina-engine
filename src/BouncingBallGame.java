import Doctrina.Canvas;
import Doctrina.Game;

import java.awt.*;

public final class BouncingBallGame extends Game {

    private Ball ball;
    private int score;



    @Override
    protected void initiliaze() {
        ball = new Ball(25);
    }

    @Override
    protected void update() {
        ball.update();
        if (ball.hasTouchBound()) {
            score += 10;
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        ball.draw(canvas);
        canvas.drawString("Score: " + score, 10, 20, Color.WHITE);

    }
}
