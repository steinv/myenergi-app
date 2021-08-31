package com.stein.myenergi.api.calls;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * TODO read error-codes.properties
 */
public class ResultCode {

     private ResourceBundle codes;

     public ResultCode() throws IOException {
          codes = new PropertyResourceBundle(new FileInputStream("error-codes"));
     }
}

