package com.jakemarsden.colditz.model.board;

import java.util.Set;

/**
 * <figure>
 * <img src="doc-files/originalBoard.jpg" alt="Original Colditz board" />
 * <figcaption>The original Colditz board. <a href="http://www.markalldridge.co.uk/escape-from-colditz.html">Source</a>.</figcaption>
 * </figure>
 *
 * @author jakemarsden
 * @see BoardLoader
 * @see ColditzTile
 * @see Board
 */
public interface Tile {

    /**
     * @return The board which contains this tile
     */
    Board getBoard();

    /**
     * @return Where abouts on the {@link Board} this tile lies
     */
    Coord getCoord();

    /**
     * @return True if this tile is inside the given {@code Area}
     */
    default boolean isIn(Area area) {
        return getArea().equals(area);
    }

    /**
     * @return The area which this tile is a part of. Always non-{@code null}.
     */
    Area getArea();

    /**
     * @return True if this tile is inside the given {@code Room}
     */
    default boolean isIn(Room room) {
        return getRoom().equals(room);
    }

    /**
     * @return The room which this tile is a part of, or {@link Room#None} if it isn't part of a
     * room. Always non-{@code null}.
     */
    Room getRoom();

    /**
     * @return True if this tile represents one of the "safe areas" marked in blue on the original
     * board. POWs are immune to arrest when on these tiles.
     */
    boolean isSafe();

    /**
     * @return True if this tile and the given are close enough for counters to move between
     * @see #getNeighbouringTiles()
     */
    boolean isNeighbourOf(Tile other);

    /**
     * @return The tiles which are close enough to this tile for counters to move to/from
     * @see #isNeighbourOf(Tile)
     */
    Set<Tile> getNeighbouringTiles();
}
