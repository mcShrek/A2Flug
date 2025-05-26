package air;

public abstract class AbstractAir<T> implements AirInterface {
    private final String iataCode;
    private final String name;
    private final String country;

    public AbstractAir(String iataCode, String name, String country) {
        this.iataCode = iataCode;
        this.name = name;
        this.country = country;
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

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s]",getClass().getSimpleName(),
                iataCode, name, toStringAddon(), country);
    }
}
