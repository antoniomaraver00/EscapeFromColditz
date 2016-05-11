package com.jakemarsden.colditz.model.board;

import com.jakemarsden.colditz.model.EscapeEquipment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.jakemarsden.colditz.model.EscapeEquipment.*;

/**
 * The different rooms of the game, as marked on the original Colidtz board.
 * <figure>
 * <img src="doc-files/originalBoard.jpg" alt="Original Colditz board" />
 * <figcaption>The original Colditz board. <a href="http://www.markalldridge.co.uk/escape-from-colditz.html">Source</a>.</figcaption>
 * </figure>
 *
 * @author jakemarsden
 */
@RequiredArgsConstructor
public enum Room {

    /**
     * This tile is not inside a {@code Room}
     */
    None(null),

    AssemblyArea(null),
    Canteen(Rations),
    Chapel(Rope),
    Dentist(Key),
    GuardHouse(Pass),
    InterviewRoom(Map),
    Kitchen(WireCutters),
    Laundry(Disguise),
    Office(Map),
    OfficersQuarters(Compass),
    OrderliesQuarters(Pass),
    ParcelOffice(Rations),
    Showers(Key),
    SickBay(WireCutters),
    Store(Rope),
    Theatre(Disguise),
    Wc(Compass);


    /**
     * The equipment inside the room (eg. players can earn equipment by moving two POWs to rooms
     * containing the same equipment type)
     */
    @Getter
    private final EscapeEquipment equipment;
}
