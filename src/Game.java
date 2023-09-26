import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Game {


    private Ball ball;
    private JPanel panel;
    private boolean playing = true;
    private JFrame frame;

    private BufferedImage bufferedImage;
    private Graphics2D bufferEngine;
    private long before;
    private int score;
    private long SLEEP;



    public Game() {
        initializeFrame();
        initializePanel();



        ball = new Ball(25);
    }

    public void start() {
        frame.setVisible(true);
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

        ball.update();
        if(ball.hasTouchBound()) {
            score += 10;
        }

    }




    private void drawOnBuffer() {
       // bufferEngine.setPaint(Color.RED);
       // bufferEngine.fillOval(x,y,radius * 2, radius * 2);

        ball.draw(bufferEngine);

        bufferEngine.setPaint(Color.white);
        bufferEngine.drawString("Score: " + score,10,20);
    }

    private void drawOnScreen() {
        Graphics2D graphics = (Graphics2D) panel.getGraphics();
        graphics.drawImage(bufferedImage,0,0,panel);
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    private void initializeFrame() {
        frame = new JFrame();

        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("My Bouncing Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setState(JFrame.NORMAL);
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        frame.add(panel);
    }

}
