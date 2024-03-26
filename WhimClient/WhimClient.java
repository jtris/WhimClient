package WhimClient;


import WhimClient.event.EventManager;

public class WhimClient {

    private static final WhimClient INSTANCE = new WhimClient();

    public static WhimClient getInstance()
    {
        return INSTANCE;
    }

    public void init()
    {
        EventManager.register(new TestClass());
        System.out.println("test class registered!");
    }

    public void shutdown()
    {
        System.out.println("shutting down!");

    }
}
