package air;

import java.util.Objects;

public class Airport extends AbstractAir {

    private final String location;

    public Airport(String iataCode, String name, String location, String country) {
        super(iataCode, name, country);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return super.equals(o) && Objects.equals(location, airport.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), location);
    }



    @Override
    public String toStringAddon() {
        return this.location;
    }
}
