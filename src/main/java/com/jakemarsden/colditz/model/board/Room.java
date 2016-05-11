package com.jakemarsden.colditz.model.board;

import com.jakemarsden.colditz.model.EscapeEquipment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.jakemarsden.colditz.model.EscapeEquipment.*;

/**
 * @author jakemarsden
 */
@RequiredArgsConstructor
public enum Room {

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
