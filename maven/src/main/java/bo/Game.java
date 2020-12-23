package bo;

public class Game {
    private int id;
    private String homeTeam,awayTeam, league, hour;
    private String date;
    private double oddHome, oddTie, oddAway;

    public Game(int id, String homeTeam, String awayTeam, String date, String league,String hour, double oddHome, double oddTie, double oddAway) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.league = league;
        this.hour = hour;
        this.oddHome = oddHome;
        this.oddTie = oddTie;
        this.oddAway = oddAway;
    }

    public Game(String homeTeam, String awayTeam, String date, String league,String hour, double oddHome, double oddTie, double oddAway) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.league = league;
        this.hour = hour;
        this.oddHome = oddHome;
        this.oddTie = oddTie;
        this.oddAway = oddAway;
    }

    @Override
    public String toString() {
        return "Game{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", date='" + date + '\'' +
                ", league='" + league + '\'' +
                ", hour='" + hour + '\'' +
                ", oddHome=" + oddHome +
                ", oddTie=" + oddTie +
                ", oddAway=" + oddAway +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getLeague() {
        return league;
    }

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }

    public double getOddHome() {
        return oddHome;
    }

    public double getOddTie() {
        return oddTie;
    }

    public double getOddAway() {
        return oddAway;
    }
}
