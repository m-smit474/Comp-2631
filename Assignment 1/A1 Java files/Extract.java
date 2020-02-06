import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This class extracts NFL stat's from the Internet and creates
 * a CSV file containing all of the information in an easy to use
 * format.
 * @author Matt Smith
 *
 */
public class Extract {

    /**
     * This is the main function of the program which takes a year
     * as a parameter and outputs a CSV file with the stat's for the given
     * year.
     */
    public static void main(String[] args) {
        Document website;
        String url;
        String year;

        if (args.length == 0) {
            year = "2018";
            url = "https://www.pro-football-reference.com/years/2018/games.htm";
        } else {
            year = args[0];
            url = "https://www.pro-football-reference.com/years/" + year + "/games.htm";
        }

        website = getWebsite(url);

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

                        away = teamName(away);
                        home = teamName(home);
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

                        home = teamName(home);
                        away = teamName(away);
                    }

                    Game nflGame = new Game(year, week, day, home, homeScore, away, awayScore, date, result, homeYards,
                            awayYards, homeTO, awayTO);

                    fileOut.println(nflGame);
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Failed to make file");
        }

    }

    /**
     * this is a function takes in a URL as a string and
     * returns a document connected to the URL.
     * @return
     */
    static public Document getWebsite(String url) {
        Document doc = null;

        while (doc == null) {
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException ex) {
                System.out.println("Failed to load URL: " + url);
            }
        }

        return doc;
    }

    /**
     * this function takes in a team name as a string and
     * abbreviates it.
     * @return
     */
    public static String teamName(String team) {
        switch (team) {
            case "Arizona Cardinals":
                team = "ARI";
                break;

            case "Atlanta Falcons":
                team = "ATL";
                break;

            case "Baltimore Ravens":
                team = "BAL";
                break;

            case "Buffalo Bills":
                team = "BUF";
                break;

            case "Carolina Panthers":
                team = "CAR";
                break;

            case "Chicago Bears":
                team = "CHI";
                break;

            case "Cincinnati Bengals":
                team = "CIN";
                break;

            case "Cleveland Browns":
                team = "CLE";
                break;

            case "Dallas Cowboys":
                team = "DAL";
                break;

            case "Denver Broncos":
                team = "DEN";
                break;

            case "Detroit Lions":
                team = "DET";
                break;

            case "Green Bay Packers":
                team = "GB";
                break;

            case "Houston Texans":
                team = "HOU";
                break;

            case "Indianapolis Colts":
                team = "IND";
                break;

            case "Jacksonville Jaguars":
                team = "JAC";
                break;

            case "Kansas City Chiefs":
                team = "KC";
                break;

            case "Los Angeles Chargers":
                team = "LAC";
                break;

            case "Los Angeles Rams":
                team = "LAR";
                break;

            case "Miami Dolphins":
                team = "MIA";
                break;

            case "Minnesota Vikings":
                team = "MIN";
                break;

            case "New England Patriots":
                team = "NE";
                break;

            case "New Orleans Saints":
                team = "NO";
                break;

            case "New York Giants":
                team = "NYG";
                break;

            case "New York Jets":
                team = "NYJ";
                break;

            case "Oakland Raiders":
                team = "OAK";
                break;

            case "Philadelphia Eagles":
                team = "PHI";
                break;

            case "Pittsburgh Steelers":
                team = "PIT";
                break;

            case "San Diego Chargers":
                team = "SD";
                break;

            case "Seattle Seahawks":
                team = "SEA";
                break;

            case "San Francisco 49ers":
                team = "SF";
                break;

            case "St. Louis Rams":
                team = "STL";
                break;

            case "Tampa Bay Buccaneers":
                team = "TB";
                break;

            case "Tennessee Titans":
                team = "TEN";
                break;

            case "Washington Redskins":
                team = "WAS";
                break;

            default:
                team = "UNK";
                break;
        }

        return team;
    }
}
