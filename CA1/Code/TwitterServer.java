import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.security.*;

/**
 * This module contains the application logic of a Twitter messaging server
 * which uses a stream-mode socket for interprocess communication.
 * Unlike EchoServer2, this server services clients concurrently.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 * SSLServerSocket Code taken from Lab 2
 */

public class TwitterServer {
	public static void main(String[] args) {
		int serverPort = 7;    // default port
		String message;

		if (args.length == 1 ) {
			serverPort = Integer.parseInt(args[0]);
		}
		try {
			// instantiates a stream socket for accepting connections
			SSLServerSocket mySSLConnectionSocket = initialiseSSL(serverPort);
			System.out.println("Twitter server ready.");  
			while (true) {  // forever loop
				// wait to accept a connection 
				System.out.println("Waiting for a connection.");
				MyStreamSocket myDataSocket = new MyStreamSocket((SSLSocket) mySSLConnectionSocket.accept( ));
				System.out.println("connection accepted");
				// Start a thread to handle this client's sesson
				Thread theThread = new Thread(new TwitterServerThread(myDataSocket));
				theThread.start();
				// and go on to the next client
			} //end while forever
		} // end try
		catch (Exception ex) {
			ex.printStackTrace( );
		} // end catch
	} //end main
	
	private static SSLServerSocket initialiseSSL(int serverPort) throws Exception {
		// Initialises the server socket using an SSLServerSocketFactory 
		// using the herong.jks keystore for authentication
		String ksName = "herong.jks";
		char ksPass[] = "password".toCharArray();
		char ctPass[] = "password".toCharArray();KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(ksName), ksPass);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, ctPass);
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(), null, null);
		SSLServerSocketFactory ssf = sc.getServerSocketFactory();
        SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(serverPort);
		return s;
	} // end initialiseSSL
} // end class
