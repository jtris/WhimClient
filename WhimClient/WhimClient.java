package WhimClient;


import WhimClient.event.EventManager;
import WhimClient.event.EventTarget;
import WhimClient.event.impl.ClientTickEvent;
import WhimClient.gui.hud.HUDManager;
import WhimClient.mods.impl.ModInstances;
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
        ModInstances.register(hudManager);

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

        if (Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
            hudManager.openConfigScreen();
        }

    }
}
