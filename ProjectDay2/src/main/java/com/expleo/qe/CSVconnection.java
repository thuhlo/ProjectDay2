package com.expleo.qe;

import net.thucydides.core.annotations.Step;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CSVconnection {

    //static String filePath = "C:\\Users\\7200\\Desktop\\ProjectDay1\\";
    //static String fileName = "Project Day 1 - Java Libs and Packages - CSV Data Ver 1.0";

    /**
     * Checks for a file in the root of the project folder
     * The value returned depends on if a file is found or not
     * @param fileName
     * @return
     */
    @Step("Find if CSV file exists")
    public static boolean csvFound(String fileName)
    {
        File myFile;
        boolean found = false;

        myFile = new File(fileName.trim()+".txt");

        if(myFile.exists())
        {
            found = true;
        }
        System.out.println("From CsvConnection " + found);
        return found;
    }



    public boolean csvFound2(String fileName)
    {
        File myFile;
        boolean found = false;

        myFile = new File(fileName.trim()+".txt");

        if(myFile.exists())
        {
            found = true;
        }
        return found;
    }



    /**
     * Reads the CSV file found in the root directory of the project folder
     * Returns an ArrayList of type DataModel, that contains data from CSV file
     * @param fileName
     * @return
     * @throws IOException
     */
    @Step("Get all data from CSV File")
    public ArrayList<DataModel> readCSVfile(String fileName) throws IOException {
        int lineCounter = 0;
        String fileLine;
        int count = 0;
        ArrayList<DataModel> arData = new ArrayList<>();

            FileInputStream fis = new FileInputStream(fileName + ".txt");
            BufferedReader myInput = new BufferedReader(new InputStreamReader(fis));
            DataModel objModel;


            while (((fileLine = myInput.readLine()) != null)) {

                StringTokenizer st = new StringTokenizer(fileLine, ",");

                if (lineCounter != 0) {
                    while (st.hasMoreElements()) {

                        //System.out.println(st.nextToken() + "\t\t" + st.nextToken() + "\t\t" + st.nextToken() +
                        //   "\t\t" + st.nextToken() + "\t\t" + st.nextToken() + "\t\t" + st.nextToken());
                        objModel = new DataModel(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(),
                                st.nextToken());
                        arData.add(objModel);

                    }
                }
                lineCounter++;
            }
            myInput.close();
            return arData;
    }
}
