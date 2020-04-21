import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for a Twitter messaging client using stream-mode socket.
 * @author M. L. Liu
 */

public class TwitterClientHelper {
	static final String endMessage = ".";
	private MyStreamSocket mySocket;
	private InetAddress serverHost;
	private int serverPort;

	TwitterClientHelper(String hostName, String portNum) throws SocketException, UnknownHostException, IOException {
		this.serverHost = InetAddress.getByName(hostName);
  		this.serverPort = Integer.parseInt(portNum);
		//Instantiates a stream-mode socket and wait for a connection.
		this.mySocket = new MyStreamSocket(this.serverHost,this.serverPort); 
		System.out.println("Connection request made");
	} // end constructor
	
	public String getEcho( String message) throws SocketException, IOException {     
		String echo = "";    
		mySocket.sendMessage( message);
		// now receive the echo
		echo = mySocket.receiveMessage();
		return echo;
	} // end getEcho
	
	public String loginUser(String loginDetails) throws SocketException, IOException {     
		String loginResults = "";    
		mySocket.sendMessage("100 "+loginDetails);
		// now receive the loginResults
		loginResults = mySocket.receiveMessage();
		return loginResults;
	} // end loginUser
	
	public String uploadTweet(String tweet) throws SocketException, IOException {     
		String uploadResults = "";    
		mySocket.sendMessage("101 "+tweet);
		// now receive the uploadResults
		uploadResults = mySocket.receiveMessage();
		return uploadResults;
	} // end upload tweet
	
	public String downloadTweets() throws SocketException, IOException {     
		String tweets = "";    
		mySocket.sendMessage("102");
		// now receive the tweets
		tweets = mySocket.receiveMessage();
		return tweets;
	} // end download tweets
	
	public String logUserOut(String username) throws SocketException, IOException {
		username = username.split(" ")[0];
		String logoutResults = "";    
		mySocket.sendMessage("103 "+username);
		// now receive the logoutResults
		logoutResults = mySocket.receiveMessage();
		done();
		return logoutResults;
	} // end logUserOut

	public void done( ) throws SocketException, IOException {
		mySocket.sendMessage(endMessage);
		mySocket.close( );
	} // end done 
} //end class
