package fr.sharkhendrix.zerogravity.client.gui;

import javax.swing.JPanel;

public abstract class SubPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public abstract void enter();

    public abstract void leave();
}
