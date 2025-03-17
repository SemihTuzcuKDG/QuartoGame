package be.kdg.integration2.mvpglobal.model;

import java.util.EnumSet;
import java.util.Set;

public class Piece {
    private final Attribute height;
    private final Attribute color;
    private final Attribute shape;
    private final Attribute top; // SOLID or HOLLOW

    public Piece(Attribute height, Attribute color, Attribute shape, Attribute top) {
        this.height = height;
        this.color = color;
        this.shape = shape;
        this.top = top;
    }

    public Attribute getHeight() { return height; }
    public Attribute getColor() { return color; }
    public Attribute getShape() { return shape; }
    public Attribute getTop() { return top; }

    public String getImagePath() {
        String path = "/images/";
        path += height.name().toLowerCase() + "-"
                + color.name().toLowerCase() + "-"
                + shape.name().toLowerCase();

        if(top == Attribute.HOLLOW) {
            path += "-hole";
        }
        path += ".png";
        return path;
    }

    @Override
    public String toString() {
        return height + "-" + color + "-" + shape + "-" + top;
    }
}