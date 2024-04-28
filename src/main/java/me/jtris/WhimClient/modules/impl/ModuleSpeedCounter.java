package WhimClient.modules.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;

public class ModuleSpeedCounter extends DraggableModule {

    int tps = 20;

    @Override
    public int getWidth()
    {
        return font.getStringWidth("00.0 m/s");
    }

    @Override
    public int getHeight()
    {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos)
    {
        double speed = Math.sqrt(
                Math.pow(mc.thePlayer.posX - mc.thePlayer.prevPosX, 2) +
                Math.pow(mc.thePlayer.posZ - mc.thePlayer.prevPosZ, 2)
        );

        font.drawString(String.format("%.1f m/s", speed * this.tps), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos)
    {
        font.drawString("00.0 m/s", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

}
