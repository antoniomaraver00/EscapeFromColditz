package com.jakemarsden.colditz.model.board;

import com.jakemarsden.colditz.util.Json;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Set;


/**
 * Defines how Colditz boards are stored and loaded into the game. Instances must be managed by
 * Spring to work correctly.
 *
 * @author jakemarsden
 * @see ColditzBoard
 * @see ColditzTile
 */
@Component
@Slf4j
public class BoardLoader {
    @Value("classpath:boards/defaultBoard.yaml")
    private URL defaultBoardDefinition;


    /**
     * Loads and returns an instance of the default game board
     * <figure>
     * <img src="doc-files/originalBoard.jpg" alt="Original Colditz board" />
     * <figcaption>The original Colditz board. <a href="http://www.markalldridge.co.uk/escape-from-colditz.html">Source</a>.</figcaption>
     * </figure>
     */
    @SneakyThrows(IOException.class)
    public Board loadDefaultBoard() {
        final JsonBoard jsonBoard = Json.getMapper().readValue(defaultBoardDefinition, JsonBoard.class);
        final ColditzBoard board = jsonBoard.toColditzBoard();
        logger.debug("Board loaded: {}", board);
        return board;
    }


    /**
     * How a {@link ColditzTile} will be represented in JSON
     */
    @AllArgsConstructor
    @NoArgsConstructor
    private static class JsonTile {
        @Getter
        @Setter
        private int x;
        @Getter
        @Setter
        private int y;
        @Getter
        @Setter
        private Area area;
        @Getter
        @Setter
        private Room room;
        @Getter
        @Setter
        private Boolean safe = false;


        ColditzTile toColditzTile(@NonNull ColditzBoard board) {
            Room room = (this.room == null) ? Room.None : this.room;
            boolean safe = (this.safe == null) ? false : this.safe;
            return new ColditzTile(board, Coord.at(x, y), area, room, safe);
        }
    }

    /**
     * How a {@link ColditzBoard} will be represented in JSON
     */
    @AllArgsConstructor
    @NoArgsConstructor
    private static class JsonBoard {
        @Getter
        @Setter
        private Set<JsonTile> tiles;

        ColditzBoard toColditzBoard() {
            final ColditzBoard board = new ColditzBoard();
            tiles.stream()
                    .map(tile -> tile.toColditzTile(board))
                    .forEach(board::addTile);
            return board;
        }
    }
}
