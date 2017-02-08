package fr.sharkhendrix.zerogravity.client.gui;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;

import fr.sharkhendrix.zerogravity.client.Context;
import fr.sharkhendrix.zerogravity.client.engine.ImageStore;

public class ClientGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public ClientGUI() {
        setContentPane(Context.getMainPanel());
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                Context.getKeyMapping().update(e);
                return false;
            }
        });
        ImageStore.load();
        new ClientGUI();
    }
}
