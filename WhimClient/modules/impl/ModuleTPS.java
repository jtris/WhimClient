package WhimClient.modules.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;

import java.util.ArrayList;
import java.util.List;

public class ModuleTPS extends DraggableModule {

    private List<Long> ticks;
    private int tps;
    private long lastTPSUpdateTime = -1;
    private long joinTime = -1;

    public ModuleTPS ()
    {
        this.ticks = new ArrayList<>();
    }

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
        int _tps = this.getTPS();
        String message = _tps == -1 ? "TPS: *20" : "TPS: " + _tps;
        font.drawString(message, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    public void updateTPS(Long currentTime)
    {
        this.ticks.add(currentTime);
    }

    private int getTPS()
    {
        final long time = System.currentTimeMillis();
        this.ticks.removeIf(tickTime -> tickTime + 1000 < time);

        if (System.currentTimeMillis() - this.joinTime < 5000) {
            return 20;
        }

        if (System.currentTimeMillis() - this.lastTPSUpdateTime < 3000) {
            return this.tps == 0 ? -1 : this.tps;
        }

        if (this.lastTPSUpdateTime == -1) {
            this.joinTime = System.currentTimeMillis();
        }
        this.lastTPSUpdateTime = System.currentTimeMillis();

        this.tps = (int) (this.ticks.size() / 2);
        this.tps = Math.min(this.tps, 20);

        /*
        due to the fact that we determine tps by the S32PacketConfirmTransaction packets,
        there can be areas on multiplayer servers where the client doesn't receive any
        of these packets, thus *20 will get rendered as an indicator that it's a placeholder
        value
        */
        if (this.tps == 0) {
            return -1;
        }

        return this.tps;
    }

}
