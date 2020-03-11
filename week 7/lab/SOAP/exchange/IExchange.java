// A SOAP service object interface
// Source: 
//   http://www-106.ibm.com/developerworks/library/ws-peer2/

// This method performs currency exchange for two countries
// specified as parameters.
public interface IExchange 
{    
    float getRate( String country1, String country2 ); 

} //end interace
