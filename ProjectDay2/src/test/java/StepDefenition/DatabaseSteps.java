package StepDefenition;

import com.expleo.qe.CSVconnection;
import com.expleo.qe.DataModel;
import com.expleo.qe.DatabaseAccess;
import com.expleo.qe.ProcessedData;
import cucumber.api.java.en.*;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class DatabaseSteps
{

    int dataCounter = 0;
    boolean csvFound;


    DataModel objDataModel;
    CSVconnection csvConn = new CSVconnection();
    ArrayList<DataModel> arData;
    ArrayList<DataModel> arResults = new ArrayList<>();

    @Steps
    ProcessedData processedData;


    @Given("^The CSV file with data$")
    public void the_CSV_file_with_data() throws IOException
    {
        String csvFileName = "Project Day 1 - Java Libs and Packages - CSV Data Ver 1.0";


        csvFound = csvConn.csvFound2(csvFileName);
        assertThat("CSV file not found",true,is(equalTo(csvFound)));

        arData = csvConn.readCSVfile(csvFileName);
        assertThat("Database is not found",arData.size()>=1,is(arData.size()>=1));


    }


    @When("^I access a given database from the CSV file provided$")
    public void i_access_a_given_database_from_the_CSV_file_provided()
    {

        boolean dbFound = false, tableFound = false, keyColumnFound = false, doesColumnExist = false;
        String keyColumnValueMatch;

        try {
            for (int i = 0; i < arData.size(); i++) {

                try
                {
                    //Initialize database connection
                    DatabaseAccess.createConnection(arData.get(i).getDatabase());
                }
                catch (SQLException e)
                {
                    objDataModel = new DataModel(arData.get(i).getDatabase(), arData.get(i).getTable(),
                            arData.get(i).getKeyColumn(), arData.get(i).getKeyValue(), arData.get(i).getColumn(),
                            arData.get(i).getValue(), "Invalid Database");
                    arResults.add(objDataModel);
                    continue;
                }

                try {
                    //if Database is found
                    dbFound = DatabaseAccess.databaseFound(arData.get(i).getDatabase());
                    assertThat("Database :" + arData.get(i).getDatabase() + " Not Found", true, is(dbFound));
                    // System.out.println("Database Found :" + dbFound);
                } catch (AssertionError e) {
                    objDataModel = new DataModel(arData.get(i).getDatabase(), arData.get(i).getTable(),
                            arData.get(i).getKeyColumn(), arData.get(i).getKeyValue(), arData.get(i).getColumn(),
                            arData.get(i).getValue(), "Invalid Database");
                    arResults.add(objDataModel);
                    continue;
                }

                try {
                    //if TABLE is found in database
                    tableFound = DatabaseAccess.findTable(arData.get(i).getTable());
                    assertThat("Table :" + arData.get(i).getTable() + " is Not Found", true, is(tableFound));
                } catch (AssertionError e) {
                    objDataModel = new DataModel(arData.get(i).getDatabase(), arData.get(i).getTable(),
                            arData.get(i).getKeyColumn(), arData.get(i).getKeyValue(), arData.get(i).getColumn(),
                            arData.get(i).getValue(), "Invalid table");
                    arResults.add(objDataModel);
                    continue;
                }

                try {
                    //if key column found
                    keyColumnFound = DatabaseAccess.keyColumnFound(arData.get(i).getKeyColumn(), arData.get(i).getTable());
                    assertThat("Key Column does not exist", true, is(equalTo(keyColumnFound)));
                } catch (AssertionError e) {
                    objDataModel = new DataModel(arData.get(i).getDatabase(), arData.get(i).getTable(),
                            arData.get(i).getKeyColumn(), arData.get(i).getKeyValue(), arData.get(i).getColumn(),
                            arData.get(i).getValue(), "Invalid Key Column");
                    arResults.add(objDataModel);
                    continue;
                }

                try {
                    //if key column value match found
                    keyColumnValueMatch = DatabaseAccess.keyColumnValueMatch(arData.get(i).getKeyValue()
                            , arData.get(i).getKeyColumn(), arData.get(i).getTable());
                    assertThat("Key Column value mismatch", arData.get(i).getKeyValue(), is(equalTo(keyColumnValueMatch)));
                } catch (AssertionError e) {
                    objDataModel = new DataModel(arData.get(i).getDatabase(), arData.get(i).getTable(),
                            arData.get(i).getKeyColumn(), arData.get(i).getKeyValue(), arData.get(i).getColumn(),
                            arData.get(i).getValue(), "Key Column Value mismatch");
                    arResults.add(objDataModel);
                    continue;
                }

                try {
                    //If the column in the table does not exist, the fail the line
                    doesColumnExist = DatabaseAccess.doesColumnExist(arData.get(i).getColumn(), arData.get(i).getTable());
                    assertThat("Column" + arData.get(i).getColumn() + " does not exist", true, is(equalTo(doesColumnExist)));
                } catch (AssertionError e) {
                    objDataModel = new DataModel(arData.get(i).getDatabase(), arData.get(i).getTable(),
                            arData.get(i).getKeyColumn(), arData.get(i).getKeyValue(), arData.get(i).getColumn(),
                            arData.get(i).getValue(), "Invalid Column");
                    arResults.add(objDataModel);
                    continue;
                }

                try {
                    //If the column value does not match the value in the CSV file, then fail the line
                    keyColumnValueMatch = DatabaseAccess.keyColumnValueMatch(arData.get(i).getKeyValue()
                            , arData.get(i).getKeyColumn(), arData.get(i).getTable());
                    assertThat("Key Column value mismatch", arData.get(i).getKeyValue(), is(equalTo(keyColumnValueMatch)));
                } catch (AssertionError e) {
                    objDataModel = new DataModel(arData.get(i).getDatabase(), arData.get(i).getTable(),
                            arData.get(i).getKeyColumn(), arData.get(i).getKeyValue(), arData.get(i).getColumn(),
                            arData.get(i).getValue(), "Key Column Value mismatch");
                    arResults.add(objDataModel);
                    continue;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }

    @Then("^I should be presented with correct output as a serenity report$")
    public void i_should_be_presented_with_correct_output_as_a_serenity_report()
    {
        System.out.println("============================================================================");
        for(int t = 0; t < arResults.size(); t++)
        {
            processedData.testRsult(arResults.get(t).getDatabase() + "\t\t" + arResults.get(t).getTable() + "\t\t"
            + arResults.get(t).getKeyColumn() + "\t\t" + arResults.get(t).getKeyValue() + "\t\t" + arResults.get(t).getColumn()
            + arResults.get(t).getValue() + "\t\t" + arResults.get(t).getMessage());  //TODO pass index 5 of current row
        }
        System.out.println("============================================================================");
    }



}
