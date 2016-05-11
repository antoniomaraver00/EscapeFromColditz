package com.jakemarsden.colditz.model.board;

import javafx.geometry.Rectangle2D;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.Validate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the physical board which POWs etc. can move around in the game. Made up of a series of
 * {@link ColditzTile}s. Can be instantiated via a {@link BoardLoader}.
 *
 * @author jakemarsden
 * @see BoardLoader
 * @see ColditzTile
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class ColditzBoard implements Board {
    private final Set<ColditzTile> tiles = new HashSet<>();


    @Override
    public Tile getTileAt(@NonNull Coord coord) {
        final Optional<? extends Tile> result = tiles.stream()
                .filter(tile -> tile.getCoord().equals(coord))
                .findAny();
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public Set<Tile> getTiles() {
        return getColditzTiles().stream().collect(Collectors.toSet());
    }

    @Override
    public Rectangle2D getBoundingRect() {
        final List<Integer> xValues = tiles.stream()
                .map(tile -> tile.getCoord().getX())
                .collect(Collectors.toList());
        final List<Integer> yValues = tiles.stream()
                .map(tile -> tile.getCoord().getY())
                .collect(Collectors.toList());
        Optional<Integer> left = xValues.stream().min(Comparator.naturalOrder());
        Optional<Integer> right = xValues.stream().max(Comparator.naturalOrder());
        Optional<Integer> top = yValues.stream().min(Comparator.naturalOrder());
        Optional<Integer> bottom = yValues.stream().max(Comparator.naturalOrder());

        final int l = left.orElse(0);
        final int t = top.orElse(0);
        return new Rectangle2D(l, t, right.orElse(0) - l, bottom.orElse(0) - t);
    }


    Set<ColditzTile> getColditzTiles() {
        return Collections.unmodifiableSet(tiles);
    }

    void addTile(@NonNull ColditzTile tile) {
        Validate.isTrue(tile.getBoard() == this);
        Validate.isTrue(getTileAt(tile.getCoord()) == null, "A tile already exists at %s: %s", tile.getCoord(), tile);
        tiles.add(tile);
    }


    double getMaxNeighbourDistance() {
        return Math.hypot(10, 5);
    }


    @Override
    public String toString() {
        return String.format("%s[tiles=%s]", getClass().getSimpleName(), tiles);
    }
}
