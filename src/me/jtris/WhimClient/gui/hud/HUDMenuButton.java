package WhimClient.gui.hud;

import net.minecraft.client.gui.GuiScreen;

public class HUDMenuButton extends GuiScreen {

    boolean isEnabled;

    public void HUDMenuButton(boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

}