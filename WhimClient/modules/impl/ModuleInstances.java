package WhimClient.modules.impl;

import WhimClient.gui.hud.HUDManager;

public class ModuleInstances {

    public static ModuleArmorStatus modArmorStatus;
    public static ModuleKeystrokes modKeystrokes;
    public static ModuleCPS modCPS;
    public static ModuleToggleSprint modToggleSprint;

    public static void register(HUDManager api)
    {
        modArmorStatus = new ModuleArmorStatus();
        api.register(modArmorStatus);

        modKeystrokes = new ModuleKeystrokes();
        api.register(modKeystrokes);

        modCPS = new ModuleCPS();
        api.register(modCPS);

        modToggleSprint = new ModuleToggleSprint();
        api.register(modToggleSprint);

        initModInstances();
    }

    private static void initModInstances()
    {
        modArmorStatus.setEnabled(modArmorStatus.loadStateFromFile());
        modKeystrokes.setEnabled(modKeystrokes.loadStateFromFile());
        modCPS.setEnabled(modCPS.loadStateFromFile());
        modToggleSprint.setEnabled(modToggleSprint.loadStateFromFile());
    }

}