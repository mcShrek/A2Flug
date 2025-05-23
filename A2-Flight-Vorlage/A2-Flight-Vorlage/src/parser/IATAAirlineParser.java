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
        String data = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "Inhalt ist leer"; //ggf durch files ersetzen

        scanner.close();

        Pattern pattern = Pattern.compile(
//                "<tr[^>]*>\\s*" +
//                "<td>([A-Z0-9]{2})</td>\\s*" +
//                        "<td>(?:<a[^>]*>)?(.*?)(?:</a>)?</td>\\s*" +
//                        "<td>(?:(?:<a[^>]*>)?(.*?)(?:</a>)?|.*?)</td>\\s*" +
//                        "<td>(.*?)\\s*</td>" +                               // Gruppe 4: Comment
//                "</tr>",
                "<tr[^>]*>\\s*" +
                        "<td>([A-Z0-9]{2})</td>\\s*" +                         // Gruppe 1: IATA
                        "<td>(?:<a[^>]*>)?([^<]+)(?:</a>)?</td>\\s*" +         // Gruppe 2: Name
                        "<td>(?:<a[^>]*>)?([^<]+)(?:</a>)?</td>\\s*" +         // Gruppe 3: Country
                        "<td>((?:(?:<a[^>]*>)?([^<]*)</a>?|[^<>])*)</td>\\s*" +// Gruppe 4: Kommentar: alles Sichtbare (auch in <a>)
                        "</tr>",
//                "<td>([A-Z0-9]{2})</td>\\s*" +                                     // Code
//                        "<td>(?:<a[^>]*>)?(.*?)(?:</a>)?</td>\\s*" +                       // Airline-Name
//                        "<td>(?:<a[^>]*>)?(.*?)(?:</a>)?</td>\\s*" +                       // Land
//                       "<td>(.*?)</td>",
              //  "<tr[^>]*>\\s*" +
//                        "<td>([A-Z0-9]{2})</td>\\s*" +                                   // Code
//                        "<td>(?:<a[^>]*>)?(.*?)(?:</a>)?</td>\\s*" +                     // Name
//                        "<td>(?:<a[^>]*>)?(.*?)(?:</a>)?</td>\\s*" +                     // Land
//                        "<td>(.*?)</td>\\s*" +                                           // Kommentar (kann mehrere <a> enthalten)
//                        "</tr>",
//                "<tr[^>]*>\\s*" +
//                        "<td>([A-Z0-9]{2})</td>\\s*" +                           // IATA-Code
//                        "<td>(?:<a[^>]*>)?([^<]+)(?:</a>)?</td>\\s*" +           // Name
//                        "<td>(?:<a[^>]*>)?([^<]+)(?:</a>)?</td>\\s*" +           // Country
//                        "<td>(.*?)</td>\\s*" +                                   // Kommentar (HTML erlaubt!)
//                        "</tr>",
                 Pattern.DOTALL


        );

        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String IATACode = matcher.group(1);
            String name = matcher.group(2);
            String country = matcher.group(3);
            String comment = matcher.group(4).replaceAll("<[^>]+>", "").trim();


            airlines.add(new Airline(IATACode, name, country, comment));

        }
        //test methode, muss gelöscht werdenn
        for (Airline airline : airlines) {
            System.out.println(airline);
        }


        return airlines;
    }


}