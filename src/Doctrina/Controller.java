package Doctrina;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Controller implements KeyListener {

    private final HashMap<Integer , Boolean> pressedKeys;
    public Controller() {
        pressedKeys = new HashMap<>();
    }

    protected void bindKey(int keyCode) {
        pressedKeys.put(keyCode, false);
    }

    protected void clearKey() {
        pressedKeys.clear();
    }

    protected void removeKey(int keyCode) {
        pressedKeys.remove(keyCode);
    }
    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.containsKey(keyCode)
                && pressedKeys.get(keyCode);
    }
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, false);
        }
    }
}
