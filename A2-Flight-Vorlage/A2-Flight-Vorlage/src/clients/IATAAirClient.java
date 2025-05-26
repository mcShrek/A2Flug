package clients;

import air.Airline;
import air.Airport;
import parser.IATAAirlineParser;
import parser.IATAAirportParser;
import queries.AirlineQuery;
import queries.AirportQuery;

import java.io.IOException;
import java.util.List;

public class IATAAirClient {

    public static final String AIRPORT_RESOURCE_ROOT = "A2-Flight-Vorlage/A2-Flight-Vorlage/iataairports/de.wikipedia.org/wiki";
    public static final String AIRLINE_RESOURCE_ROOT = "A2-Flight-Vorlage/A2-Flight-Vorlage/iataairlines/de.wikipedia.org/wiki";
    public static final String AIRPORT_PAGE = "Liste_der_IATA-Flughafen-Codes.html";
    public static final String AIRLINE_PAGE = "Liste_der_IATA-Airline-Codes.html";
    public static final String AIRPORT_CODES_DIR = "Liste_der_IATA-Codes";

    public static void main(String[] args) throws IOException {

        AirlineQuery airlineQuery = new AirlineQuery(AIRLINE_RESOURCE_ROOT, AIRLINE_PAGE);
        AirportQuery airportQuery = new AirportQuery(AIRPORT_RESOURCE_ROOT, AIRPORT_CODES_DIR, AIRPORT_PAGE);

        for ( Airline line : airlineQuery.filterByCountry("USA")) {
            System.out.println(line);
        }
        for ( Airline line : airlineQuery.filterByIATAPrefix("X")) {
            System.out.println(line);
          }
        for ( Airport airport : airportQuery.filterByCountry("Rumänien")) {
            System.out.println(airport);
        }
        for ( Airport port : airportQuery.filterByIATAPrefix("UR")) {
            System.out.println(port);
          }

    }
}
