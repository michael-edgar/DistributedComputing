import java.net.*;
import java.io.*;

/**
 * This example illustrates the basic method calls for connectionless
 * datagram socket.
 * @author M. L. Liu
 */
public class Example2ReceiverSender {

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
   	      MyDatagramSocket s = new MyDatagramSocket(port);
		  String message = s.receiveMessage();
		  s.sendMessage(InetAddress.getByName("localhost"), 2000, message);
		  System.out.println("Bye");
		  s.close();
		  
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
