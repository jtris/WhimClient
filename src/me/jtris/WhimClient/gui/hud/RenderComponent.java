package WhimClient.gui.hud;

public interface RenderComponent extends RenderComponentBase {

    int getWidth();
    int getHeight();

    /* render to the screen */
    void render(ScreenPosition pos);

    /* called when rendering the `position screen` */
    default void renderDummy(ScreenPosition pos) {

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        render(pos);
    }

    public default boolean isEnabled() {
        return true;
    }
}