package fr.sharkhendrix.zerogravity.client.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import fr.sharkhendrix.zerogravity.client.mapeditor.MapEditor;
import lombok.Getter;

public class MainPanel extends JPanel {

    public static final String MAIN_MENU = "MAIN_MENU";

    public static final String MAP_EDITOR = "MAP_EDITOR";

    private static final long serialVersionUID = 1L;

    @Getter
    private MainMenu mainMenu = new MainMenu();

    @Getter
    private MapEditor mapEditor = new MapEditor();

    private Map<String, SubPanel> panels = new HashMap<>();

    private String currentPanelName = null;

    public MainPanel() {

        setLayout(new CardLayout());
        setPreferredSize(new Dimension(800, 600));

        panels.put(MAIN_MENU, mainMenu);
        panels.put(MAP_EDITOR, mapEditor);

        panels.forEach((k, v) -> add(v, k));

        showPanel(MAIN_MENU);
    }

    public void showPanel(String name) {
        if (currentPanelName != null) {
            panels.get(currentPanelName).leave();
        }
        panels.get(name).enter();
        ((CardLayout) getLayout()).show(this, name);
    }
}
