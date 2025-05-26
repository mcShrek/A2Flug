package queries;

import air.AirInterface;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQuery<T extends AirInterface> {
    protected List<T> airList;

    public List<T> getAll() {
        return List.copyOf(airList);
    }

    public List<T> filterByCountry(String country) {
        List<T> result = new ArrayList<>();
        for (T item : airList) {
            if (item.getCountry().equalsIgnoreCase(country)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<T> filterByIATAPrefix(String prefix) {
        List<T> result = new ArrayList<>();
        for (T item : airList) {
            if (item.getIataCode().startsWith(prefix)) {
                result.add(item);
            }
        }
        return result;
    }
}