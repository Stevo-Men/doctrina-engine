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
    private static final String MAP_PATH = "images/map_1_test.png";
    private ArrayList<Enemy> enemies;

    private Camerav2 camerav2;
    private Screen screen;



    @Override
    protected void initialize() {
       GameConfig.enableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad);
        world = new World();
        screen = new Screen();
        camerav2 = new Camerav2(new AABB(new Vector2f(player.x, player.y), 800, 600));


        player.teleport(200, 200);

        //  player.teleport(100, 100);
        bullets = new ArrayList<>();
        world = new World();
        tree = new Tree(300, 350);
        obstacles = new ArrayList<>();
        enemies = new ArrayList<>();
        enemy = new Enemy();
        enemies.add(new Enemy());







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

        // RenderingEngine.getInstance().getScreen().toggleFullscreen();
        //RenderingEngine.getInstance().getScreen().hideCursor();
    }


    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }



        player.update();
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


        for (Enemy enemy : enemies) {
            enemy.update();
        }

        ArrayList<StaticEntity> killedElements = new ArrayList<>();

        for (Bullet bullet : bullets) {
            bullet.update();
            for (Enemy enemy : enemies) {
                if (bullet.hitBoxIntersectWith(enemy)) {
                    killedElements.add(bullet);
                    killedElements.add(enemy);
                }
            }
        }



        for (StaticEntity killedElement : killedElements) {
            if (killedElement instanceof Enemy) {
                enemies.remove(killedElement);
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






        for (Enemy enemy : enemies) {
            enemy.draw(canvas);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(canvas);
        }

        if (GameConfig.isDebugEnabled()) {
            camerav2.draw(canvas);
        }
    }


}

