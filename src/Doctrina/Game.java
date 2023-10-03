package Doctrina;

import java.awt.*;
import java.awt.event.KeyListener;

public abstract class Game {


    private RenderingEngine renderingEngine;
    private boolean playing = true;

    protected abstract void initiliaze();
    private GameTime gameTime;

    protected abstract void update();

    protected abstract void draw(Canvas canvas);

    public Game() {
        renderingEngine = new RenderingEngine();
    }

    public final void addKeyListener(KeyListener keyListener) {
        renderingEngine.addKeyListener(keyListener);
    }

    public final void start() {
        initiliaze();
        run();

    }

    public final void stop() {
        playing = false;
    }

    private void run() {
        renderingEngine.start();
        gameTime = new GameTime();
        while (playing) {
            update();
            draw(renderingEngine.buildCanvas());
            renderingEngine.drawOnScreen();
            gameTime.synchronize();
        }
        renderingEngine.stop();
    }




}
