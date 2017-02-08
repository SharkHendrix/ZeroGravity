package fr.sharkhendrix.zerogravity.client.mapeditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JToggleButton;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ImageToggleButton extends JToggleButton {

    private static final long serialVersionUID = 1L;

    @Getter
    private BufferedImage image;
    @Setter
    @Getter
    private int imageBorder;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(image, imageBorder, imageBorder, getWidth() - imageBorder, getHeight() - imageBorder, 0, 0,
                image.getWidth(), image.getHeight(), null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }
}
