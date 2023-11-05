package org.example;

import org.flywaydb.core.Flyway;

import java.sql.*;

import static org.example.Config.*;

public class OsbbCRUD {
    //static Logger logger = Logger.getLogger(OsbbCRUD.class);
    private Connection connection;
    public OsbbCRUD(){
      //  logger.info("initialization of crud interface");

        fwMigrate();

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            System.out.println("помилка при отримуванні підключення");
            throw new RuntimeException(e);
        }
    }
    public static void fwMigrate(){

        Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .schemas("flywaycreated")
              //  .locations("classpath:resources.db.migration")
                .load()
                .migrate();
        //logger.info("Processed migration with flyWay");
    }
    public void close() throws SQLException {
        //logger.trace("closing jdbc connection");
        connection.close();
        connection = null;
    }
    public String selectFirstBuildingsRow(String sqlQuery) {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            resultSet = preparedStatement.executeQuery();
        String result = "";
        while (resultSet.next()) {
            String name = resultSet.getString("rsd.name");
            String surname = resultSet.getString("rsd.surname");
            String email = resultSet.getString("rsd.email");
            String address = resultSet.getString("group_concat(distinct(bld.address))");
            String apartment_number = resultSet.getString("group_concat(flt.apartment_number)");
            String flat_area = resultSet.getString("group_concat(flt.area)");

            result += "name: "+name+", surname: "+surname+", email: "+email+", address: "
                    +address+", apartment number: " +apartment_number+", flat area: "+flat_area +"\n";
    }

        return(result);
        } catch (SQLException e) {
            System.out.println("помилка при виводі");
            throw new RuntimeException(e);
        }
    }
}