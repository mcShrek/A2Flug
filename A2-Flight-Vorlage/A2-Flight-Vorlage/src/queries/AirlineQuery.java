package queries;

import air.Airline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirlineQuery {

    private List<Airline> airlines;

    public AirlineQuery(String airlineResourceRoot, String airlinePage) throws IOException {
        // TODO nutzt den AirlineParser um die Liste der Airlines zu berechnen
    }
    public List<Airline> getAll() {
        return List.copyOf(airlines);
    }

    public List<Airline> filterByCountry(String country) {
        return new ArrayList<>();
    }

    public List<Airline> filterByIATAPrefix(String prefix) {
        return new ArrayList<>();
    }

}
