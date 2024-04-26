package WhimClient.modules.impl;

import WhimClient.gui.hud.HUDManager;
import WhimClient.modules.DraggableModule;

import java.util.ArrayList;
import java.util.List;

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

    public static ModuleCompass moduleCompass;

    public static List<DraggableModule> modulesArray;

    public static void register(HUDManager api)
    {
        modulesArray = new ArrayList<>();

        moduleArmorStatus = new ModuleArmorStatus();
        api.register(moduleArmorStatus);
        modulesArray.add(moduleArmorStatus);

        moduleKeystrokes = new ModuleKeystrokes();
        api.register(moduleKeystrokes);
        modulesArray.add(moduleKeystrokes);

        moduleCPS = new ModuleCPS();
        api.register(moduleCPS);
        modulesArray.add(moduleCPS);

        moduleToggleSprint = new ModuleToggleSprint();
        api.register(moduleToggleSprint);
        modulesArray.add(moduleToggleSprint);

        moduleXYZPosition = new ModuleXYZPosition();
        api.register(moduleXYZPosition);
        modulesArray.add(moduleXYZPosition);

        moduleFPS = new ModuleFPS();
        api.register(moduleFPS);
        modulesArray.add(moduleFPS);

        moduleFreelook = new ModuleFreelook();
        api.register(moduleFreelook);
        modulesArray.add(moduleFreelook);

        modulePotionEffects = new ModulePotionEffects();
        api.register(modulePotionEffects);
        modulesArray.add(modulePotionEffects);

        moduleSpeedCounter = new ModuleSpeedCounter();
        api.register(moduleSpeedCounter);
        modulesArray.add(moduleSpeedCounter);

        moduleTPS = new ModuleTPS();
        api.register(moduleTPS);
        modulesArray.add(moduleTPS);

        moduleCompass = new ModuleCompass();
        api.register(moduleCompass);
        modulesArray.add(moduleCompass);

        initModInstances();
    }

    private static void initModInstances()
    {

        for (DraggableModule module : modulesArray) {
            module.setEnabled(module.loadStateFromFile());
        }

    }

}
