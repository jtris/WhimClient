package WhimClient.modules;

import java.io.File;

import WhimClient.FileManager;
import WhimClient.gui.hud.RenderComponent;
import WhimClient.gui.hud.ScreenPosition;

public abstract class DraggableModule extends Module implements RenderComponent {

    protected ScreenPosition pos;
    protected boolean state;

    public DraggableModule()
    {
        pos = loadPositionFromFile();
    }

    @Override
    public ScreenPosition load()
    {
        return pos;
    }

    @Override
    public void save(ScreenPosition pos)
    {
        this.pos = pos;
        savePositionToFile();
    }

    private File getFolder()
    {
        File folder = new File(FileManager.getModsDirectory(), this.getClass().getSimpleName());
        folder.mkdirs();
        return folder;
    }

    private void savePositionToFile()
    {
        FileManager.writeJsonToFile(new File(getFolder(), "pos.json"), pos);
    }

    private ScreenPosition loadPositionFromFile()
    {
        ScreenPosition loaded = FileManager.readFromJson(new File(getFolder(), "pos.json"), ScreenPosition.class);

        if (loaded == null) {
            loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
            this.pos = loaded;
            savePositionToFile();
        }
        return loaded;
    }

    public void saveStateToFile(boolean state_)
    {
        FileManager.writeJsonToFile(new File(getFolder(), "state.json"), state_);
    }

    public boolean loadStateFromFile()
    {
        boolean loaded;

        try {
            loaded = FileManager.readFromJson(new File(getFolder(), "state.json"), boolean.class);
        }
        catch (Exception e) {
            loaded = false;
            this.state = loaded;
            saveStateToFile(loaded);
        }
        return loaded;
    }

    public final int getLineOffset(ScreenPosition pos, int lineNum)
    {
        return pos.getAbsoluteY() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum)
    {
        return (font.FONT_HEIGHT + 3) * lineNum;
    }

}