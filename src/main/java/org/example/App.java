package org.example;

import java.sql.*;

import org.apache.log4j.Logger;

import static org.example.Config.*;


public class App {
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) throws SQLException {
        String sqlQuery = "SELECT * FROM buildings LIMIT 1";
        try {
            OsbbCRUD osbbCRUD = new OsbbCRUD();
            osbbCRUD.selectFirstBuildingsRow(sqlQuery);

        } catch (SQLException e) {
            logger.fatal(e);
        }
    }
}