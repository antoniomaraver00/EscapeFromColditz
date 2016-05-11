package com.jakemarsden.colditz.model.board;

import javafx.geometry.Rectangle2D;

import java.util.Set;

/**
 * <figure>
 * <img src="doc-files/originalBoard.jpg" alt="Original Colditz board" />
 * <figcaption>The original Colditz board. <a href="http://www.markalldridge.co.uk/escape-from-colditz.html">Source</a>.</figcaption>
 * </figure>
 *
 * @author jakemarsden
 * @see BoardLoader
 * @see ColditzBoard
 * @see Tile
 */
public interface Board {

    /**
     * @return The tile at the given coordinate, or {@code null} if there is no tile there
     */
    Tile getTileAt(Coord coord);

    /**
     * @return All of the tiles which make up this board
     */
    Set<Tile> getTiles();

    /**
     * @return A rectangle describing the area encompassed by the left-most, top-most, right-most
     * and bottom-most tiles
     */
    Rectangle2D getBoundingRect();
}
