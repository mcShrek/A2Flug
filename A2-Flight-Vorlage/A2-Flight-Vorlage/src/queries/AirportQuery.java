package queries;

import air.Airport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirportQuery {
    private List<Airport> airports;

    public AirportQuery(String airportResourceRoot, String airportCodesDir, String airportPage) throws IOException {
        // TODO nutzt den AirportParser um die Liste der Airports zu berechnen
    }

    public List<Airport> getAll() {
        return List.copyOf(airports);
    }

    public List<Airport> filterByCountry(String country) {
        return new ArrayList<>();
    }

    public List<Airport> filterByIATAPrefix(String prefix) {
        return new ArrayList<>();
    }

}
