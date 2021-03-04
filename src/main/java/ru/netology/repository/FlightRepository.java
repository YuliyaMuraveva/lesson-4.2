package ru.netology.repository;

import ru.netology.domain.Flight;
import ru.netology.domain.NotFoundException;

public class FlightRepository {
    private Flight[] flights = new Flight[0];

    public void save(Flight flight) {
        int length = flights.length + 1;
        Flight[] newFlights = new Flight[length];
        System.arraycopy(flights, 0, newFlights, 0, flights.length);
        newFlights[newFlights.length - 1] = flight;
        flights = newFlights;
    }

    public Flight[] findAll() {
        return flights;
    }

    public Flight[] findById(int id) {
        Flight[] tmp = new Flight[1];
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                tmp[0] = flight;
            }
        }
        if (tmp[0] == null) {
            throw new NotFoundException("Flight with id: " + id + " not found");
        } else {
            return tmp;
        }
    }

    public void removeById(int id) {
        findById(id);
        int length = flights.length - 1;
        Flight[] tmp = new Flight[length];
        int index = 0;
        for (Flight flight : flights) {
            if (flight.getId() != id) {
                tmp[index] = flight;
                index++;
            }
        }
        flights = tmp;
    }
}
