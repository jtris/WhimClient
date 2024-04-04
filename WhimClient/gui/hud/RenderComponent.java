package WhimClient.gui.hud;

public interface RenderComponent extends RenderComponentBase {

    int getWidth();
    int getHeight();

    /* render to the screen */
    void render(ScreenPosition pos);

    /* called when rendering the `position screen` */
    default void renderDummy(ScreenPosition pos) {
        render(pos);
    }

    public default boolean isEnabled() {
        return true;
    }
}