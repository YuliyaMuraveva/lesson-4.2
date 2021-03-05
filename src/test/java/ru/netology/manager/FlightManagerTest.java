package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Flight;
import ru.netology.domain.FlightByFlightTimeAscComparator;
import ru.netology.domain.NotFoundException;
import ru.netology.repository.FlightRepository;

import static org.junit.jupiter.api.Assertions.*;

class FlightManagerTest {
    private FlightRepository repository = new FlightRepository();
    private FlightManager manager = new FlightManager(repository);
    private FlightByFlightTimeAscComparator comparator = new FlightByFlightTimeAscComparator();
    private Flight spbMoscow = new Flight(1, 2000, "LED", "DME", 100);
    private Flight moscowSpb1 = new Flight(2, 2500, "DME", "LED", 90);
    private Flight moscowRostov = new Flight(3, 5000, "DME", "ROV", 120);
    private Flight moscowSpb2 = new Flight(4, 1500, "DME", "LED", 85);
    private Flight spbRostov = new Flight(5, 5500, "LED", "ROV", 160);
    private Flight moscowSpb3 = new Flight(6, 7000, "DME", "LED", 85);
    private Flight spbMoscow2 = new Flight(7, 2000, "LED", "DME", 95);

    @BeforeEach
    public void setUp() {
        manager.add(spbMoscow);
        manager.add(moscowSpb1);
        manager.add(moscowRostov);
        manager.add(moscowSpb2);
        manager.add(spbRostov);
        manager.add(moscowSpb3);
        manager.add(spbMoscow2);
    }

    @Test
    void shouldSearchAndSortByTime() {
        Flight[] actual = manager.findAll("DME", "LED", comparator);
        Flight[] expected = new Flight[]{moscowSpb2, moscowSpb3, moscowSpb1};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchInOne() {
        Flight[] actual = manager.findAll("dme", "rov", comparator);
        Flight[] expected = new Flight[]{moscowRostov};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchNotInList() {
        Flight[] actual = manager.findAll("Dme", "Kzn", comparator);
        Flight[] expected = new Flight[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdInList() {
        manager.removeById(2);
        Flight[] actual = manager.getAll();
        Flight[] expected = new Flight[]{spbMoscow, moscowRostov, moscowSpb2, spbRostov, moscowSpb3, spbMoscow2};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowException() {
        assertThrows(NotFoundException.class, () -> manager.removeById(8));
    }

    @Test
    void shouldSearchById() {
        Flight[] actual = manager.searchById(2);
        Flight[] expected = new Flight[]{moscowSpb1};
        assertArrayEquals(expected, actual);
    }
}
