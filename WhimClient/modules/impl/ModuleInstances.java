package WhimClient.modules.impl;

import WhimClient.gui.hud.HUDManager;

public class ModuleInstances {

    public static ModuleArmorStatus moduleArmorStatus;
    public static ModuleKeystrokes moduleKeystrokes;
    public static ModuleCPS moduleCPS;
    public static ModuleToggleSprint moduleToggleSprint;
    public static ModuleXYZPosition moduleXYZPosition;
    public static ModuleFPS moduleFPS;
    public static ModuleFreelook moduleFreelook;
    public static ModulePotionEffects modulePotionEffects;
    public static ModuleSpeedCounter moduleSpeedCounter;
    public static ModuleTPS moduleTPS;

    public static void register(HUDManager api)
    {
        moduleArmorStatus = new ModuleArmorStatus();
        api.register(moduleArmorStatus);

        moduleKeystrokes = new ModuleKeystrokes();
        api.register(moduleKeystrokes);

        moduleCPS = new ModuleCPS();
        api.register(moduleCPS);

        moduleToggleSprint = new ModuleToggleSprint();
        api.register(moduleToggleSprint);

        moduleXYZPosition = new ModuleXYZPosition();
        api.register(moduleXYZPosition);

        moduleFPS = new ModuleFPS();
        api.register(moduleFPS);

        moduleFreelook = new ModuleFreelook();
        api.register(moduleFreelook);

        modulePotionEffects = new ModulePotionEffects();
        api.register(modulePotionEffects);

        moduleSpeedCounter = new ModuleSpeedCounter();
        api.register(moduleSpeedCounter);

        moduleTPS = new ModuleTPS();
        api.register(moduleTPS);

        initModInstances();
    }

    private static void initModInstances()
    {
        moduleArmorStatus.setEnabled(moduleArmorStatus.loadStateFromFile());
        moduleKeystrokes.setEnabled(moduleKeystrokes.loadStateFromFile());
        moduleCPS.setEnabled(moduleCPS.loadStateFromFile());
        moduleToggleSprint.setEnabled(moduleToggleSprint.loadStateFromFile());
        moduleXYZPosition.setEnabled(moduleXYZPosition.loadStateFromFile());
        moduleFPS.setEnabled(moduleFPS.loadStateFromFile());
        moduleFreelook.setEnabled(moduleFreelook.loadStateFromFile());
        modulePotionEffects.setEnabled(modulePotionEffects.loadStateFromFile());
        moduleSpeedCounter.setEnabled(moduleSpeedCounter.loadStateFromFile());
        moduleTPS.setEnabled(moduleTPS.loadStateFromFile());
    }

}
