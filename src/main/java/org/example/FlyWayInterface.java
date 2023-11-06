package org.example;

import org.flywaydb.core.Flyway;

import static org.example.Config.*;
import static org.example.OsbbCRUD.logger;

public class FlyWayInterface {
    private FlyWayInterface() {

    }

    public static void fwMigrate(){
        Flyway.configure()
                .dataSource(JDBCURL, USERNAME, PASSWORD)
                .schemas("flywaycreated")
                .load()
                .migrate();
        logger.info("Processed migration with flyWay");
    }
}
