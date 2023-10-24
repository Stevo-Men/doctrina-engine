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
    private ArrayList<Obstacle> obstacles;


    @Override
    protected void initialize() {
        GameConfig.enableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(200, 200);
        world = new World();
        world.load();
        tree = new Tree(300, 350);

        obstacles = new ArrayList<>();
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
        player.update();
        ArrayList<StaticEntity> killedElements = new ArrayList<>();

        for (Bullet bullet : bullets) {
            bullet.update();
            for (Obstacle obstacle : obstacles) {
                if (bullet.hitBoxIntersectWith(obstacle)) {
                    killedElements.add(bullet);
                    killedElements.add(obstacle);
                }
            }
        }

        for (StaticEntity killedElement : killedElements) {
            if (killedElement instanceof Obstacle) {
                obstacles.remove(killedElement);
            }
            if (killedElement instanceof Bullet) {
                bullets.remove(killedElement);
            }
        }
        CollidableRepository.getInstance().unregisterEntities(killedElements);

    }

    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas);

        player.draw(canvas);
        for (Bullet bullet: bullets) {
            bullet.draw(canvas);
        }



        if (player.getY() < tree.getY() + 52) {
            player.draw(canvas);
            tree.draw(canvas);
        } else {
            tree.draw(canvas);
            player.draw(canvas);
        }
    }
}

