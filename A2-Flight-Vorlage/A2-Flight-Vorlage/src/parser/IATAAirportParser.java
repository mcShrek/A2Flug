package parser;

import air.Airport;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IATAAirportParser  extends AbstractParser{


    public IATAAirportParser(String resourceRoot, String iataPagePath, String iataCodesDir) {
        super(resourceRoot, iataPagePath, iataCodesDir);
    }

    public List<Airport> parse() throws IOException {

        ArrayList<Airport> airports = new ArrayList<Airport>();

        for(char ch = 'A'; ch<= 'Z'; ch++) {

            Path path = Paths.get(resourceRoot, iataCodesDir, ch + ".html");
            Scanner scanner = new Scanner(path.toUri().toURL().openStream(),
                    StandardCharsets.UTF_8);

            if (!Files.isReadable(path)) {
                System.out.println("Fehler");
                //System.out.println(iataPagePath.toAbsolutePath());
                continue;

            }

            String data;
            // öffnet einen Scanner zum Lesen Seite "Liste_der_IATA-Flughafen-Codes.html"
//        Scanner scanner = new Scanner(iataPagePath.toUri().toURL().openStream(),
//                StandardCharsets.UTF_8);
            data = scanner.useDelimiter("\\A").next();
            Pattern formatAirports = Pattern.compile(

//                        "<td>([A-Z]{3})</td>\\s*" +                                      // IATA-Code
//                                "<td>.*?</td>\\s*" +                                             // ICAO (nicht extrahiert)
//                                "<td>.*?>(.*?)</a></td>\\s*" +                                   // Name des Flughafens
//                                "<td>.*?>(.*?)</a></td>\\s*" +                                   // Stadt / Location
//                                "<td>.*?</td>\\s*" +                                             // Region (überspringen)
//                                "<td>.*?>(.*?)</a>",                                             // Land
//                        Pattern.DOTALL
                    "<tr>\\s*" +
                            "<td>([A-Z]{3})</td>\\s*" +                              // Gruppe 1: IATA
                            "<td>[^<]*</td>\\s*" +                                   // ICAO ignorieren
                            "<td>(?:<a[^>]*>)?([^<]+)(?:</a>)?</td>\\s*" +           // Gruppe 2: Name
                            "<td><a[^>]*>([^<]+)</a></td>\\s*" +                     // Gruppe 3: Stadt
                            "<td><a[^>]*>([^<]+)</a></td>\\s*" +                     // Gruppe 4: Region
                            "<td><a[^>]*>([^<]+)</a>\\s*</td>\\s*" +                 // Gruppe 5: Land
                            "</tr>",                               // Land
                    Pattern.DOTALL
            );

            Matcher matcher = formatAirports.matcher(data);
            while (matcher.find()) {

                String iataCode = matcher.group(1);
                String airportName = matcher.group(2);
                String city = matcher.group(3);
                String country = matcher.group(4);


                airports.add(new Airport(iataCode, airportName, city, country));


            }
        }
        //System.out.println("Flughäfen: " + counter );
        //System.out.println(data);
        for (Airport airport : airports) {
            System.out.println(airport);
        }
        return airports;
    }



}

