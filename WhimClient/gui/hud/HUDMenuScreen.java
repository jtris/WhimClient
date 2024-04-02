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

    HUDMenuButton[] buttonArray = {
            mod1Button, mod2Button, mod3Button,
            mod4Button, mod5Button, mod6Button
    };

    public HUDMenuScreen()
    {

        for (int i = 0; i < 6; i++) {
            this.buttonArray[i] = new HUDMenuButton();
        }

        // load states of buttons
        ModuleInstances.modArmorStatus.setEnabled(ModuleInstances.modArmorStatus.loadStateFromFile());
        ModuleInstances.modKeystrokes.setEnabled(ModuleInstances.modKeystrokes.loadStateFromFile());
        ModuleInstances.modCPS.setEnabled(ModuleInstances.modCPS.loadStateFromFile());

        this.buttonArray[0].isEnabled = ModuleInstances.modArmorStatus.isEnabled();
        this.buttonArray[1].isEnabled = ModuleInstances.modKeystrokes.isEnabled();
        this.buttonArray[2].isEnabled = ModuleInstances.modCPS.isEnabled();

    }

    private int getMod1ButtonX()
    {
        return 80;
    }

    private int getMod1ButtonY()
    {
        return 60;
    }

    private int getMod2ButtonX(int screenWidth)
    {
        return (int) (screenWidth/2 - 0.5 * screenWidth/5);
    }

    private int getMod2ButtonY()
    {
        return 60;
    }

    private int getMod3ButtonX(int screenWidth)
    {
        return (int) (screenWidth/2 - 0.5 * screenWidth/5 + (screenWidth/2 - 80 - (screenWidth/5)/2));
    }

    private int getMod3ButtonY()
    {
        return 60;
    }

    private int getMod4ButtonX()
    {
        return 80;
    }

    private int getMod4ButtonY(int screenHeight)
    {
        return 60 + screenHeight/4 + screenHeight/20;
    }

    private int getMod5ButtonX(int screenWidth)
    {
        return (int) (screenWidth/2 - 0.5 * screenWidth/5);
    }

    private int getMod5ButtonY(int screenHeight)
    {
        return 60 + screenHeight/4 + screenHeight/20;
    }

    private int getMod6ButtonX(int screenWidth)
    {
        return (int) (screenWidth/2 - 0.5 * screenWidth/5 + (screenWidth/2 - 80 - (screenWidth/5)/2));
    }

    private int getMod6ButtonY(int screenHeight)
    {
        return 60 + screenHeight/4 + screenHeight/20;
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
        this.drawButton(getMod1ButtonX(), getMod1ButtonY(), this.buttonArray[0].isEnabled, "Armor Status");
        this.drawButton(getMod2ButtonX(this.width), getMod2ButtonY(), this.buttonArray[1].isEnabled, "Keystrokes");
        this.drawButton(getMod3ButtonX(this.width), getMod3ButtonY(), this.buttonArray[2].isEnabled, "CPS");
        this.drawButton(getMod4ButtonX(), getMod4ButtonY(this.height), this.buttonArray[3].isEnabled, "Mod 4");
        this.drawButton(getMod5ButtonX(this.width), getMod5ButtonY(this.height), this.buttonArray[4].isEnabled, "Mod 5");
        this.drawButton(getMod6ButtonX(this.width), getMod6ButtonY(this.height), this.buttonArray[5].isEnabled, "Mod 6");

        this.zLevel = zBackup;
    }

    private void drawButton(int x, int y, boolean isEnabled, String text)
    {

        int color = isEnabled ? this.colorEnabled : this.colorDisabled;

        GuiScreen.drawRect(
                x,
                y,
                x + this.width/5,
                y + this.height/4,
                color
        );

        fontRendererObj.drawString(text,
                (int) (x + (this.width/5 -fontRendererObj.getStringWidth(text)) / 2),
                (int) (y + 0.4 * this.height/4),
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

        int buttonWidth = this.width/5;
        int buttonHeight = this.height/4;

        int mod1X1 = getMod1ButtonX();
        int mod1X2 = mod1X1 + buttonWidth;
        int mod1Y1 = getMod1ButtonY();
        int mod1Y2 = mod1Y1 + buttonHeight;

        int mod2X1 = getMod2ButtonX(this.width);
        int mod2X2 = mod2X1 + buttonWidth;
        int mod2Y1 = getMod2ButtonY();
        int mod2Y2 = mod2Y1 + buttonHeight;

        int mod3X1 = getMod3ButtonX(this.width);
        int mod3X2 = mod3X1 + buttonWidth;
        int mod3Y1 = getMod3ButtonY();
        int mod3Y2 = mod3Y1 + buttonHeight;

        int mod4X1 = getMod4ButtonX();
        int mod4X2 = mod4X1 + buttonWidth;
        int mod4Y1 = getMod4ButtonY(this.height);
        int mod4Y2 = mod4Y1 + buttonHeight;

        int mod5X1 = getMod5ButtonX(this.width);
        int mod5X2 = mod5X1 + buttonWidth;
        int mod5Y1 = getMod5ButtonY(this.height);
        int mod5Y2 = mod5Y1 + buttonHeight;

        int mod6X1 = getMod6ButtonX(this.width);
        int mod6X2 = mod6X1 + buttonWidth;
        int mod6Y1 = getMod6ButtonY(this.height);
        int mod6Y2 = mod6Y1 + this.height;

        int[][] buttonProperties = {
                {mod1X1, mod1X2, mod1Y1, mod1Y2},
                {mod2X1, mod2X2, mod2Y1, mod2Y2},
                {mod3X1, mod3X2, mod3Y1, mod3Y2},
                {mod4X1, mod4X2, mod4Y1, mod4Y2},
                {mod5X1, mod5X2, mod5Y1, mod5Y2},
                {mod6X1, mod6X2, mod6Y1, mod6Y2}
        };

        for (int i = 0; i < 6; i++) {
            int modX1 = buttonProperties[i][0];
            int modX2 = buttonProperties[i][1];
            int modY1 = buttonProperties[i][2];
            int modY2 = buttonProperties[i][3];

            if (x >= modX1 && x <= modX2 && y >= modY1 && y <= modY2) {

                this.buttonArray[i].isEnabled = !this.buttonArray[i].isEnabled;

                if (i == 0) {
                    ModuleInstances.modArmorStatus.setEnabled(this.buttonArray[i].isEnabled);
                }
                else if (i == 1) {
                    ModuleInstances.modKeystrokes.setEnabled(this.buttonArray[i].isEnabled);
                }
                else if (i == 2) {
                    ModuleInstances.modCPS.setEnabled(this.buttonArray[i].isEnabled);
                }

                break;
            }
        }
    }

    @Override
    public void onGuiClosed()
    {
        // save module states
        boolean armorStatusState = ModuleInstances.modArmorStatus.isEnabled();
        boolean keystrokesState = ModuleInstances.modKeystrokes.isEnabled();
        boolean cpsState = ModuleInstances.modCPS.isEnabled();

        ModuleInstances.modArmorStatus.saveStateToFile(armorStatusState);
        ModuleInstances.modKeystrokes.saveStateToFile(keystrokesState);
        ModuleInstances.modCPS.saveStateToFile(cpsState);
    }
}
