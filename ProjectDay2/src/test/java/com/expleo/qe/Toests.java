package com.expleo.qe;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class Toests
{
    public static void main(String[] args) throws IOException {
        CSVconnection csvConn = new CSVconnection();
        boolean csvFound, tableFound = false;
        String csvFileName = "Project Day 1 - Java Libs and Packages - CSV Data Ver 1.0";

        csvFound = csvConn.csvFound2(csvFileName);
        System.out.println(csvFound);

        ArrayList<DataModel>arD = csvConn.readCSVfile(csvFileName);

        try {

            for (int i = 0; i < arD.size(); i++)
            {
                System.out.println("db : " + arD.get(i).getDatabase());
                DatabaseAccess.createConnection(arD.get(i).getDatabase());
                tableFound = DatabaseAccess.findTable(arD.get(i).getTable());
                System.out.println(tableFound);
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
