package com.expleo.qe;

import net.thucydides.core.annotations.Step;

import java.io.File;
import java.sql.*;

public class DatabaseAccess
{
    static Connection conn;

    /**
     * Finds if the Database file exists
     * @param dbName - the name of a Database
     * @return Boolean based on whether the Database is found or not
     */
    @Step("Find if database exists")
    public static boolean databaseFound(String dbName)
    {
        File myFile;
        boolean found = false;
        myFile = new File(dbName + ".db");
        if(myFile.exists())
        {
            found = true;
        }

        return found;
    }

    /**
     * Creates a Global Connection to the Database
     * @param dbName -- the name of Database to access
     */
    @Step("Create Connection to database")
    public static void createConnection(String dbName) throws SQLException {
        final String DB_URL = "jdbc:sqlite:C:\\Users\\7200\\Squirrel\\" + dbName + ".db";
        final String USER = "";
        final String PASS = "";

        //try
        //{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
       // }
       // catch (SQLException e)
       // {
         //   System.out.println( e.getMessage());
       // }
    }

    /**
     * Builds and queries the database based on parameters
     * @param column -- the column to be searched
     * @param tableName -- the table to be searched
     * @param keyColumn -- the primary key column to be found
     * @param keyValue -- the primary key values
     * @return A String containing the data found by query
     */
    @Step("Access database and query")
  static String buildSQL(String column, String tableName, String keyColumn, String keyValue)
  {
      Statement stmnt;
      ResultSet rs;
      String sql = "Select " + column + " from " + tableName +  " Where " + keyColumn +  " = " + keyValue;

      String returnString = "";
      DataModel objData = null;
      try
      {
          stmnt = conn.createStatement();
          rs = stmnt.executeQuery(sql);

          if(rs != null)
          {
            returnString = rs.getString(1);

          }
        rs.close();
      }
      catch (SQLException e)
      {
          e.printStackTrace();
      }
      return returnString;
  }


    /**
     * Finds or searches the Datbase for a specified table
     * @param tableName -- table name to search for
     * @return -- returns true or false based on what is found
     */
    @Step("Find if table exists")
  public static boolean findTable(String tableName)
  {
      boolean found;
      Statement stmt;
      String sql  = "Select * from " + tableName;

      try
      {
          stmt = conn.createStatement();
          found = stmt.execute(sql);

      }
      catch (SQLException e)
      {
          e.getMessage();
          found = false;
      }
        return found;
  }


    /**
     * Finds Key Column in the Databes
     * @param keyColumn -- the column to search for
     * @param tableName -- the table to search in
     * @return -- True or False based on the query
     */
    @Step("Find if key Column exists")
  public static boolean keyColumnFound(String keyColumn, String tableName)
  {
      boolean found = false;
      Statement stmnt;
      ResultSet rs;
      String sql = "Select " + keyColumn + " from " + tableName;

      try
      {
          stmnt = conn.createStatement();

          if(stmnt.execute(sql))
          {
              found = true;
          }
      }
      catch (SQLException e)
      {
          e.getMessage();
      }
      return found;
  }

    /**
     * Searches the Database table for a Key Column
     * @param keyValue -- the value to look for in the Database
     * @param keyColumn -- the column to search in
     * @param tableNAme -- the table to search fom
     * @return -- returns only the first column of what is found
     */
    @Step("Find if key column value match is found")
  public static String keyColumnValueMatch(String keyValue, String keyColumn, String tableNAme)
  {
      Statement stmnt;
      ResultSet rs = null;
      String sql = "Select " + keyColumn + " from " + tableNAme + "  where " + keyColumn + " = " + keyValue;
      boolean found = false;
      String rsID = "";
      try
      {
          stmnt = conn.createStatement();
          rs = stmnt.executeQuery(sql);
          rsID = rs.getString(1);
          rs.close();
      }
      catch (SQLException e)
      {
          e.getMessage();
      }
      return rsID;
  }

    /**
     * Checks the Database whether a specified column exists
     * @param columnName -- the column to look for!
     * @param tableName -- the table to search in!
     * @return -- True or False, based on the Query
     */
    @Step("Find if column exists")
  public static boolean doesColumnExist(String columnName, String tableName)
  {
      Statement stmnt;
      String sql = "Select " + columnName + " from " + tableName;
      boolean found = false;

      try
      {
          stmnt = conn.createStatement();
          found = stmnt.execute(sql);

      }
      catch (SQLException e)
      {
          e.getMessage();
      }
      return found;
  }




}
