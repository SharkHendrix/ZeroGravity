package fr.sharkhendrix.zerogravity.client.mapeditor;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.dyn4j.geometry.Vector2;

import fr.sharkhendrix.zerogravity.common.map.Direction;
import fr.sharkhendrix.zerogravity.common.map.Tile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapEditorMouseManager implements MouseListener, MouseMotionListener {

    private @NonNull MapEditor mapEditor;

    private boolean leftClicking = false;

    public void addTo(Component comp) {
        comp.addMouseListener(this);
        comp.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        leftClicking = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicking = true;
            placeTile(e);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            mapEditor.getMaterialChooser()
                    .setDirection(Direction.values()[(mapEditor.getMaterialChooser().getDirection().getId() + 1) % 4]);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicking = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (leftClicking) {
            placeTile(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private void placeTile(MouseEvent e) {
        Vector2 position = mapEditor.getCamera().screenToWorld(e.getPoint());
        Tile tile = mapEditor.getMaterialChooser().getSelectedTile();
        int x = (int) position.x;
        int y = (int) position.y;
        if (tile != null && mapEditor.getEditingMap().isValidLocation(x, y)) {
            mapEditor.setMapTile(x, y, tile);
        }
    }

}
