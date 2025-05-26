package queries;

import air.Airline;
import parser.IATAAirlineParser;


import java.io.IOException;


public class AirlineQuery extends AbstractQuery<Airline> {

    public AirlineQuery(String airlineResourceRoot, String airlinePage) throws IOException {
        super(new IATAAirlineParser(airlineResourceRoot, airlinePage).parse());
    }
}
