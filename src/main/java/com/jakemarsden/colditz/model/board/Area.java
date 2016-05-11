package com.jakemarsden.colditz.model.board;

/**
 * The different area of the game
 * <figure>
 * <img src="doc-files/originalBoard.jpg" alt="Original Colditz board" />
 * <figcaption>The original Colditz board. <a href="http://www.markalldridge.co.uk/escape-from-colditz.html">Source</a>.</figcaption>
 * </figure>
 *
 * @author jakemarsden
 */
public enum Area {
    /**
     * The areas marked in blue on the original board. Also includes all of the {@link Room}s, the
     * {@link Room#AssemblyArea} and the blue rooftops.
     */
    InnerCourtyard,
    /**
     * The areas marked in orange/light green on the original board, outside the
     * {@code InnerCourtyard} but still inside the barbed wire/outermost walls. Includes the two
     * bridges but not the river crossing.
     */
    OuterCourtyard,
    /**
     * The areas marked in dark green on the original board. Includes the river crossing but not the
     * two bridges. Basically anywhere outside the {@code OuterCourtyard} which is not a
     * {@code Target}.
     */
    Outside,
    /**
     * The 7 tiles marked by a bullseye on the original board. These tiles are special in that they
     * represent the win condition of the game - players can win by getting POWs to these tiles.
     */
    Target
}
