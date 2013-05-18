/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoregon.cs;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ahmad
 */
@WebService(serviceName = "HelloWorld")
public class HelloWorld {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLocation")
    public long[] getLocation(int uid) {
        long[] toReturn = new long[2];
        //these values will be taken from the database
        toReturn[0] = 65; //longitude
        toReturn[1] = 65; //latitude
        return toReturn;
    }


}
