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
    public long getLocation() {
        //TODO write your implementation code here:
        return 65;
    }


}
