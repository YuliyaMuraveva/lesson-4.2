package ru.netology.manager;

import ru.netology.domain.Flight;
import ru.netology.repository.FlightRepository;

import java.util.Arrays;
import java.util.Comparator;

public class FlightManager {
    private FlightRepository repository;

    public FlightManager(FlightRepository repository) { this.repository = repository; }

    public void add(Flight flight) { repository.save(flight); }

    public void removeById(int id) { repository.removeById(id); }

    public Flight[] searchById(int id) {
        return repository.findById(id);
    }

    public Flight[] getAll() {
        return repository.findAll();
    }

    public Flight[] findAll(String departureAirport, String arrivalAirport, Comparator<Flight> comparator) {
        Flight[] offers = new Flight[0];
        for (Flight flight : repository.findAll()) {
            if (flight.matches(departureAirport, arrivalAirport)) {
                Flight[] tmp = new Flight[offers.length + 1];
                System.arraycopy(offers, 0, tmp, 0, offers.length);
                tmp[tmp.length - 1] = flight;
                offers = tmp;
            }
        }
        Arrays.sort(offers, comparator);
        return offers;
    }
}
