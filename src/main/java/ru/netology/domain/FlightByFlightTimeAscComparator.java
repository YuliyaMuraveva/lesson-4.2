package ru.netology.domain;

import java.util.Comparator;

public class FlightByFlightTimeAscComparator implements Comparator<Flight> {
    public int compare(Flight o1, Flight o2) {
        return o1.getFlightTime() - o2.getFlightTime();
    }
}
