package project;
import doctrina.*;
import doctrina.Canvas;
import math.AABB;
import math.Vector2f;

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
    private Camera camera;
    private Canvas canvas;
    private Enemy enemy;
    private int soundCooldown;
    private MovableEntity movableEntity;

    private static final String MAP_PATH = "images/map_1_test.png";
    private ArrayList<Target> targets;
    private Target target;
    private Camerav2 camerav2;
    private Screen screen;



    @Override
    protected void initialize() {
        GameConfig.enableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad);
       // camera = new Camera(player, 100, 100);
        screen =new Screen();
        camerav2 = new Camerav2(new AABB(new Vector2f(-64, -64), (screen.getWidth() + 128)/2, (screen.getHeight() + 128)/2));
        player.teleport(1215, 2000);
        //  player.teleport(100, 100);
        bullets = new ArrayList<>();
        world = new World();
        tree = new Tree(300, 350);
        obstacles = new ArrayList<>();
        enemy = new Enemy(100,100,world);
        enemy.teleport(100,100);
        targets = new ArrayList<>();
        target = new Target(1300,2000);
        targets.add(new Target(1300, 2000));
        targets.add(new Target(1400, 2000));
        targets.add(new Target(1500, 2000));








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
        camerav2.update(player);



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
            for (Target target : targets) {
                if (bullet.hitBoxIntersectWith(target)) {
                    killedElements.add(bullet);
                    killedElements.add(target);
                }
            }
        }

        for (StaticEntity killedElement : killedElements) {
            if (killedElement instanceof Target) {
                targets.remove(killedElement);
            }
            if (killedElement instanceof Bullet) {
                bullets.remove(killedElement);
            }
        }
        CollidableRepository.getInstance().unregisterEntities(killedElements);

    }

    @Override
    protected void draw(Canvas canvas) {

        world.draw(canvas,camerav2);
        player.draw(canvas);

       // enemy.draw(canvas);
       // enemy2.draw(canvas);

        for (Target target : targets) {
            target.draw(canvas);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(canvas);
        }

        if (GameConfig.isDebugEnabled()) {


        }
    }


}

