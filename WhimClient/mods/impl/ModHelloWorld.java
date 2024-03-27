package WhimClient.mods.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.mods.ModDraggable;

public class ModHelloWorld extends ModDraggable {

    private ScreenPosition pos;

    @Override
    public int getWidth() {
        return font.getStringWidth("Hello World (Dummy Rendering)");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawString("Hello World", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1); // -1 == white

    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawString("Hello World (Dummy Rendering)", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, 0xFF00FF00);
    }

    @Override
    public void save(ScreenPosition pos) {
        this.pos = pos;
    }

    @Override
    public ScreenPosition load() {
        return pos;
    }

}