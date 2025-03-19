package be.kdg.integration2.mvpglobal.model;

import java.util.EnumSet;
import java.util.Set;

public class Piece {
    private final Set<Attribute> attributes;

    public Piece(Attribute... attributes) {
        this.attributes = EnumSet.of(attributes[0], attributes);
    }

    public boolean hasAttribute(Attribute attr) {
        return attributes.contains(attr);
    }

    public Set<Attribute> getAttributes() {
        return EnumSet.copyOf(attributes);
    }

    @Override
    public String toString() {
        return attributes.toString();
    }
}