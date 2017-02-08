package fr.sharkhendrix.zerogravity.client.mapeditor;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import fr.sharkhendrix.zerogravity.client.Translation;

public class MapEditorMenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private MapEditor mapEditor;

    public MapEditorMenuBar(MapEditor mapEditor) {
        this.mapEditor = mapEditor;

        JMenu fileMenu = new JMenu(Translation.getString("mapEditor.menu.file"));
        add(fileMenu);
    }

}
