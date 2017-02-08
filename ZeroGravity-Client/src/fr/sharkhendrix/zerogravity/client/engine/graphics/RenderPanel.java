package fr.sharkhendrix.zerogravity.client.engine.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import fr.sharkhendrix.zerogravity.client.Constants;
import fr.sharkhendrix.zerogravity.client.Context;
import fr.sharkhendrix.zerogravity.client.engine.Camera;
import lombok.Getter;
import lombok.Setter;

public class RenderPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    @Getter
    private Camera camera = new Camera();

    private List<Renderer> worldRenderers = new ArrayList<>();
    private List<Renderer> screenRenderers = new ArrayList<>();

    @Setter
    private double currentDelta;

    public RenderPanel() {
        setDoubleBuffered(true);
        setIgnoreRepaint(true);
        addComponentListener(new ComponentListener() {

            @Override
            public void componentShown(ComponentEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void componentResized(ComponentEvent e) {
                updateCamera();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void componentHidden(ComponentEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void addWorldRenderer(Renderer renderer) {
        worldRenderers.add(renderer);
    }

    public void addScreenRenderer(Renderer renderer) {
        screenRenderers.add(renderer);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform screenTransform = g2d.getTransform();
        AffineTransform worldTransform = new AffineTransform(screenTransform);
        worldTransform.translate(getWidth() / 2, getHeight() / 2);
        worldTransform.translate(-camera.getPosition().x * camera.getScale(),
                -camera.getPosition().y * camera.getScale());
        worldTransform.scale(camera.getScale() / Constants.TILE_WIDTH, camera.getScale() / Constants.TILE_WIDTH);
        synchronized (Context.getEngineLock()) {
            g2d.setTransform(worldTransform);
            worldRenderers.forEach(r -> r.render(g2d, currentDelta));
            g2d.setTransform(screenTransform);
            screenRenderers.forEach(r -> r.render(g2d, currentDelta));
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void updateCamera() {
        camera.setWidth(getWidth());
        camera.setHeight(getHeight());
    }
}
