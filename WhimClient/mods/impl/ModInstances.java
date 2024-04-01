package WhimClient.mods.impl;

import WhimClient.gui.hud.HUDManager;

public class ModInstances {

    public static ModArmorStatus modArmorStatus;
    public static ModKeystrokes modKeystrokes;

    public static void register(HUDManager api)
    {
        modArmorStatus = new ModArmorStatus();
        api.register(modArmorStatus);

        modKeystrokes = new ModKeystrokes();
        api.register(modKeystrokes);
    }

}