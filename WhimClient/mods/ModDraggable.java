package WhimClient.mods;

import WhimClient.gui.hud.RenderComponent;
import WhimClient.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements RenderComponent {

    public final int getLineOffset(ScreenPosition pos, int lineNum) {
        return pos.getAbsoluteY() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum) {
        return (font.FONT_HEIGHT + 3) * lineNum;
    }

}