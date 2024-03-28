package WhimClient.mods.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.mods.ModDraggable;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;


public class ModArmorStatus extends ModDraggable {

    @Override
    public int getWidth()
    {
        return 64;
    }

    @Override
    public int getHeight()
    {
        return 64;
    }

    @Override
    public void render(ScreenPosition pos)
    {
        for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
            ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
            renderItemStack(pos, i, itemStack);
        }
    }

    private void renderItemStack(ScreenPosition pos, int i, ItemStack itemStack)
    {
        if (itemStack == null) {
            return;
        }

        GL11.glPushMatrix();
        int yDifference = -16 * i + 48;

        if (itemStack.getItem().isDamageable()) {
            double damage = (itemStack.getMaxDamage() - itemStack.getItemDamage()) / ((double) itemStack.getMaxDamage()) * 100;
            font.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yDifference + 5, -1);
        }

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + yDifference);
        GL11.glPopMatrix();
    }

    @Override
    public void renderDummy(ScreenPosition pos)
    {
        renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
        renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
        renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
        renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
    }

}
