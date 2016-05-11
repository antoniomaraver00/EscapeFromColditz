package com.jakemarsden.colditz.model.board;

import com.jakemarsden.colditz.util.Json;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author jakemarsden
 */
@Component
@Slf4j
public class BoardLoader {
    @Value("classpath:boards/defaultBoard.yaml")
    private URL defaultBoardDefinition;


    @SneakyThrows(IOException.class)
    public Board loadBoard() {
        final JsonBoard jsonBoard = Json.getMapper().readValue(defaultBoardDefinition, JsonBoard.class);
        final ColditzBoard board = jsonBoard.toColditzBoard();
        logger.debug("Board loaded: {}", board);
        return board;
    }


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


        static JsonTile fromColditzTile(@NonNull ColditzTile tile) {
            final Coord coord = tile.getCoord();
            return new JsonTile(coord.getX(), coord.getY(), tile.getArea(), tile.getRoom(), tile.isSafe());
        }

        ColditzTile toColditzTile(@NonNull ColditzBoard board) {
            Room room = (this.room == null) ? Room.None : this.room;
            boolean safe = (this.safe == null) ? false : this.safe;
            return new ColditzTile(board, Coord.at(x, y), area, room, safe);
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class JsonBoard {
        @Getter
        @Setter
        private Set<JsonTile> tiles;

        static JsonBoard fromColditzBoard(@NonNull ColditzBoard board) {
            final Set<JsonTile> jsonTiles = board.getColditzTiles().stream()
                    .map(JsonTile::fromColditzTile)
                    .collect(Collectors.toSet());
            return new JsonBoard(jsonTiles);
        }

        ColditzBoard toColditzBoard() {
            final ColditzBoard board = new ColditzBoard();
            tiles.stream()
                    .map(tile -> tile.toColditzTile(board))
                    .forEach(board::addTile);
            return board;
        }
    }
}
