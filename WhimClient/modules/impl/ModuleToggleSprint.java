package WhimClient.modules.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;
import net.minecraft.potion.Potion;

public class ModuleToggleSprint extends DraggableModule {

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void render(ScreenPosition pos)
    {

        // do not display anything
        mc.thePlayer.setSprinting(this.isEnabled() && (!mc.thePlayer.isBlocking()

                && !mc.thePlayer.isSneaking()
                && (mc.thePlayer.motionX != 0)
                && (mc.thePlayer.motionZ != 0)
                && !mc.thePlayer.isPotionActive(Potion.moveSlowdown)
                && !mc.thePlayer.isPotionActive(Potion.confusion)
                && mc.gameSettings.keyBindLeft.isKeyDown()));

    }

    @Override
    public void renderDummy(ScreenPosition pos) {}
}
