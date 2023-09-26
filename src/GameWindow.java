import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameWindow extends JFrame {

    private Random rnd = new Random();
    private JPanel panel;
    private boolean playing = true;
    private int radius = 25;
    private int x = randomNumber(radius * 2, 800 - radius * 2);
    private int y = randomNumber(radius * 2, 600 - radius * 2);;
    private int dx = randomNumber(0, 1) == 0 ? 5 : -5;
    private int dy = randomNumber(0, 1) == 0 ? 5 : -5;;
    private BufferedImage bufferedImage;
    private Graphics2D bufferEngine;
    private long before;
    private int score;
    private long SLEEP;



    public GameWindow() {
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("My Bouncing Balls");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setState(JFrame.NORMAL);

        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        add(panel);
    }

    public void start() {
        setVisible(true);
        before = System.currentTimeMillis();
        while (playing) {
            bufferedImage = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            bufferEngine = bufferedImage.createGraphics();
            bufferEngine.setRenderingHints(hints);

            update();
            drawOnBuffer();
            drawOnScreen();

            long sleep = SLEEP - (System.currentTimeMillis() - before);
            if (sleep < 4) {
                sleep = 4;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            before = System.currentTimeMillis();
        }


    }

    private void update() {
        x += dx;
        y += dy;
        if (y <= radius || y >= 600 - radius) {
            dy *= -1;
            score += 10;
        }
        if (x <= radius || x >= 600 - radius) {
            dx *= -1;
            score += 10;
        }
    }

    private void drawOnBuffer() {
        bufferEngine.setPaint(Color.RED);
        bufferEngine.fillOval(x,y,radius * 2, radius * 2);


        bufferEngine.setPaint(Color.white);
        bufferEngine.drawString("Score: " + score,10,20);
    }

    private void drawOnScreen() {
        Graphics2D graphics = (Graphics2D) panel.getGraphics();
        graphics.drawImage(bufferedImage,0,0,panel);
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    private int randomNumber(int min, int max) {
        return rnd.nextInt((max - min) + 1) + min;
    }
}
