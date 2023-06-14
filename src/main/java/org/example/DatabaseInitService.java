package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {

    private static PreparedStatement initDB;

    public static final String INIT_DB_FILENAME = "sql/init_db.sql";


    public static void main(String[] args) {
        try
                (Connection conn = Database.getInstance().getConnection()) {
                String sql = Files.readString(Path.of(INIT_DB_FILENAME));
                 initDB = conn.prepareStatement(sql);
                 initDB.executeUpdate();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
