package com.jakemarsden.colditz.model.board;

import javafx.geometry.Rectangle2D;

import java.util.Set;

/**
 * @author jakemarsden
 */
public interface Board {

    Tile getTileAt(Coord coord);

    Set<Tile> getTiles();

    Rectangle2D getBoundingRect();
}
