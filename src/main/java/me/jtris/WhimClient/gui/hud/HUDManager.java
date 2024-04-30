package WhimClient.gui.hud;

import WhimClient.event.EventManager;
import WhimClient.event.EventTarget;
import WhimClient.event.impl.RenderEvent;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;


public class HUDManager {

    private HUDManager() {}

    private static HUDManager instance = null;
    private static HUDConfigScreen hudConfigScreen = null;
    private static HUDMenuScreen hudMenuScreen = null;

    /* returns an existing instance or creates a new one */
    public static HUDManager getInstance()
    {
        if (instance != null) {
            return instance;
        }

        instance = new HUDManager();
        EventManager.register(instance);
        return instance;
    }

    private Set<RenderComponent> registeredRenderers = Sets.newHashSet();
    private Minecraft mc = Minecraft.getMinecraft();

    /* registers components */
    public void register(RenderComponent... renderers)
    {
        for (RenderComponent render : renderers) {
            this.registeredRenderers.add(render);
        }
    }

    public void unregister(RenderComponent... renderers)
    {
        for (RenderComponent render : renderers) {
            this.registeredRenderers.remove(render);
        }
    }

    public Collection<RenderComponent> getRegisteredRenderers()
    {
        return Sets.newHashSet(registeredRenderers);
    }

    public void openMenuScreen()
    {
        if (hudMenuScreen == null) {
            hudMenuScreen = new HUDMenuScreen();
        }
        mc.displayGuiScreen(hudMenuScreen);
    }

    public void openConfigScreen()
    {
        if (hudConfigScreen == null) {
            hudConfigScreen = new HUDConfigScreen(this);
        }
        hudConfigScreen.reloadActiveRenderers();
        mc.displayGuiScreen(hudConfigScreen);
    }

    @EventTarget
    public void onRender(RenderEvent e)
    {
        if (mc.currentScreen == null || mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiChat) {
            for (RenderComponent renderer : registeredRenderers) {
                callRenderer(renderer);
            }
        }
    }

    private void callRenderer(RenderComponent renderer)
    {
        if (!renderer.isEnabled()) {
            return;
        }

        ScreenPosition pos = renderer.load();

        if (pos == null) {
            pos = ScreenPosition.fromRelativePosition(0.5, 0.5); // center of the screen
        }

        renderer.render(pos);
    }
}