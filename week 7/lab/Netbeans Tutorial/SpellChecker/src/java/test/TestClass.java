/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import com.cdyne.ws.DocumentSummary;
import com.cdyne.ws.Words;
import java.util.List;


/**
 *
 * @author t00036478
 */
public class TestClass {
    
    public static void main(String args[]){
        
                DocumentSummary ds = checkTextBodyV2("compting");
                List l = ds.getMisspelledWord();
                String onewrongword = ((Words) l.get(0)).getWord();
                System.out.println(onewrongword);
        
    }

    private static DocumentSummary checkTextBodyV2(java.lang.String bodyText) {
        com.cdyne.ws.Check service = new com.cdyne.ws.Check();
        com.cdyne.ws.CheckSoap port = service.getCheckSoap();
        return port.checkTextBodyV2(bodyText);
    }


    
}
