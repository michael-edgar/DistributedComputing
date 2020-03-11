
// A SOAP service object inplementation
// Source: 
//   http://www-106.ibm.com/developerworks/library/ws-peer2/

public class Exchange implements IExchange
{
   public float getRate( String country1, String country2 )
   { 
      System.out.println
         ( "getRate( " + country1 +        ", " + 
            country2 + " )" ); 
      return 144.52F; // returns a constant for now  
 
   } // end getRate

} //end class
