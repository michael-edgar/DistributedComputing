// A sample SOAP service object inplementation

public class Temp implements ITemp
{
   public float getTemp( String zipCode )
   { 
      /**/  System.out.println
      ( "Temperature for zip code " + zipCode + " requested.");
      return 74.5F; // returns a constant for simplicty  
 
   } // end getTemp

} //end class
