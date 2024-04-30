package WhimClient.modules.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;

public class ModuleXYZPosition extends DraggableModule {

    @Override
    public int getWidth()
    {
        return font.getStringWidth(getFormattedString());
    }

    private String getFormattedString()
    {
        double xPosition = mc.getRenderViewEntity().posX;
        double yPosition = mc.getRenderViewEntity().getEntityBoundingBox().minY;
        double zPosition = mc.getRenderViewEntity().posZ;

        return String.format("XYZ: [%.1f, %.1f, %.1f]", xPosition, yPosition, zPosition);
    }

    @Override
    public int getHeight()
    {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos)
    {
        font.drawString(getFormattedString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

}
