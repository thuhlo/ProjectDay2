package com.expleo.qe;

import net.thucydides.core.annotations.Step;

public class ProcessedData  {

    public ProcessedData(){}


    String message = "Chinook   artists  2    Name  Accept      OK";
    //toSerenity.createLine(message);

    //static ToSerenity toSerenity;

    @Step ("{0}")         // TODO Change to display {0}
    public String testRsult(String resultMessage) {  //TODO receive the current message
      // A = "a";
       return resultMessage;

    }
}
