package project;
import doctrina.*;
import doctrina.Canvas;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;


public class projectGame extends Game {

    private Player player;
    private GamePad gamePad;
    private ArrayList<Bullet> bullets;
    private ArrayList<Obstacle> obstacles;
    private World world;
    private Tree tree;
    private Camera camera;
    private Canvas canvas;
    private Enemy enemy;
    private int soundCooldown;
    private MovableEntity movableEntity;

    private static final String MAP_PATH = "images/map_1_test.png";



    @Override
    protected void initialize() {
       // GameConfig.enableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad);
        camera = new Camera(player, 100, 100);
       // player.teleport(1215, 2000);
        player.teleport(100, 100);
        bullets = new ArrayList<>();
        world = new World();
        tree = new Tree(300, 350);
        obstacles = new ArrayList<>();
        enemy = new Enemy(1215,2000);
        enemy.teleport(1215,2000);








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

         RenderingEngine.getInstance().getScreen().toggleFullscreen();
        //RenderingEngine.getInstance().getScreen().hideCursor();
    }


    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();


        enemy.update();

        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }

        if (gamePad.isFirePressed() && player.canFire()) {
            bullets.add(player.fire());
        }


        soundCooldown--;
        if (soundCooldown < 0) {
            soundCooldown = 100;
        }

        if (gamePad.isFirePressed() && soundCooldown == 0) {
            soundCooldown--;
            SoundEffect.FIRE.play();
        }



        world.update();







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

        world.draw(canvas, camera);
        enemy.draw(canvas);
        player.draw(canvas);


        for (Bullet bullet : bullets) {
            bullet.draw(canvas);
        }


        if (GameConfig.isDebugEnabled()) {
            camera.drawCamera(canvas);
        }
    }


}

