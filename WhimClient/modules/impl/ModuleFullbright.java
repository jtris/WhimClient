package WhimClient.modules.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;

public class ModuleFullbright extends DraggableModule {

    @Override
    public int getWidth()
    {
        return 0;
    }

    @Override
    public int getHeight()
    {
        return 0;
    }

    @Override
    public void render(ScreenPosition pos)
    {
        this.setGamma(1000.0F);
    }

    public void setGamma(float gamma)
    {
        mc.gameSettings.saturation = gamma;
    }

}
