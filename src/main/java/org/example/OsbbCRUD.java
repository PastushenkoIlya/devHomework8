package org.example;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.*;

import static org.example.Config.*;

public class OsbbCRUD {
    static Logger logger = Logger.getLogger(OsbbCRUD.class);
    private Connection connection;
    public OsbbCRUD() throws SQLException {
        logger.info("initialization of crud interface");

        fwMigrate();

        connection = DriverManager.getConnection(jdbcUrl, username, password);
    }
    public static void fwMigrate(){
        logger.info("Processed migration with flyWay");
        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .locations("classpath:db/migration")
                .load();
    }
    public void close() throws SQLException {
        logger.trace("closing jdbc connection");
        connection.close();
        connection = null;
    }
    public String selectFirstBuildingsRow(String sqlQuery) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        String result = "";
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String address = resultSet.getString("address");
            result = "id: " + id + "\naddress: " + address;
    }
        return result;
}
}