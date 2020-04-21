import java.io.*;
import java.util.ArrayList;
/**
 * This module is to be used with a concurrent Twitter messaging server.
 * Its run method carries out the logic of a client session.
 * @author M. L. Liu
 */

class TwitterServerThread implements Runnable {
	static final String endMessage = ".";
	ArrayList<String> usernames = new ArrayList<String>();
	ArrayList<String> tweets = new ArrayList<String>();
	MyStreamSocket myDataSocket;

	TwitterServerThread(MyStreamSocket myDataSocket) {
		this.myDataSocket = myDataSocket;
	}
 
	public void run( ) {
		boolean done = false;
		String message;
		try {
			while (!done) {
				message = myDataSocket.receiveMessage( );
				System.out.println("message received: "+ message);
				if ((message.trim()).equals (endMessage)){
					//Session over; close the data socket.
					System.out.println("Session over.");
					myDataSocket.close( );
					done = true;
				} //end if
				else {
					int code = getMessageCode(message);
					
					switch (code) {
						case 100 : {
							String username = (message.substring(4)).split(" ")[0];
							usernames.add(username);
							System.out.println("User: " +username+ " logged in");
							myDataSocket.sendMessage("200");
							break;
						} // end case 100
						case 101: {
							String tweet = message.substring(4);
							tweets.add(tweet);
							System.out.println("Message: " +tweet+ " saved");
							myDataSocket.sendMessage("200");
							break;
						} // end case 101
						case 102: {
							StringBuilder listOfTweets = new StringBuilder();
							for(int i = 0; i < tweets.size(); i++) {
								if(i < (tweets.size()-1)) {
									listOfTweets.append(tweets.get(i)+", ");
								}
								else {
									listOfTweets.append(tweets.get(i));
								}
							}
							
							System.out.println("Tweets: " +listOfTweets);
							System.out.println(""+tweets.size());
							myDataSocket.sendMessage(listOfTweets.toString());
							break;
						} // end case 102
						case 103: {
							String username = (message.substring(4));
							if(usernames.contains(username)) {
								usernames.remove(username);
								System.out.println("User: " +username+ " logged out");
								myDataSocket.sendMessage("200");
							} // end if
							else {
								myDataSocket.sendMessage("404 USERNOTFOUND");
							} // end else
							break;
						} // end case 103
						default: {
							// Echo back the message to check for errors in input
							myDataSocket.sendMessage("400 INVALID MESSAGE: "+message);
						} // end default
					} // end switch
				} // end else
			} //end while !done
		}// end try
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} // end catch
	} //end run
	
	private int getMessageCode(String message) {
		int messageCode = 0;
		message = message.substring(0, 3);
		try {
			messageCode = Integer.parseInt(message);
		} // end try
		catch(Exception ex) {
			System.out.println("Exception caught in thread: " +ex);
		} // end catch
		return messageCode;
	} // end getMessageCode
} //end class 
