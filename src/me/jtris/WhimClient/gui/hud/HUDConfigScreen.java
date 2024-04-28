package WhimClient.gui.hud;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Predicate;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;


public class HUDConfigScreen extends GuiScreen {

    private final HashMap<RenderComponent, ScreenPosition> renderers = new HashMap<RenderComponent, ScreenPosition>();

    private Optional<RenderComponent> selectedRenderer = Optional.empty();

    private int prevX, prevY;

    /* loads HUD components */
    public HUDConfigScreen(HUDManager api) {
        Collection<RenderComponent> registeredRenderers = api.getRegisteredRenderers();

        for (RenderComponent ren : registeredRenderers) {

            if (!ren.isEnabled()) {
                continue;
            }

            ScreenPosition pos = ren.load();

            if (pos == null) {
                pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
            }

            adjustBounds(ren, pos);
            this.renderers.put(ren, pos);

        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();

        final float zBackup = this.zLevel;
        this.zLevel = 200;

        this.drawHollowRect(0, 0, this.width-1, this.height-1, 0xFFFF0000);

        for (RenderComponent renderer : renderers.keySet()) {
            ScreenPosition pos = renderers.get(renderer);

            renderer.renderDummy(pos);

            if (renderer.getWidth() != 0 && renderer.getHeight() != 0) {
                this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), 0xFF00FFFF);
            }
        }

        this.zLevel = zBackup;
    }

    /* draws all the edges of a rectangle to easily recognize the HUD config screen */
    public void drawHollowRect(int x, int y, int width, int height, int color) {
        this.drawHorizontalLine(x, x + width, y, color);
        this.drawHorizontalLine(x, x + width, y + height, color);

        this.drawVerticalLine(x, y + height, y, color);
        this.drawVerticalLine(x + width, y + height, y, color);
    }

    /* handles key inputs */
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            renderers.entrySet().forEach((entry) -> {
                entry.getKey().save(entry.getValue());
            });
            this.mc.displayGuiScreen(null);
        }
    }

    /* handles moving HUD components */
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int button, long timeSinceLastClick) {
        if (selectedRenderer.isPresent()) {
            moveSelectedRenderBy(mouseX - prevX, mouseY - prevY);
        }

        this.prevX = mouseX;
        this.prevY = mouseY;
    }

    private void moveSelectedRenderBy(int offsetX, int offsetY) {
        RenderComponent renderer = selectedRenderer.get();
        ScreenPosition pos = renderers.get(renderer);

        pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);
        adjustBounds(renderer, pos);
    }

    @Override
    public void onGuiClosed() {
        for (RenderComponent renderer : renderers.keySet()) {
            renderer.save(renderers.get(renderer));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    private void adjustBounds(RenderComponent renderer, ScreenPosition pos) {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

        int absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth(), 0)));
        int absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight(), 0)));

        pos.setAbsolute(absoluteX, absoluteY);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException {
        this.prevX = x;
        this.prevY = y;

        loadMouseOver(x, y);
    }

    private void loadMouseOver(int x, int y) {
        //this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
        MouseOverFinder mouseOverFinder = new MouseOverFinder(x, y);
        this.selectedRenderer = renderers.keySet().stream()
                .filter(r -> mouseOverFinder.test(r)).findFirst();
    }

    private abstract class MouseOverFinderABC implements Predicate<RenderComponent> {
        private int mouseX, mouseY;

        public MouseOverFinderABC(int x, int y) {
            this.mouseX = x;
            this.mouseY = y;
        }

        //@Override
        public boolean test(RenderComponent renderer) {
            ScreenPosition pos = renderers.get(renderer);

            int absoluteX = pos.getAbsoluteX();
            int absoluteY = pos.getAbsoluteY();

            if (mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) {
                if (mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean apply(RenderComponent arg0) {
            return false;
        }
    }

    private class MouseOverFinder extends MouseOverFinderABC {

        public MouseOverFinder(int x, int y) {
            super(x, y);
        }

    }
}