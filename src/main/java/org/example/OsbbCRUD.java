package org.example;

import org.apache.log4j.Logger;
import java.sql.*;
import static org.example.Config.*;

public class OsbbCRUD {
    static Logger logger = Logger.getLogger(OsbbCRUD.class);
    private Connection connection;
    public OsbbCRUD(){
        logger.info("initialization of crud interface");
        try {
            connection = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.fatal("Помилка при отриманні підключення");
            throw new RuntimeException(e);
        }
    }
    public void close(){
        logger.trace("closing jdbc connection");
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            logger.fatal("помилка при закритті JDBC з'єднання");
            throw new RuntimeException(e);
        }

    }
    public String selectFirstBuildingsRow(String sqlQuery) {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            resultSet = preparedStatement.executeQuery();

            StringBuilder sb = new StringBuilder();

            while (resultSet.next()) {
                String name = resultSet.getString("rsd.name");
                String surname = resultSet.getString("rsd.surname");
                String email = resultSet.getString("rsd.email");
                String address = resultSet.getString("group_concat(distinct(bld.address))");
                String apartmentNumber = resultSet.getString("group_concat(flt.apartment_number)");
                String flatArea = resultSet.getString("group_concat(flt.area)");

                sb      .append("name: ")
                        .append(name)
                        .append(", surname: ")
                        .append(surname)
                        .append(", email: ")
                        .append(email)
                        .append(", address: ")
                        .append(address)
                        .append(", apartment number: ")
                        .append(apartmentNumber)
                        .append(", flat area: ")
                        .append(flatArea)
                        .append("\n");
            }

        return(sb.toString());
        } catch (SQLException e) {
            logger.fatal("помилка при виконанні sql команди");
            throw new RuntimeException(e);
        }
    }
}