package air;

import java.util.Objects;

public class Airline implements AirInterface {

    private final String iataCode;
    private final String name;
    private final String country;
    private final String comment;

    public Airline(){
        this("","","","");
    }

    public Airline(String iataCode, String name, String country, String comment) {
        this.iataCode = iataCode;
        this.name = name;
        this.country = country;
        this.comment = comment;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getName() {

        return name;
    }

    public String getCountry() {

        return country;
    }

    public String getComment() {

        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return Objects.equals(iataCode, airline.iataCode) && Objects.equals(name, airline.name) && Objects.equals(country, airline.country) && Objects.equals(comment, airline.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataCode, name, country, comment);
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s]", getClass().getSimpleName(),iataCode, name,country, comment);
    }
}
