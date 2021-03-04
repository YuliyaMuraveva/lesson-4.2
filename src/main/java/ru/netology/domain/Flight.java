package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Flight implements Comparable<Flight>{
    private int id;
    private int price;
    private String departureAirport;
    private String arrivalAirport;
    private int flightTime;

    @Override
    public int compareTo(Flight o) {
        return price - o.price;
    }

    public boolean matches(String searchDeparture, String searchArrival) {
        if (this.getDepartureAirport().equalsIgnoreCase(searchDeparture) &&
                this.getArrivalAirport().equalsIgnoreCase(searchArrival)) {
            return true;
        }
        else { return false; }
    }
}
