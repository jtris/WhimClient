package WhimClient.modules.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;

public class ModuleFPS extends DraggableModule {

    @Override
    public int getWidth()
    {
        return 50;
    }

    @Override
    public int getHeight()
    {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos)
    {
        font.drawString("FPS: " + mc.getDebugFPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }
}
