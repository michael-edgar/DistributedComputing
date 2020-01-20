import java.net.*;
import java.io.*;

/**
 * This example illustrates the basic method calls for connectionless
 * datagram socket.
 * @author M. L. Liu
 */
public class Example2SenderReceiver {

// An application which sends a message using connectionless
// datagram socket.
// Three command line arguments are expected, in order: 
//    <domain name or IP address of the receiver>
//    <port number of the receiver's socket>
//    <message, a string, to send>

   public static void main(String[] args) {
      if (args.length != 3)
         System.out.println
            ("This program requires three command line arguments");
      else {
         try {
  		      InetAddress receiverHost = InetAddress.getByName(args[0]);
  		      System.out.println("Host Name: " + receiverHost.getHostName());
  		      System.out.println("Host Address: " +receiverHost.getHostAddress());
  		      //System.out.println("canonical: " +receiverHost.getCanonicalHostName());
  		      System.out.println("to String: " +receiverHost.toString());
  		      int receiverPort = Integer.parseInt(args[1]);
            String message = args[2];
  	
            // instantiates a datagram socket for sending the data
   	        MyDatagramSocket mySocket = new MyDatagramSocket(2000);
			mySocket.sendMessage(receiverHost, receiverPort, message);
			String m = mySocket.receiveMessage();
			System.out.println(m);
			mySocket.close();
			
         } // end try
	 catch (Exception ex) {
       ex.printStackTrace( );
	 }
      } // end else
   } // end main
} // end class
