package fr.sharkhendrix.zerogravity.client.mapeditor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import fr.sharkhendrix.zerogravity.client.engine.ImageStore;
import fr.sharkhendrix.zerogravity.common.map.Direction;
import fr.sharkhendrix.zerogravity.common.map.Material;
import fr.sharkhendrix.zerogravity.common.map.Tile;
import lombok.Getter;

public class MaterialChooser extends JPanel {

    private static final long serialVersionUID = 1L;

    @Getter
    private Material selectedMaterial = null;
    private ImageToggleButton[] buttons;
    @Getter
    private Direction direction = Direction.RIGHT;

    public MaterialChooser() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 3, 5, 3);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        add(new JPanel(), gbc);
        gbc.weighty = 0;
        Material[] materialValues = Material.values();
        buttons = new ImageToggleButton[materialValues.length];
        for (int i = 0; i < materialValues.length; i++) {
            final ImageToggleButton button = new ImageToggleButton(ImageStore.getTileImage(Tile.get(materialValues[i])),
                    8);
            button.setMinimumSize(new Dimension(50, 50));
            button.setPreferredSize(new Dimension(50, 50));
            final int id = i;
            button.addActionListener(e -> {
                select(id);

            });
            button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(String.valueOf(i + 1)),
                    "select");
            button.getActionMap().put("select", new AbstractAction() {

                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    button.doClick();
                }
            });
            gbc.gridy++;
            add(button, gbc);
            buttons[i] = button;
        }
        gbc.gridy++;
        gbc.weighty = 1;
        JPanel panel = new JPanel();
        panel.add(new JToggleButton(new ImageIcon(ImageStore.getTileImage(Tile.get(Material.WALL)))));
        add(panel, gbc);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        Material[] materialValues = Material.values();
        for (int i = 0; i < materialValues.length; i++) {
            buttons[i].setImage(ImageStore.getTileImage(Tile.get(materialValues[i], direction)));
        }
    }

    public Tile getSelectedTile() {
        if (selectedMaterial != null) {
            return Tile.get(selectedMaterial, direction);
        } else {
            return null;
        }
    }

    private void select(int id) {
        buttons[id].setSelected(true);
        selectedMaterial = Material.values()[id];
        for (int i = 0; i < buttons.length; i++) {
            if (i != id) {
                buttons[i].setSelected(false);
            }
        }
    }

}
