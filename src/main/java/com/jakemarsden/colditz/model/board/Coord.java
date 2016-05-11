package com.jakemarsden.colditz.model.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author jakemarsden
 */
@RequiredArgsConstructor(staticName = "at")
@EqualsAndHashCode
public final class Coord {
    private static final Coord ORIGIN = new Coord(0, 0);

    @Getter
    private final int x;
    @Getter
    private final int y;


    public static Coord origin() {
        return ORIGIN;
    }


    public double distanceFrom(Coord other) {
        return Math.hypot(getX() - other.getX(), getY() - other.getY());
    }


    @Override
    public String toString() {
        return String.format("[%s, %s]", x, y);
    }
}
