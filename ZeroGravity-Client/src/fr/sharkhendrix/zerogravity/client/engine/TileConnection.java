package fr.sharkhendrix.zerogravity.client.engine;

import fr.sharkhendrix.zerogravity.common.map.Direction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TileConnection {

    private static TileConnection[] instances;

    static {
        instances = new TileConnection[16];
        for (int i = 0; i < 16; i++) {
            instances[i] = new TileConnection(new boolean[] { (i & 1) == 1, (i & 2) == 2, (i & 4) == 4, (i & 8) == 8 });
        }
    }

    private boolean[] connections;

    public static TileConnection get(boolean rightConnected, boolean downConnected, boolean leftConnected,
            boolean upConnected) {
        return instances[(rightConnected ? 1 : 0) + (downConnected ? 2 : 0) + (leftConnected ? 4 : 0)
                + (upConnected ? 8 : 0)];
    }

    public boolean isConnected(Direction direction) {
        return connections[direction.getId()];
    }

}
