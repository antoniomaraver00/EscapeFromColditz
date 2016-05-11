package com.jakemarsden.colditz.model.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a physical tile which POWs etc. can move to. Can be instantiated as part of a
 * {@link ColditzBoard} via a {@link BoardLoader}.
 *
 * @author jakemarsden
 * @see BoardLoader
 * @see ColditzBoard
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ColditzTile implements Tile {
    @Getter
    @NonNull
    private final ColditzBoard board;
    @Getter
    @NonNull
    private final Coord coord;

    @Getter
    @NonNull
    private final Area area;
    @Getter
    @NonNull
    private final Room room;
    @Getter
    private final boolean safe;


    ColditzTile(ColditzBoard board, Coord coord, Area area) {
        this(board, coord, area, Room.None, false);
    }


    @Override
    public boolean isNeighbourOf(@NonNull Tile other) {
        return other != this && coord.distanceFrom(other.getCoord()) <= board.getMaxNeighbourDistance();
    }

    @Override
    public Set<Tile> getNeighbouringTiles() {
        final Set<Tile> neighbouringTiles = board.getTiles().stream()
                .filter(this::isNeighbourOf)
                .collect(Collectors.toSet());
        return Collections.unmodifiableSet(neighbouringTiles);
    }


    @Override
    public String toString() {
        final String format = "%s[coord=%s, area=%s, room=%s]";
        return String.format(format, getClass().getSimpleName(), coord, area, room);
    }
}
