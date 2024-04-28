package WhimClient.event.impl;

import WhimClient.event.EventCancelable;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class PacketReceivedEvent extends EventCancelable {

    private static final PacketReceivedEvent INSTANCE = new PacketReceivedEvent();

    public Packet<?> packet;
    public INetHandler packetListener;

    public static PacketReceivedEvent get(Packet<?> packet, INetHandler packetListener)
    {
        INSTANCE.setCancelled(false);
        INSTANCE.packet = packet;
        INSTANCE.packetListener = packetListener;
        return INSTANCE;
    }
}
