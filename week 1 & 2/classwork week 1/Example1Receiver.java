import java.net.*;
import java.io.*;

/**
 * This example illustrates the basic method calls for connectionless
 * datagram socket.
 * @author M. L. Liu
 */
public class Example1Receiver {

// An application which receives a message using connectionless
// datagram socket.
// A command line argument is expected, in order: 
//    <port number of the receiver's socket>
// Note: the same port number should be specified in the
// command-line arguments for the sender.

   public static void main(String[] args) {
      if (args.length != 1)
         System.out.println
            ("This program requires a command line argument.");
      else {
			int port = Integer.parseInt(args[0]);
         final int MAX_LEN = 10; 
            // This is the assumed maximum byte length of the 
            //      datagram to be received.
			try {
   	      DatagramSocket	mySocket = new DatagramSocket(port);  
               // instantiates a datagram socket for receiving the data
		  mySocket.setSoTimeout(5000);	   
            byte[ ] buffer = new byte[MAX_LEN];                                     
            DatagramPacket datagram = 
               new DatagramPacket(buffer, MAX_LEN);
            mySocket.receive(datagram); // blocking
            String message = new String(buffer);
            System.out.println(message);
            mySocket.close( );
         } // end try
	 catch(SocketTimeoutException e){
		 System.out.println("Time is up");
	 }
	 catch (Exception ex) {
        ex.printStackTrace( );
	 }
      } // end else
   } // end main
} // end class
