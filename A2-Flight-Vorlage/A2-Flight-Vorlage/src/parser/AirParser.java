package parser;

import air.AirInterface;

import java.io.IOException;
import java.util.List;

public interface AirParser<T extends AirInterface> {
    List<T> parse() throws IOException;

}
