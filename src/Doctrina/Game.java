package Doctrina;

import java.awt.*;

public abstract class Game {
    private static final int SLEEP = 25;

    private RenderingEngine renderingEngine;
    private boolean playing = true;
    private long before;
    protected abstract void initiliaze();

    protected abstract void update();

    protected abstract void draw(Canvas canvas);

    public Game() {
        renderingEngine = new RenderingEngine();
    }

    public final void start() {
        initiliaze();
        run();

    }

    private void run() {
        renderingEngine.start();
        updateSyncTime();
        while (playing) {
            update();
            draw(renderingEngine.buildCanvas());
            renderingEngine.drawOnScreen();
            sleep();
        }
    }


    private void sleep() {
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        updateSyncTime();
    }

    private long getSleepTime() {
        long sleep = SLEEP - (System.currentTimeMillis() - before);
        if (sleep < 4) {
            sleep = 4;
        }
        return sleep;
    }



    private void updateSyncTime() {
        before = System.currentTimeMillis();
    }


}
