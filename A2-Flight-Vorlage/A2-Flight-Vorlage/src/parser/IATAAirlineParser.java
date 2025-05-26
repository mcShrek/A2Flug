package parser;

import air.Airline;
import air.Airport;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IATAAirlineParser extends AbstractParser<Airline> {


    public IATAAirlineParser(String resourceRoot, String iataPagePath) {
        super(resourceRoot, iataPagePath);
    }


    public List<Airline> parse() throws IOException {
        ArrayList<Airline> airlines = new ArrayList<>();

        // öffnet einen Scanner zum Lesen Seite "Liste_der_IATA-Airline-Codes.html"
        Scanner scanner = new Scanner(iataPagePath.toUri().toURL().openStream(),
                StandardCharsets.UTF_8);

        //Inhalt wird in einen String verwandelt, nimmt alles aus dem Dokument als Inhalt
        String data = scanner.useDelimiter("\\A").next();


        //erzeugt Pattern um

        Pattern pattern = Pattern.compile(
                "<tr[^>]*>\\s*" + // start
                        "<td>([A-Z0-9]{2})</td>\\s*" +                         // erlaubt genau 2 Buchstaben oder Ziffern
                        "<td>(?:<a[^>]*>)?([^<]+)(?:</a>)?</td>\\s*" +         // Name
                        "<td>(?:<a[^>]*>)?([^<]+)(?:</a>)?</td>\\s*" +         // Land
                        "<td>([^<]*)</td>\\s*" + //Kommentar
                        "</tr>",
                Pattern.DOTALL


        );


        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String IATACode = matcher.group(1);
            String name = matcher.group(2);
            String country = matcher.group(3);
            String comment = matcher.group(4).trim();

            //airline wird zu Liste hinzugefügt
            airlines.add(new Airline(IATACode, name, country, comment));

        }
        scanner.close();
        return airlines;
    }
}