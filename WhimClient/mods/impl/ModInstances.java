package WhimClient.mods.impl;

import WhimClient.gui.hud.HUDManager;

public class ModInstances {

    private static ModArmorStatus modArmorStatus;

    public static void register(HUDManager api)
    {

        modArmorStatus = new ModArmorStatus();
        api.register(modArmorStatus);
    }

}