package database.tables;

import database.Connection;
import models.Request;
import models.Word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by theapache64 on 2/12/16.
 */
public class Requests extends BaseTable<Request> {

    private static final Requests instance = new Requests();

    private Requests() {
        super("requests");
    }

    public static Requests getInstance() {
        return instance;
    }

    @Override
    public boolean add(Request request) throws InsertFailedException {
        boolean isAdded = false;

        final String query = "INSERT INTO requests (word_id, ip) VALUES (?,?);";
        final java.sql.Connection con = Connection.getConnection();
        try {
            final PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, request.getWordId());
            ps.setString(2, request.getIPAddress());
            isAdded = ps.executeUpdate() == 1;
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (!isAdded) {
            throw new InsertFailedException("Failed to add request");
        }

        return true;
    }

    public Word getWord(String ipAddress) {
        Word word = null;
        final String query = "SELECT w.id, w.word FROM words w INNER JOIN requests r ON r.word_id = w.id WHERE r.ip = ? GROUP BY r.id ORDER BY r.id DESC LIMIT 1;";
        final java.sql.Connection con = Connection.getConnection();
        try {
            final PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, ipAddress);
            final ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                word = new Word(
                        rs.getString(Words.COLUMN_ID),
                        rs.getString(Words.COLUMN_WORD)
                );
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return word;
    }
}
