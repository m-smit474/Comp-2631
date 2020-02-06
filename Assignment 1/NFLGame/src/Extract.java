import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Extract {

    public static void main(String[] args) {
        Document website;
        String Url;
        String year;

        if (args.length == 0) {
            year = "2018";
            Url = "https://www.pro-football-reference.com/years/2018/games.htm";
        } else {
            year = args[0];
            Url = "https://www.pro-football-reference.com/years/" + year + "/games.htm";
        }

        website = getWebsite(Url);

        Element games = website.getElementById("games");
        Elements table = games.getElementsByTag("tbody");
        Elements rows = table.first().getElementsByTag("tr");

        try (PrintStream fileOut = new PrintStream("NFL_Stats_" + year + ".txt")) {

            fileOut.println("Year,Week,Day,Home,Home Score,Away," + "Away Score,Date,Result,Home Yards,Away Yards,"
                    + "Home Turnovers,Away Turnovers");

            for (Element row : rows) {

                if (!row.hasClass("thead")) {

                    Elements columns = row.children();

                    String week = columns.first().text();
                    String day = columns.next().first().text();
                    String date = columns.next().next().first().text();
                    String result = columns.next().next().next().next().next().first().text();
                    String homeScore;
                    String awayScore;
                    String home;
                    String away;
                    String homeYards;
                    String awayYards;
                    String homeTO;
                    String awayTO;


                    if (result.equals("@")) {
                        // Away team won
                        result = "A";
                        away = columns.next().next().next().next().first().text();
                        home = columns.next().next().next().next().next().next().first().text();
                        homeScore = columns.next().next().next().next().next().next().next().next().next().first()
                                .text();
                        awayScore = columns.next().next().next().next().next().next().next().next().first().text();
                        awayYards = columns.next().next().next().next().next().next().next().next().next().next()
                                .first().text();
                        homeYards = columns.next().next().next().next().next().next().next().next().next().next().next()
                                .next().first().text();
                        awayTO = columns.next().next().next().next().next().next().next().next().next().next().next()
                                .first().text();
                        homeTO = columns.next().next().next().next().next().next().next().next().next().next().next()
                                .next().next().first().text();
                    } else {
                        // Home team won
                        result = "H";
                        home = columns.next().next().next().next().first().text();
                        away = columns.next().next().next().next().next().next().first().text();
                        homeScore = columns.next().next().next().next().next().next().next().next().first().text();
                        awayScore = columns.next().next().next().next().next().next().next().next().next().first()
                                .text();
                        homeYards = columns.next().next().next().next().next().next().next().next().next().next()
                                .first().text();
                        awayYards = columns.next().next().next().next().next().next().next().next().next().next().next()
                                .next().first().text();
                        homeTO = columns.next().next().next().next().next().next().next().next().next().next().next()
                                .first().text();
                        awayTO = columns.next().next().next().next().next().next().next().next().next().next().next()
                                .next().next().first().text();
                    }

                    Game NFLGame = new Game(year, week, day, home, homeScore, away, awayScore, date, result, homeYards,
                            awayYards, homeTO, awayTO);

                    fileOut.println(NFLGame);
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Failed to make file");
        }

    }

    static public Document getWebsite(String URL) {
        Document doc = null;

        while (doc == null) {
            try {
                doc = Jsoup.connect(URL).get();
            } catch (IOException ex) {
                System.out.println("Failed to load URL: " + URL);
            }
        }

        return doc;
    }

}
