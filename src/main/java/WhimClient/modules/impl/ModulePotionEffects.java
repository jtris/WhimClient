package WhimClient.modules.impl;

import WhimClient.event.EventTarget;
import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.potion.Potion;

import java.util.HashMap;

public class ModulePotionEffects extends DraggableModule {

    private final HashMap<Potion, String> potionNameTranslations = new HashMap<>();

    private final int capacity = this.getHeight() / (font.FONT_HEIGHT + 4);
    private int occupiedSlotsCount = 0;

    public ModulePotionEffects()
    {
        this.potionNameTranslations.put(Potion.moveSpeed, "speed");
        this.potionNameTranslations.put(Potion.moveSlowdown, "slowness");
        this.potionNameTranslations.put(Potion.digSpeed, "haste");
        this.potionNameTranslations.put(Potion.digSlowdown, "mining fatigue");
        this.potionNameTranslations.put(Potion.damageBoost, "strength");
        this.potionNameTranslations.put(Potion.heal, "instant health");
        this.potionNameTranslations.put(Potion.harm, "instant damage");
        this.potionNameTranslations.put(Potion.jump, "jump boost");
        this.potionNameTranslations.put(Potion.confusion, "nausea");
        this.potionNameTranslations.put(Potion.regeneration, "regeneration");
        this.potionNameTranslations.put(Potion.resistance, "resistance");
        this.potionNameTranslations.put(Potion.fireResistance, "fire resistance");
        this.potionNameTranslations.put(Potion.waterBreathing, "water breathing");
        this.potionNameTranslations.put(Potion.invisibility, "invisibility");
        this.potionNameTranslations.put(Potion.blindness, "blindness");
        this.potionNameTranslations.put(Potion.nightVision, "night vision");
        this.potionNameTranslations.put(Potion.hunger, "hunger");
        this.potionNameTranslations.put(Potion.weakness, "weakness");
        this.potionNameTranslations.put(Potion.poison, "poison");
        this.potionNameTranslations.put(Potion.wither, "wither");
        this.potionNameTranslations.put(Potion.healthBoost, "health boost");
        this.potionNameTranslations.put(Potion.absorption, "absorption");
        this.potionNameTranslations.put(Potion.saturation, "saturation");
    }

    private void renderEffect(String name, double duration, ScreenPosition hudPos)
    {
                String durationString = name;
                durationString += " (";
                if (duration > 60) {
                    durationString += (int) ((duration - (duration % 60)) / 60);
                    duration -= (duration - (duration % 60));
                }
                else {
                    durationString += "0";
                }
                durationString += ":";

                if (duration < 10) {
                    durationString += "0";
                }
                duration = Math.round(duration);
                durationString += (int) duration;
                durationString += ")";

                int screenPos = (this.capacity - this.occupiedSlotsCount) * getHeight() / this.capacity + 2;
                font.drawString(durationString, hudPos.getAbsoluteX() + 2, hudPos.getAbsoluteY() + screenPos, -1);
    }

    @Override
    public void render(ScreenPosition pos)
    {
        this.occupiedSlotsCount = 0;

        for (Potion potion : potionNameTranslations.keySet()) {

            if (mc.thePlayer.isPotionActive(potion)) {

                this.occupiedSlotsCount++;
                if (this.occupiedSlotsCount > this.capacity) return;

                renderEffect(
                        potionNameTranslations.get(potion),
                        (double) (mc.thePlayer.getActivePotionEffect(potion).getDuration()) / 21.125,
                        pos
                );

            }

        }

    }

    @Override
    public int getWidth()
    {
        return 130;
    }

    @Override
    public int getHeight()
    {
        return 150;
    }

    @Override
    public void renderDummy(ScreenPosition pos)
    {
        font.drawString(
                "potion effects",
                pos.getAbsoluteX() + (int) (0.5 * (getWidth() - font.getStringWidth("potion effects"))),
                pos.getAbsoluteY() + (int) (0.5 * (getHeight() - font.FONT_HEIGHT)),
                -1);
    }
}
