package WhimClient;

import WhimClient.event.EventTarget;
import WhimClient.event.impl.ClientTickEvent;

public class TestClass {

    @EventTarget
    public void onTick(ClientTickEvent e)
    {
        System.out.println("client tick!");
    }
}
