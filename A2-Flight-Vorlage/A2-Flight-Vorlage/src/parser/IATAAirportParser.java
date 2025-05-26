package parser;

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

public class IATAAirportParser  extends AbstractParser<Airport>{


    public IATAAirportParser(String resourceRoot, String iataPagePath, String iataCodesDir) {
        super(resourceRoot, iataPagePath, iataCodesDir);
    }

    public List<Airport> parse() throws IOException {

        ArrayList<Airport> airports = new ArrayList<Airport>();

        //Schleife, um über jede Datei zu iterieren
        for(char fileChar = 'A'; fileChar <= 'Z'; fileChar++) {

            Path path = Paths.get(resourceRoot, iataCodesDir, fileChar + ".html");

            // öffnet einen Scanner zum Lesen Seite "Liste_der_IATA-Flughafen-Codes.html"
            Scanner scanner = new Scanner(path.toUri().toURL().openStream(),
                    StandardCharsets.UTF_8);

            if (!Files.isReadable(path)) {
                System.out.println("Fehler beim Lesen der Datei mit: " + fileChar);

                continue;

            }


            String data = scanner.useDelimiter("\\A").next();
            Pattern formatAirports = Pattern.compile(

//
                    "<tr[^>]*>\\s*" +
                            "<td>([A-Z]{3})</td>\\s*" +                              // IATA Code muss aus drei Buchstaben bestehen
                            "<td>[^<]*</td>\\s*" +                                   // ICAO
                            "<td>(?:<a[^>]*>)?([^<]+)(?:</a>)?</td>\\s*" +           //Name
                            "<td><a[^>]*>([^<]+)</a>\\s*</td>\\s*" +                 //Stadt
                            "<td><a[^>]*>([^<]+)</a>\\s*</td>\\s*" +                 //Region
                            "<td><a[^>]*>([^<]+)</a>\\s*</td>\\s*" +                 // Land
                            "</tr>",
                    Pattern.DOTALL
            );

            Matcher matcher = formatAirports.matcher(data);
            while (matcher.find()) {

                String iataCode = matcher.group(1);
                String airportName = matcher.group(2);
                String region = matcher.group(3)+ " in " + matcher.group(4);
                String country = matcher.group(5);


                airports.add(new Airport(iataCode, airportName, region, country));


            }
            scanner.close();
        }
        return airports;
    }



}