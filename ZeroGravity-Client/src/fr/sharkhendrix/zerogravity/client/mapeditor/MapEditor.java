package fr.sharkhendrix.zerogravity.client.mapeditor;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.dyn4j.geometry.Vector2;

import fr.sharkhendrix.zerogravity.client.Constants;
import fr.sharkhendrix.zerogravity.client.Context;
import fr.sharkhendrix.zerogravity.client.KeyMapping;
import fr.sharkhendrix.zerogravity.client.engine.Camera;
import fr.sharkhendrix.zerogravity.client.engine.Engine;
import fr.sharkhendrix.zerogravity.client.engine.FrameUpdate;
import fr.sharkhendrix.zerogravity.client.engine.graphics.MapRenderer;
import fr.sharkhendrix.zerogravity.client.engine.graphics.RenderPanel;
import fr.sharkhendrix.zerogravity.client.gui.SubPanel;
import fr.sharkhendrix.zerogravity.common.map.Map;
import fr.sharkhendrix.zerogravity.common.map.MapFactory;
import fr.sharkhendrix.zerogravity.common.map.Tile;
import lombok.Getter;

public class MapEditor extends SubPanel implements MouseWheelListener, FrameUpdate {

    private static final long serialVersionUID = 1L;

    private @Getter MapEditorSettings settings;
    private Engine engine;
    private @Getter Map editingMap;
    private @Getter Camera camera;
    private MapRenderer mapRenderer;
    private @Getter MaterialChooser materialChooser;
    private MapEditorMouseManager mouseManager;

    public MapEditor() {
        setLayout(new BorderLayout());

        settings = new MapEditorSettings();
        editingMap = MapFactory.createEmptyMap(40, 40);

        MapEditorRenderer mapEditorRenderer = new MapEditorRenderer(this);
        RenderPanel renderPanel = new RenderPanel();
        camera = renderPanel.getCamera();
        mapRenderer = new MapRenderer();
        mapRenderer.setMap(editingMap);
        renderPanel.addWorldRenderer(mapRenderer);
        renderPanel.addScreenRenderer(mapEditorRenderer);

        add(new MapEditorMenuBar(this), BorderLayout.NORTH);
        add(renderPanel, BorderLayout.CENTER);
        materialChooser = new MaterialChooser();
        add(materialChooser, BorderLayout.WEST);

        this.addMouseWheelListener(this);
        mouseManager = new MapEditorMouseManager(this);
        mouseManager.addTo(renderPanel);

        engine = new Engine();
        engine.setRenderPanel(renderPanel);
        engine.addFrameAction(this);
    }

    public void setMapTile(int x, int y, Tile tile) {
        editingMap.set(x, y, tile);
        mapRenderer.updateConnection(x, y);
        if (x > 0) {
            mapRenderer.updateConnection(x - 1, y);
        }
        if (x < editingMap.getWidth() - 1) {
            mapRenderer.updateConnection(x + 1, y);
        }
        if (y > 0) {
            mapRenderer.updateConnection(x, y - 1);
        }
        if (y < editingMap.getHeight() - 1) {
            mapRenderer.updateConnection(x, y + 1);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        double scale = 1 - event.getWheelRotation() * 0.1;
        camera.setScale(camera.getScale() * scale);
    }

    @Override
    public void update(double delta) {

        Vector2 move = new Vector2();

        KeyMapping keyMapping = Context.getKeyMapping();
        if (keyMapping.isPressed(KeyEvent.VK_RIGHT)) {
            move.x = settings.getCameraSpeed();
        } else if (keyMapping.isPressed(KeyEvent.VK_LEFT)) {
            move.x = -settings.getCameraSpeed();
        }

        if (keyMapping.isPressed(KeyEvent.VK_UP)) {
            move.y = -settings.getCameraSpeed();
        } else if (keyMapping.isPressed(KeyEvent.VK_DOWN)) {
            move.y = settings.getCameraSpeed();
        }

        camera.getPosition().add(move.multiply(delta).multiply(Constants.TILE_WIDTH / camera.getScale()));
    }

    @Override
    public void enter() {
        engine.start();
    }

    @Override
    public void leave() {
        engine.setRunning(false);
    }

}
