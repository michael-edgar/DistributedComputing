/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lt00036478
 */
import java.io.IOException; 
import java.util.Vector; 
import org.apache.xmlrpc.XmlRpcException;
  import org.apache.xmlrpc.client.XmlRpcClient;
  import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
  import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
  import org.apache.xmlrpc.client.util.ClientFactory;
import java.net.MalformedURLException;
  import java.net.URL;
  

public class AreaClient {
    public static void main(String args[]) 
{ 
if (args.length < 1) 
{ 
System.out.println( "Usage: java AreaClient [radius]"); System.exit(-1); 
} 




AreaClient client = new AreaClient( ); 
double radius = Double.parseDouble(args[0]); 
try { 
double area = client.areaCircle(radius); 
// Report the results 
System.out.println("The area of the circle would be: " + area); 
} 
catch (Exception e) 
{ 
System.out.println("IO Exception: " + e.getMessage( )); 
} 
} //end of main
public double areaCircle (double radius) throws IOException, XmlRpcException 
{ 
    // create configuration
          XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
          config.setServerURL(new URL("http://127.0.0.1:3000/xmlrpc"));
          config.setEnabledForExtensions(true);  
          config.setConnectionTimeout(60 * 1000);
          config.setReplyTimeout(60 * 1000);
          
          XmlRpcClient client = new XmlRpcClient();
        
          // use Commons HttpClient as transport
          client.setTransportFactory(
              new XmlRpcCommonsTransportFactory(client));
          // set configuration
          client.setConfig(config);


 // make the a regular call
          Object[] params = new Object[]
              { new Double(2.2) };
          double result = (double) client.execute("AreaHandler.circleArea", params);
          System.out.println(result);

return result; 
} 
    
}
