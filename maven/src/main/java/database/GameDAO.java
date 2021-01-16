package database;

import bo.Game;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDAO extends DAO<Game> {

    public Game insert(Game obj) {
        try {
                //TODO : Scandaleux
                PreparedStatement updateCounter = this    .connect
                    .prepareStatement(
                            "UPDATE GameIDCounter SET counter=counter+1 WHERE 1"
                    );
                updateCounter.executeUpdate();
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                PreparedStatement prepare = this    .connect
                        .prepareStatement(
                                "INSERT INTO GAME (id,homeTeam,awayTeam,date,league,hour,oddHome,oddTie,oddAway) SELECT (SELECT counter from GameIDCounter),?,?,?,?,?,?,?,? FROM DUAL WHERE NOT EXISTS (SELECT * FROM GAME WHERE homeTeam=? AND awayTeam=? ) ");
                prepare.setString(1, obj.getHomeTeam());
                prepare.setString(2, obj.getAwayTeam());
                prepare.setString(3, obj.getDate());
                prepare.setString(4, obj.getLeague());
                prepare.setString(5, obj.getHour());
                prepare.setDouble(6, obj.getOddHome());
                prepare.setDouble(7, obj.getOddTie());
                prepare.setDouble(8, obj.getOddAway());
                prepare.setString(9, obj.getHomeTeam());
                prepare.setString(10, obj.getAwayTeam());
                prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}