package WhimClient.modules;

import WhimClient.WhimClient;
import WhimClient.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Module {

    private boolean isEnabled = true;

    protected final Minecraft mc;
    protected final FontRenderer font;
    protected final WhimClient client;

    public Module()
    {
        this.mc = Minecraft.getMinecraft();
        this.font = this.mc.fontRendererObj;
        this.client = WhimClient.getInstance();

        setEnabled(isEnabled);
    }

    public void setEnabled(boolean isEnabled)
    {
        this.isEnabled = isEnabled;

        if (isEnabled) {
            EventManager.register(this);
        }
        else {
            EventManager.unregister(this);
        }
    }

    public boolean isEnabled()
    {
        return isEnabled;
    }

}