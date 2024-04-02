package WhimClient.modules.impl;

import WhimClient.gui.hud.HUDManager;

public class ModuleInstances {

    public static ModuleArmorStatus modArmorStatus;
    public static ModuleKeystrokes modKeystrokes;
    public static ModuleCPS modCPS;

    public static void register(HUDManager api)
    {
        modArmorStatus = new ModuleArmorStatus();
        api.register(modArmorStatus);

        modKeystrokes = new ModuleKeystrokes();
        api.register(modKeystrokes);

        modCPS = new ModuleCPS();
        api.register(modCPS);

        initModInstances();
    }

    private static void initModInstances()
    {
        modArmorStatus.setEnabled(modArmorStatus.loadStateFromFile());
        modKeystrokes.setEnabled(modKeystrokes.loadStateFromFile());
        modCPS.setEnabled(modCPS.loadStateFromFile());
    }

}