package WhimClient.event.impl;

import WhimClient.event.EventCancelable;

public class EventKeyPressed extends EventCancelable {

    private final int key;

    public EventKeyPressed(int key)
    {
        this.key = key;
    }

    public int getKey()
    {
        return this.key;
    }
}
