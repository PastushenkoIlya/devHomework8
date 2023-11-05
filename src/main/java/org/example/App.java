package org.example;

import java.sql.*;
//import org.apache.log4j.Logger;
public class App {
    //private static final Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) throws SQLException {
        String sqlQuery = "select rsd.name, rsd.surname, rsd.email, group_concat(distinct(bld.address)), group_concat(flt.apartment_number),\n" +
                "group_concat(flt.area)\n" +
                "from  residents as rsd, buildings as bld, flats as flt, owners_to_flats\n" +
                "where rsd.id = owners_to_flats.owner_id and\n" +
                "flt.id = owners_to_flats.flat_id and\n" +
                "flt.building_id = bld.id and\n" +
                "owners_to_flats.owner_id = any(select id from residents)\n" +
                "group by name, surname, email\n" +
                "having count(flt.apartment_number) < 2";
        OsbbCRUD osbbCRUD = new OsbbCRUD();
        System.out.println(osbbCRUD.selectFirstBuildingsRow(sqlQuery));
    }
}