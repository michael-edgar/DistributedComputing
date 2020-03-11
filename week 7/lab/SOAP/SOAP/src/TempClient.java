import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.soap.util.xml.*;
import org.apache.soap.*;
import org.apache.soap.rpc.*;

public class TempClient{

   public static void main(String[] args) {
      try	{
	     URL url= new URL(        
         "http://wsf.cdyne.com/WeatherWS/");
	     String zipcode= "23320";
	     float temp = getTemp(url, zipcode);
	     System.out.println("The temperature is " + temp);
 	 }
    catch (Exception e) {
       e.printStackTrace();
    }
   } //end main

   public static float getTemp (URL url, String zipcode) 
       throws Exception {

	   Call call = new Call ();

      // SOAP encoding specification
	   String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
	   call.setEncodingStyleURI(encodingStyleURI);

      // Set service locator parameters
	   call.setTargetObjectURI ("WeatherSoap");
   	call.setMethodName ("GetCityForecastByZIP");

      // Create parameter vector
	   Parameter aParam = 
      new Parameter("ZIP", String.class, zipcode, null);
	   Vector params = new Vector ();
	   params.addElement (aParam);
	   call.setParams (params);

      // Invoke the service 
      Response resp = call.invoke (url,"");

      // Process the response
    	if (resp.generatedFault ()) {
         // The call was unsuccessful
	      Fault f = resp.getFault(); // an error occurred 
         System.err.println( "Fault= " + f.getFaultCode() + 
           ", " + f.getFaultString() );
         throw new Exception( );
	   } 
      else {
         // The call was successful. 
         // Extract return value and return the result
	      Parameter result = resp.getReturnValue ();
	      Float readOut=(Float) result.getValue();
	      return readOut.floatValue();
	   }
    } //end getTemp

} //end class
