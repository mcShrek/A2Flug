package air;

import java.util.Objects;

public class Airport implements AirInterface{

    private final String iataCode;
    private final String name;
    private final String location;
    private final String country;

    public Airport(String iataCode, String name, String location, String country) {
        this.iataCode = iataCode;
        this.name = name;
        this.location = location;
        this.country = country;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(iataCode, airport.iataCode) && Objects.equals(location, airport.location) && Objects.equals(country, airport.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataCode, location, country);
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s]",getClass().getSimpleName(),
                iataCode, location, country);
    }
}
