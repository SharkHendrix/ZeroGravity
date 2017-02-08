package fr.sharkhendrix.zerogravity.client.gui;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import fr.sharkhendrix.zerogravity.client.Context;
import fr.sharkhendrix.zerogravity.client.Translation;

public class MenuPanel extends SubPanel {

    private static final long serialVersionUID = 1L;

    public MenuPanel() {
        setLayout(new GridBagLayout());
    }

    public void addItem(String translationKey, Runnable action) {
        JButton button = new JButton(Translation.getString(translationKey));
        add(button);
        button.addActionListener(e -> action.run());
    }

    public void addItem(String translationKey, String panelName) {
        addItem(translationKey, () -> Context.getMainPanel().showPanel(panelName));
    }

    @Override
    public void enter() {
    }

    @Override
    public void leave() {
    }
}
