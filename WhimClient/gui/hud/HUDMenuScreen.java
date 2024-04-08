package WhimClient.gui.hud;

import WhimClient.modules.impl.ModuleInstances;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class HUDMenuScreen extends GuiScreen {

    int colorEnabled = new Color(24, 220, 79, 110).getRGB();
    int colorDisabled = new Color(224, 36, 45, 110).getRGB();

    HUDMenuButton mod1Button;
    HUDMenuButton mod2Button;
    HUDMenuButton mod3Button;
    HUDMenuButton mod4Button;
    HUDMenuButton mod5Button;
    HUDMenuButton mod6Button;
    HUDMenuButton mod7Button;
    HUDMenuButton mod8Button;
    HUDMenuButton mod9Button;
    HUDMenuButton mod10Button;
    HUDMenuButton mod11Button;
    HUDMenuButton mod12Button;

    HUDMenuButton[] buttonArray = {
            mod1Button, mod2Button, mod3Button,
            mod4Button, mod5Button, mod6Button,
            mod7Button, mod8Button, mod9Button,
            mod10Button, mod11Button, mod12Button
    };

    public HUDMenuScreen()
    {

        for (int i = 0; i < 12; i++) {
            this.buttonArray[i] = new HUDMenuButton();
        }

        // load states of buttons
        ModuleInstances.moduleArmorStatus.setEnabled(ModuleInstances.moduleArmorStatus.loadStateFromFile());
        ModuleInstances.moduleKeystrokes.setEnabled(ModuleInstances.moduleKeystrokes.loadStateFromFile());
        ModuleInstances.moduleCPS.setEnabled(ModuleInstances.moduleCPS.loadStateFromFile());
        ModuleInstances.moduleToggleSprint.setEnabled(ModuleInstances.moduleToggleSprint.loadStateFromFile());
        ModuleInstances.moduleXYZPosition.setEnabled(ModuleInstances.moduleXYZPosition.loadStateFromFile());
        ModuleInstances.moduleFPS.setEnabled(ModuleInstances.moduleFPS.loadStateFromFile());

        this.buttonArray[0].isEnabled = ModuleInstances.moduleArmorStatus.isEnabled();
        this.buttonArray[1].isEnabled = ModuleInstances.moduleKeystrokes.isEnabled();
        this.buttonArray[2].isEnabled = ModuleInstances.moduleCPS.isEnabled();
        this.buttonArray[3].isEnabled = ModuleInstances.moduleToggleSprint.isEnabled();
        this.buttonArray[4].isEnabled = ModuleInstances.moduleXYZPosition.isEnabled();
        this.buttonArray[5].isEnabled = ModuleInstances.moduleFPS.isEnabled();

    }

    private int getMod1ButtonX(int screenWidth)
    {
        return screenWidth/8 + screenWidth/60;
    }

    private int getMod1ButtonY(int screenHeight)
    {
        return screenHeight/7;
    }

    private int getMod2ButtonX(int screenWidth)
    {
        return getMod1ButtonX(screenWidth) + screenWidth/7 + screenWidth/20;
    }

    private int getMod2ButtonY(int screenHeight)
    {
        return getMod1ButtonY(screenHeight);
    }

    private int getMod3ButtonX(int screenWidth)
    {
        return getMod2ButtonX(screenWidth) + screenWidth/7 + screenWidth/20;
    }

    private int getMod3ButtonY(int screenHeight)
    {
        return getMod1ButtonY(screenHeight);
    }

    private int getMod4ButtonX(int screenWidth)
    {
        return getMod3ButtonX(screenWidth) + screenWidth/7 + screenWidth/20;
    }

    private int getMod4ButtonY(int screenHeight)
    {
        return getMod1ButtonY(screenHeight);
    }

    private int getMod5ButtonX(int screenWidth)
    {
        return getMod1ButtonX(screenWidth);
    }

    private int getMod5ButtonY(int screenHeight)
    {
        return getMod1ButtonY(screenHeight) + screenHeight/5 + screenHeight/15;
    }

    private int getMod6ButtonX(int screenWidth)
    {
        return getMod2ButtonX(screenWidth);
    }

    private int getMod6ButtonY(int screenHeight)
    {
        return getMod5ButtonY(screenHeight);
    }

    private int getMod7ButtonX(int screenWidth)
    {
        return getMod3ButtonX(screenWidth);
    }

    private int getMod7ButtonY(int screenHeight)
    {
        return getMod5ButtonY(screenHeight);
    }

    private int getMod8ButtonX(int screenWidth)
    {
        return getMod4ButtonX(screenWidth);
    }

    private int getMod8ButtonY(int screenHeight)
    {
        return getMod5ButtonY(screenHeight);
    }

    private int getMod9ButtonX(int screenWidth)
    {
        return getMod1ButtonX(screenWidth);
    }

    private int getMod9ButtonY(int screenHeight)
    {
        return getMod5ButtonY(screenHeight) + screenHeight/5 + screenHeight/15;
    }

    private int getMod10ButtonX(int screenWidth)
    {
        return getMod2ButtonX(screenWidth);
    }

    private int getMod10ButtonY(int screenHeight)
    {
        return getMod9ButtonY(screenHeight);
    }

    private int getMod11ButtonX(int screenWidth)
    {
        return getMod3ButtonX(screenWidth);
    }

    private int getMod11ButtonY(int screenHeight)
    {
        return getMod9ButtonY(screenHeight);
    }

    private int getMod12ButtonX(int screenWidth)
    {
        return getMod4ButtonX(screenWidth);
    }

    private int getMod12ButtonY(int screenHeight)
    {
        return getMod9ButtonY(screenHeight);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawDefaultBackground();
        this.drawMenuTitle();

        final float zBackup = this.zLevel;
        this.zLevel = 200;

        this.drawHollowRect(0, 0, this.width-1, this.height-1, 0xFFFF0000);

        // add mods here
        this.drawButton(getMod1ButtonX(this.width), getMod1ButtonY(this.height), this.buttonArray[0].isEnabled, "Armor Status");
        this.drawButton(getMod2ButtonX(this.width), getMod2ButtonY(this.height), this.buttonArray[1].isEnabled, "Keystrokes");
        this.drawButton(getMod3ButtonX(this.width), getMod3ButtonY(this.height), this.buttonArray[2].isEnabled, "CPS");
        this.drawButton(getMod4ButtonX(this.width), getMod4ButtonY(this.height), this.buttonArray[3].isEnabled, "Toggle Sprint");
        this.drawButton(getMod5ButtonX(this.width), getMod5ButtonY(this.height), this.buttonArray[4].isEnabled, "XYZ");
        this.drawButton(getMod6ButtonX(this.width), getMod6ButtonY(this.height), this.buttonArray[5].isEnabled, "FPS");
        this.drawButton(getMod7ButtonX(this.width), getMod7ButtonY(this.height), this.buttonArray[6].isEnabled, "Mod 7");
        this.drawButton(getMod8ButtonX(this.width), getMod8ButtonY(this.height), this.buttonArray[7].isEnabled, "Mod 8");
        this.drawButton(getMod9ButtonX(this.width), getMod9ButtonY(this.height), this.buttonArray[8].isEnabled, "Mod 9");
        this.drawButton(getMod10ButtonX(this.width), getMod10ButtonY(this.height), this.buttonArray[9].isEnabled, "Mod 10");
        this.drawButton(getMod11ButtonX(this.width), getMod11ButtonY(this.height), this.buttonArray[10].isEnabled, "Mod 11");
        this.drawButton(getMod12ButtonX(this.width), getMod12ButtonY(this.height), this.buttonArray[11].isEnabled, "Mod 12");

        this.zLevel = zBackup;
    }

    private void drawButton(int x, int y, boolean isEnabled, String text)
    {

        int color = isEnabled ? this.colorEnabled : this.colorDisabled;

        GuiScreen.drawRect(
                x,
                y,
                x + this.width/7,
                y + this.height/5,
                color
        );

        fontRendererObj.drawString(text,
                (int) (x + (this.width/7 -fontRendererObj.getStringWidth(text)) / 2),
                (int) (y + 0.4 * this.height/5),
                0xFFFFFFFF);
    }

    private void drawMenuTitle()
    {
        String title = "WhimClient HUD Menu";
        fontRendererObj.drawString(title, (float) this.width / 2 - ((float) fontRendererObj.getStringWidth(title) / 2), 20, -1, true);
    }

    public void drawHollowRect(int x, int y, int width, int height, int color)
    {
        this.drawHorizontalLine(x, x + width, y, color);
        this.drawHorizontalLine(x, x + width, y + height, color);

        this.drawVerticalLine(x, y + height, y, color);
        this.drawVerticalLine(x + width, y + height, y, color);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == Keyboard.KEY_ESCAPE) {

            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException {

        int buttonWidth = this.width/7;
        int buttonHeight = this.height/5;

        int mod1X = getMod1ButtonX(this.width);
        int mod1Y = getMod1ButtonY(this.height);

        int mod2X = getMod2ButtonX(this.width);
        int mod2Y = getMod2ButtonY(this.height);

        int mod3X = getMod3ButtonX(this.width);
        int mod3Y = getMod3ButtonY(this.height);

        int mod4X = getMod4ButtonX(this.width);
        int mod4Y = getMod4ButtonY(this.height);

        int mod5X = getMod5ButtonX(this.width);
        int mod5Y = getMod5ButtonY(this.height);

        int mod6X = getMod6ButtonX(this.width);
        int mod6Y = getMod6ButtonY(this.height);

        int mod7X = getMod7ButtonX(this.width);
        int mod7Y = getMod7ButtonY(this.height);

        int mod8X = getMod8ButtonX(this.width);
        int mod8Y = getMod8ButtonY(this.height);

        int mod9X = getMod9ButtonX(this.width);
        int mod9Y = getMod9ButtonY(this.height);

        int mod10X = getMod10ButtonX(this.width);
        int mod10Y = getMod10ButtonY(this.height);

        int mod11X = getMod11ButtonX(this.width);
        int mod11Y = getMod11ButtonY(this.height);

        int mod12X = getMod12ButtonX(this.width);
        int mod12Y = getMod12ButtonY(this.height);

        int[][] buttonProperties = {
                {mod1X, mod1X + buttonWidth, mod1Y, mod1Y + buttonHeight},
                {mod2X, mod2X + buttonWidth, mod2Y, mod2Y + buttonHeight},
                {mod3X, mod3X + buttonWidth, mod3Y, mod3Y + buttonHeight},
                {mod4X, mod4X + buttonWidth, mod4Y, mod4Y + buttonHeight},
                {mod5X, mod5X + buttonWidth, mod5Y, mod5Y + buttonHeight},
                {mod6X, mod6X + buttonWidth, mod6Y, mod6Y + buttonHeight},
                {mod7X, mod7X + buttonWidth, mod7Y, mod7Y + buttonHeight},
                {mod8X, mod8X + buttonWidth, mod8Y, mod8Y + buttonHeight},
                {mod9X, mod9X + buttonWidth, mod9Y, mod9Y + buttonHeight},
                {mod10X, mod10X + buttonWidth, mod10Y, mod10Y + buttonHeight},
                {mod11X, mod11X + buttonWidth, mod11Y, mod11Y + buttonHeight},
                {mod12X, mod12X + buttonWidth, mod12Y, mod12Y + buttonHeight}
        };

        for (int i = 0; i < 12; i++) {
            int modX1 = buttonProperties[i][0];
            int modX2 = buttonProperties[i][1];
            int modY1 = buttonProperties[i][2];
            int modY2 = buttonProperties[i][3];

            if (x >= modX1 && x <= modX2 && y >= modY1 && y <= modY2) {

                this.buttonArray[i].isEnabled = !this.buttonArray[i].isEnabled;

                if (i == 0) {
                    ModuleInstances.moduleArmorStatus.setEnabled(this.buttonArray[i].isEnabled);
                }
                else if (i == 1) {
                    ModuleInstances.moduleKeystrokes.setEnabled(this.buttonArray[i].isEnabled);
                }
                else if (i == 2) {
                    ModuleInstances.moduleCPS.setEnabled(this.buttonArray[i].isEnabled);
                }
                else if (i == 3) {
                    ModuleInstances.moduleToggleSprint.setEnabled(this.buttonArray[i].isEnabled);
                }
                else if (i == 4) {
                    ModuleInstances.moduleXYZPosition.setEnabled(this.buttonArray[i].isEnabled);
                }
                else if (i == 5) {
                    ModuleInstances.moduleFPS.setEnabled(this.buttonArray[i].isEnabled);
                }

                break;
            }
        }
    }

    @Override
    public void onGuiClosed()
    {
        // save module states
        boolean armorStatusState = ModuleInstances.moduleArmorStatus.isEnabled();
        boolean keystrokesState = ModuleInstances.moduleKeystrokes.isEnabled();
        boolean cpsState = ModuleInstances.moduleCPS.isEnabled();
        boolean toggleSprintState = ModuleInstances.moduleToggleSprint.isEnabled();
        boolean xyzPositionState = ModuleInstances.moduleXYZPosition.isEnabled();
        boolean fpsState = ModuleInstances.moduleFPS.isEnabled();

        ModuleInstances.moduleArmorStatus.saveStateToFile(armorStatusState);
        ModuleInstances.moduleKeystrokes.saveStateToFile(keystrokesState);
        ModuleInstances.moduleCPS.saveStateToFile(cpsState);
        ModuleInstances.moduleToggleSprint.saveStateToFile(toggleSprintState);
        ModuleInstances.moduleXYZPosition.saveStateToFile(xyzPositionState);
        ModuleInstances.moduleFPS.saveStateToFile(fpsState);
    }
}
