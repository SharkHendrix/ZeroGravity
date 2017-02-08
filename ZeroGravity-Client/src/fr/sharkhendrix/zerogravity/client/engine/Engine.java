package fr.sharkhendrix.zerogravity.client.engine;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import fr.sharkhendrix.zerogravity.client.Context;
import fr.sharkhendrix.zerogravity.client.engine.graphics.RenderPanel;
import lombok.Getter;
import lombok.Setter;

public class Engine extends Thread {

    @Getter
    @Setter
    private RenderPanel renderPanel;

    @Getter
    @Setter
    private long frameDuration = (long) ((1.0 / 60.0) * 1000);

    @Getter
    @Setter
    private boolean running = true;

    private List<FrameUpdate> frameActions = new ArrayList<>();

    public void addFrameAction(FrameUpdate action) {
        frameActions.add(action);
    }

    @Override
    public void run() {
        long previousTime = System.currentTimeMillis();
        try {
            while (running) {
                long delta = System.currentTimeMillis() - previousTime;
                if (delta < frameDuration) {
                    Thread.sleep(frameDuration - delta);
                }
                double deltaSec = (System.currentTimeMillis() - previousTime) / 1000.0;
                previousTime = System.currentTimeMillis();
                synchronized (Context.getEngineLock()) {
                    frameActions.forEach(a -> a.update(deltaSec));
                    renderPanel.setCurrentDelta(deltaSec);
                }
                if (renderPanel != null) {
                    renderPanel.repaint();
                }
                Toolkit.getDefaultToolkit().sync();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
