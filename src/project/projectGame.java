package project;

import doctrina.*;
import tank.Brick;
import tank.Missile;

import java.util.ArrayList;
import java.util.List;


public class projectGame extends Game {

    private Player player;
    private GamePad gamePad;
    private World world;
    private Tree tree;
    private ArrayList<Bullet> bullets;


    @Override
    protected void initialize() {
        GameConfig.enableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(200, 200);
        world = new World();
        world.load();
        tree = new Tree(300, 350);

        bullets = new ArrayList<>();

    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();
        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }
        if (gamePad.isFirePressed() && player.canFire()) {
            bullets.add(player.fire());
        }

    }

    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas);
        if (player.getY() < tree.getY() + 52) {
            player.draw(canvas);
            tree.draw(canvas);
        } else {
            tree.draw(canvas);
            player.draw(canvas);
        }
    }
}

