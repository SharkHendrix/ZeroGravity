package fr.sharkhendrix.zerogravity.client.mapeditor;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.sharkhendrix.zerogravity.client.engine.Camera;
import fr.sharkhendrix.zerogravity.client.engine.graphics.Renderer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapEditorRenderer implements Renderer {

    private MapEditor mapEditor;

    @Override
    public void render(Graphics2D g, double delta) {
        g.setColor(Color.RED);
        Camera camera = mapEditor.getCamera();
        g.drawRect((int) (camera.getWidth() / 2.0 - camera.getPosition().x * camera.getScale()),
                (int) (camera.getHeight() / 2.0 - camera.getPosition().y * camera.getScale()),
                (int) (mapEditor.getEditingMap().getWidth() * camera.getScale()),
                (int) (mapEditor.getEditingMap().getHeight() * camera.getScale()));
    }

}
