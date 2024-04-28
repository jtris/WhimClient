package WhimClient.modules.impl;

import WhimClient.gui.hud.ScreenPosition;
import WhimClient.modules.DraggableModule;

public class ModuleCompass extends DraggableModule {

    @Override
    public int getWidth() {
        return font.getStringWidth(Direction.NW.getName());
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawString(getDirection().getName(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    private Direction getDirection()
    {
        double yaw = mc.thePlayer.rotationYaw;

        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw > 180) {
            yaw -= 360;
        }

        for (Direction direction : Direction.values()) {
            if (direction.getStartYaw() >= yaw && yaw >= direction.getEndYaw()) {
                return direction;
            }
        }
        return Direction.N;
    }

    private enum Direction {
        N("North [-Z]", -157.5, 157.5),
        NW("North-West [-Z -X]", 157.5, 112.5),
        NE("North-East [-Z +X]", -112.5, -157.5),
        W("West [-X]", 112.5, 67.5),
        S("South [+Z]", 22.5, -22.5),
        SW("South-West [+Z -X]", 67.5, 22.5),
        SE("South-East [+Z +X]", -22.5, -67.6),
        E("East [+X]", -67.6, -112.5);

        private final String stringRepresentation;
        private final double startYaw;
        private final double endYaw;

        Direction(String stringRepresentation, double startYaw, double endYaw)
        {
            this.stringRepresentation = stringRepresentation;
            this.startYaw = startYaw;
            this.endYaw = endYaw;
        }

        public String getName()
        {
            return stringRepresentation;
        }

        public double getStartYaw()
        {
            return startYaw;
        }

        public double getEndYaw()
        {
            return endYaw;
        }
    }
}
