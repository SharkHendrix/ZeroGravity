package fr.sharkhendrix.zerogravity.client;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyMapping {

    private Map<Integer, Boolean> keyPressed = new HashMap<>();

    public boolean isPressed(int keyCode) {
        Boolean pressed = keyPressed.get(keyCode);
        return pressed == Boolean.TRUE;
    }

    public void update(KeyEvent event) {
        if (event.getID() == KeyEvent.KEY_PRESSED) {
            keyPressed.put(event.getKeyCode(), true);
        } else if (event.getID() == KeyEvent.KEY_RELEASED) {
            keyPressed.put(event.getKeyCode(), false);
        }
    }
}
