package com.stein.myenergi.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * TODO read error-codes.properties Example error response: {"status":-9,"statustext":"","asn":"s7.myenergi.net","fwv":"3402S4047"}
 */
public class ResultCode {

     private ResourceBundle codes;

     public ResultCode() throws IOException {
          codes = new PropertyResourceBundle(new FileInputStream("error-codes"));
     }
}

