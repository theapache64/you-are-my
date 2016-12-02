package database.tables;

import database.Connection;
import models.Word;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by theapache64 on 2/12/16.
 */
public class Words extends BaseTable<Word> {

    private static final Words instance = new Words();
    public static final String COLUMN_WORD = "word";

    private Words() {
        super("words");
    }

    public static Words getInstance() {
        return instance;
    }

    public Word getRandomWord() {
        Word word = null;
        final String query = "SELECT id,word FROM words ORDER BY RAND() LIMIT 1";
        final java.sql.Connection con = Connection.getConnection();
        try {
            final Statement stmt = con.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            if (rs.first()) {
                word = new Word(
                        rs.getString(COLUMN_ID),
                        rs.getString(COLUMN_WORD)
                );
            }

            rs.close();
            stmt.close();

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
