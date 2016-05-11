package com.jakemarsden.colditz.model.board;

import java.util.Set;

/**
 * @author jakemarsden
 */
public interface Tile {

    Board getBoard();

    Coord getCoord();

    default boolean isIn(Area area) {
        return getArea().equals(area);
    }

    Area getArea();

    default boolean isIn(Room room) {
        return getRoom().equals(room);
    }

    Room getRoom();

    boolean isSafe();

    boolean isNeighbourOf(Tile other);

    Set<Tile> getNeighbouringTiles();
}
