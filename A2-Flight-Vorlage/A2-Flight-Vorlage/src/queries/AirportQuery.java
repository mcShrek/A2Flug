package queries;

import air.Airport;
import parser.IATAAirportParser;
import java.io.IOException;

public class AirportQuery extends AbstractQuery<Airport> {


    public AirportQuery(String airportResourceRoot, String airportCodesDir, String airportPage) throws IOException {

        super(new IATAAirportParser(airportResourceRoot, airportPage, airportCodesDir).parse());
    }

}