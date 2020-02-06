
public class Game {

    public Game(String year, String week, String day, String home, String homeScore, String away, String awayScore, String date, String result,
            String homeYards, String awayYards, String homeTO, String awayTO) {
        super();
        this.year = year;
        this.week = week;
        this.day = day;
        this.home = home;
        this.homeScore = homeScore;
        this.away = away;
        this.awayScore = awayScore;
        this.date = date;
        this.result = result;
        this.homeYards = homeYards;
        this.awayYards = awayYards;
        this.homeTO = homeTO;
        this.awayTO = awayTO;
    }

    public String getYear() {
        return year;
    }

    public String getWeek() {
        return week;
    }

    public String getDay() {
        return day;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }

    public String getHomeYards() {
        return homeYards;
    }

    public String getAwayYards() {
        return awayYards;
    }

    public String getHomeTO() {
        return homeTO;
    }

    public String getAwayTO() {
        return awayTO;
    }

    final private String year;
    final private String week;
    final private String day;
    final private String home;
    final private String homeScore;
    final private String away;
    final private String awayScore;
    final private String date;
    final private String result;
    final private String homeYards;
    final private String awayYards;
    final private String homeTO;
    final private String awayTO;

    @Override
    public String toString() {
        return year + "," + week + "," + day + "," + home + "," + homeScore + "," + away + "," + awayScore + "," + date + "," + result + ","
                + homeYards + "," + awayYards + "," + homeTO + "," + awayTO;
    }

}
