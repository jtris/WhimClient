package WhimClient;


import WhimClient.event.EventManager;
import WhimClient.event.EventTarget;
import WhimClient.event.impl.ClientTickEvent;
import WhimClient.gui.hud.HUDManager;
import WhimClient.modules.impl.ModuleInstances;
import net.minecraft.client.Minecraft;

public class WhimClient {

    private static final WhimClient INSTANCE = new WhimClient();

    public static WhimClient getInstance()
    {
        return INSTANCE;
    }

    private HUDManager hudManager;

    public void start()
    {
        hudManager = HUDManager.getInstance();
        ModuleInstances.register(hudManager);

    }

    public void init()
    {
        EventManager.register(this);
        FileManager.init();
    }

    public void shutdown()
    {
        System.out.println("Client shutting down!");
    }

    @EventTarget
    public void onTick(ClientTickEvent e)
    {

        if (Minecraft.getMinecraft().gameSettings.CLIENT_GUI_HUD_POSITIONING.isPressed()) {
            hudManager.openConfigScreen();
        }
        else if (Minecraft.getMinecraft().gameSettings.CLIENT_GUI_HUD_MENU.isPressed()) {
            hudManager.openMenuScreen();
        }

    }

}
