import java.net.*;
import java.io.*;
import javax.net.*;
import javax.net.ssl.*;

/**
 * A wrapper class of Socket which contains 
 * methods for sending and receiving messages
 * @author M. L. Liu
 * SSLSocket Code taken from Lab 2
 */
public class MyStreamSocket {
	private SocketFactory sslSocketFactory;
	private SSLSocket sslSocket;
	private BufferedReader input;
	private PrintWriter output;
	
	MyStreamSocket(InetAddress acceptorHost,int acceptorPort ) throws SocketException,IOException {
		sslSocketFactory = SSLSocketFactory.getDefault();
		sslSocket = (SSLSocket) sslSocketFactory.createSocket(acceptorHost, acceptorPort);
		setStreams();
	}

	MyStreamSocket(SSLSocket sslSocket)  throws IOException {
		this.sslSocket = sslSocket;
		setStreams( );
	}

	private void setStreams( ) throws IOException{
		// authenticate server socket to allow secure sending/receiving of messages
		sslSocket.startHandshake();
		// get an input stream for reading from the data socket
		InputStream inStream = sslSocket.getInputStream();
		input = new BufferedReader(new InputStreamReader(inStream));
		OutputStream outStream = sslSocket.getOutputStream();
		// create a PrinterWriter object for character-mode output
		output = new PrintWriter(new OutputStreamWriter(outStream));
	}

	public void sendMessage(String message) throws IOException {	
		output.print(message + "\n");   
		//The ensuing flush method call is necessary for the data to
		// be written to the socket data stream before the
		// socket is closed.
		output.flush();               
	} // end sendMessage

	public String receiveMessage( )	throws IOException {	
		// read a line from the data stream
		String message = input.readLine( );  
		return message;
	} //end receiveMessage

	public void close( ) throws IOException {	
		sslSocket.close( );
	}
} //end class
