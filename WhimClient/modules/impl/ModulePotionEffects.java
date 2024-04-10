package WhimClient.modules.impl;

import WhimClient.event.EventTarget;
import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.potion.Potion;

import java.util.HashMap;

public class ModulePotionEffects extends DraggableModule {

    private final Potion[] potions = {
            Potion.moveSpeed, Potion.moveSlowdown, Potion.digSpeed, Potion.digSlowdown,
            Potion.damageBoost, Potion.heal, Potion.harm, Potion.jump, Potion.confusion,
            Potion.regeneration, Potion.resistance, Potion.fireResistance,
            Potion.waterBreathing, Potion.invisibility, Potion.blindness,
            Potion.nightVision, Potion.hunger, Potion.weakness, Potion.poison,
            Potion.wither, Potion.healthBoost, Potion.absorption, Potion.saturation
    };

    private final HashMap<String, String> potionNameTranslations = new HashMap<>();

    private final int capacity = this.getHeight() / (font.FONT_HEIGHT + 4);
    private int occupiedSlotsCount = 0;

    public ModulePotionEffects()
    {
        this.potionNameTranslations.put("potion.moveSpeed", "speed");
        this.potionNameTranslations.put("potion.moveSlowdown", "slowness");
        this.potionNameTranslations.put("potion.digSpeed", "haste");
        this.potionNameTranslations.put("potion.digSlowdown", "mining fatigue");
        this.potionNameTranslations.put("potion.damageBoost", "strength");
        this.potionNameTranslations.put("potion.heal", "instant health");
        this.potionNameTranslations.put("potion.harm", "instant damage");
        this.potionNameTranslations.put("potion.jump", "jump boost");
        this.potionNameTranslations.put("potion.confusion", "nausea");
        this.potionNameTranslations.put("potion.regeneration", "regeneration");
        this.potionNameTranslations.put("potion.resistance", "resistance");
        this.potionNameTranslations.put("potion.fireResistance", "fire resistance");
        this.potionNameTranslations.put("potion.waterBreathing", "water breathing");
        this.potionNameTranslations.put("potion.invisibility", "invisibility");
        this.potionNameTranslations.put("potion.blindness", "blindness");
        this.potionNameTranslations.put("potion.nightVision", "night vision");
        this.potionNameTranslations.put("potion.hunger", "hunger");
        this.potionNameTranslations.put("potion.weakness", "weakness");
        this.potionNameTranslations.put("potion.poison", "poison");
        this.potionNameTranslations.put("potion.wither", "wither");
        this.potionNameTranslations.put("potion.healthBoost", "health boost");
        this.potionNameTranslations.put("potion.absorption", "absorption");
        this.potionNameTranslations.put("potion.saturation", "saturation");
    }

    private void renderEffect(String name, double duration, ScreenPosition hudPos)
    {
                System.out.print(name);

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
                System.out.println("height = " + getHeight());
                System.out.println("pos = " + screenPos + "\n");

                font.drawString(durationString, hudPos.getAbsoluteX() + 2, hudPos.getAbsoluteY() + screenPos, -1);
                System.out.println(durationString);
    }

    @Override
    public void render(ScreenPosition pos)
    {
        this.occupiedSlotsCount = 0;

        for (Potion potion : this.potions) {

            if (mc.thePlayer.isPotionActive(potion)) {

                this.occupiedSlotsCount++;
                if (this.occupiedSlotsCount > this.capacity) return;

                renderEffect(
                        potionNameTranslations.get(potion.getName()),
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
