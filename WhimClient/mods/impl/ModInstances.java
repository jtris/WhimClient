package WhimClient.mods.impl;

import WhimClient.gui.hud.HUDManager;

public class ModInstances {

    private static ModHelloWorld modHelloWorld;

    public static void register(HUDManager api) {
        modHelloWorld = new ModHelloWorld();
        api.register(modHelloWorld);
    }

    public static ModHelloWorld getModHelloWorld() {
        return modHelloWorld;
    }

}