package air;

import java.util.Objects;

public class Airline extends AbstractAir {

    private final String comment;

    public Airline(String iataCode, String name, String comment, String country) {
        super(iataCode, name, country);
        this.comment = comment;
    }

    public String getComment() {

        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return super.equals(o) && Objects.equals(comment, airline.comment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), comment);
    }

    @Override
    public String toStringAddon() {
        return this.comment;
    }
}
