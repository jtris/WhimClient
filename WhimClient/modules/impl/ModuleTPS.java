package WhimClient.modules.impl;

import WhimClient.event.impl.PacketReceivedEvent;
import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ModuleTPS extends DraggableModule implements Listener {

    public ModuleTPS(){}

    private List<Long> ticks = new ArrayList<>();

    @Override
    public int getWidth() {
        return font.getStringWidth("TPS: 20");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawString("TPS: " + String.format("" + this.getTPS()), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @EventHandler
    private void onReceivePacket(PacketReceivedEvent event)
    {
        this.updateTPS(System.currentTimeMillis());
        if (event.packet instanceof S03PacketTimeUpdate) {
            this.updateTPS(System.currentTimeMillis());
        }
    }

    public void updateTPS(Long currentTime)
    {
        this.ticks.add(currentTime);
    }

    private int getTPS()
    {
        final long time = System.currentTimeMillis();
        //this.ticks.removeIf(tickTime -> tickTime + 1000 < time);
        return this.ticks.size();
    }
}
