package project;
import doctrina.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;


public class projectGame extends Game {

    private Player player;
    private GamePad gamePad;
    private ArrayList<Bullet> bullets;
    private ArrayList<Obstacle> obstacles;
    private World world;
    private Tree tree;


    @Override
    protected void initialize() {
        GameConfig.enableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(200, 200);
        bullets = new ArrayList<>();
        world = new World();
        world.load();
        tree = new Tree(300, 350);
        obstacles = new ArrayList<>();


        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream("audios/testMusicIntro.wav"));
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

