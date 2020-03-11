/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lt00036478
 */
import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer; 
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.*;
import java.io.IOException;

public class AreaServer {
    public static void main(String[] args) 
{ 
if (args.length < 1) 
{ 
System.out.println("Usage: java AreaServer [port]"); System.exit(-1); 
} 
try 
{ 
startServer(args); 
}
catch (IOException e) 
{ 
System.out.println("Could not start server: " + e.getMessage( )); 
} 
} 
    
public static void startServer(String[] args) throws IOException 
{ 
// Start the server, using built-in version
    System.out.println("Attempting to start XML-RPC Server...");
    WebServer server = new WebServer(Integer.parseInt(args[0]));
    XmlRpcServer xmlRPCServer =  server.getXmlRpcServer();
    PropertyHandlerMapping phm = new PropertyHandlerMapping();
    try{
    phm.load(Thread.currentThread().getContextClassLoader(),"MyHandlers.properties");
    }
    catch(Exception e){}

    xmlRPCServer.setHandlerMapping(phm);
       
    XmlRpcServerConfigImpl serverConfig =
              (XmlRpcServerConfigImpl) xmlRPCServer.getConfig();
          serverConfig.setEnabledForExtensions(true);
          serverConfig.setContentLengthOptional(false);

          server.start();

    System.out.println("Started successfully."); 
// Register our handler class as area 
 
System.out.println("Registered AreaHandler class to area.");
System.out.println("Now accepting requests. (Halt program to stop.)"); 
} 

}
