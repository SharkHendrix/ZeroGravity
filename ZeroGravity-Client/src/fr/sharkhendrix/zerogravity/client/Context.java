package fr.sharkhendrix.zerogravity.client;

import fr.sharkhendrix.zerogravity.client.gui.MainPanel;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Context {

    @Getter
    private static Settings settings = new Settings();

    @Getter
    private static MainPanel mainPanel = new MainPanel();

    @Getter
    private static KeyMapping keyMapping = new KeyMapping();

    @Getter
    private static Object engineLock = new Object();

}
