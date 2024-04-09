package WhimClient.modules.impl;

import WhimClient.event.EventTarget;
import WhimClient.event.impl.EventKeyPressed;
import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class ModuleFreelook extends DraggableModule {

    public boolean freelookToggled = false;
    private int previousPerspective = 0;
    private float cameraYaw = 0F;
    private float cameraPitch = 0F;

    @EventTarget
    public void keyboardEvent(EventKeyPressed event)
    {
        if (event.getKey() == mc.gameSettings.CLIENT_FREELOOK.getKeyCode()) {

            if (Keyboard.getEventKeyState()) {
                freelookToggled = !freelookToggled;

                cameraYaw = mc.thePlayer.rotationYaw;
                cameraPitch = mc.thePlayer.rotationPitch;

                if (freelookToggled) {
                    //previousPerspective = mc.gameSettings.thirdPersonView;
                    previousPerspective = mc.gameSettings.showDebugInfo;
                    mc.gameSettings.showDebugInfo = 1;
                }
                else {
                    mc.gameSettings.showDebugInfo = previousPerspective;
                }
            }
            else {
                freelookToggled = false;
                mc.gameSettings.showDebugInfo = previousPerspective;
            }
        }

        if (Keyboard.getEventKey() == mc.gameSettings.keyBindSmoothCamera.getKeyCode()) { //mc.gameSettings.keyBindTogglePerspective.getKeyCode()
            freelookToggled = false;
        }
    }

    public float getCameraYaw()
    {
        if (freelookToggled) {
            return cameraYaw;
        }
        return mc.thePlayer.rotationYaw;
    }

    public float getCameraPitch()
    {
        if (freelookToggled) {
            return cameraPitch;
        }
        return mc.thePlayer.rotationPitch;
    }

    public boolean overrideMouse() {
        if (!mc.inGameHasFocus && Display.isActive()) {
            return false;
        }

        if (!freelookToggled) {
            return true;
        }

        mc.mouseHelper.mouseXYChange();
        float f1 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        //float f2 = (f1 * 3.0F * 8.0F);
        float f2 = f1 * f1 * f1 * 8.0F;
        float f3 = (float) mc.mouseHelper.deltaX * f2;
        float f4 = (float) mc.mouseHelper.deltaY * f2;

        cameraYaw += f3 * 0.15F;
        cameraPitch += f4 * 0.15F;

        if (cameraPitch < -90) {
            cameraPitch = -90;
        }

        if (cameraPitch > 90) {
            cameraPitch = 90;
        }

        return false;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void render(ScreenPosition pos) {}
}
