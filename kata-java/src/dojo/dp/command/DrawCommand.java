package dojo.dp.command;

import java.awt.*;

public class DrawCommand implements Command {
    protected Drawable drawable;

    private final Point position;

    public DrawCommand(Drawable drawable, Point position) {
        this.drawable = drawable;
        this.position = position;
    }

    @Override
    public void execute() {
        drawable.draw(position.x, position.y);
    }
}
