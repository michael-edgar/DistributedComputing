import java.io.*;

/**
 * This module contains the presentaton logic of a Twitter Messaging Client.
 * @author M. L. Liu
 */
public class TwitterClient {
	private static final String endMessage = ".";
	
	public static void main(String[] args) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		try {
			System.out.println("Welcome to the Twitter client.\nWhat is the name of the server host?");
			String hostName = br.readLine();
			if (hostName.length() == 0) {// if user did not enter a name
				hostName = "localhost"; // use the default host name
			}
			System.out.println("What is the port number of the server host?");
			String portNum = br.readLine();
			if (portNum.length() == 0) { // if user did not enter a port number
				portNum = "7"; // default port number
			}
			TwitterClientHelper helper = new TwitterClientHelper(hostName, portNum);
			String loginDetails = "";
			//Ask User to login to server
			boolean validLogin = false;
			while(!validLogin) { // loops until user has entered a valid username/password combination or until user quits
				System.out.println("Please enter your username and password. Otherwise enter a single period to quit.");
				loginDetails = br.readLine( );
				if ((loginDetails.trim()).equals(endMessage)){
					helper.done();
					break;
				} // end if endMessage
				else if((loginDetails.trim()).equals("")){
					System.out.println("Error invalid input: empty response detected.");
				} // end if empty
				else {
					String[] usernameAndPassword = loginDetails.split(" ");
					if(usernameAndPassword.length > 2) {
						System.out.println("Error invalid input: expected username and password but got "+usernameAndPassword.length+" inputs instead.");
					} // end if too many inputs
					else if (usernameAndPassword.length < 2) {
						System.out.println("Error invalid input: username or password not detected.");
					} // end if too few inputs
					else {
						System.out.println("Valid input detected: proceeding to log in.");
						validLogin = true;
						break;
					} // end else valid username/password
				} // end else
			} // end while loop
			
			if(validLogin) {
				handleClientMessages(loginDetails, helper, br);
			} // end if validLogins
		} // end try  
		catch (Exception ex) {
			ex.printStackTrace( );
		} //end catch
	} //end main
	
	private static void handleClientMessages(String loginDetails, TwitterClientHelper helper, BufferedReader br) throws Exception {
		// allow the user to upload/download tweets or log off
		boolean done = false;
		String loginStatus = helper.loginUser(loginDetails);
		System.out.println(loginStatus);
		
		while(!done) {
			//Ask user to Upload, download or log off
			System.out.println("Choose from the following options:\n1.\tUpload a tweet\n2.\tDownload all tweets\n3.\tLog off");
			String userChoice = br.readLine();
			if(userChoice.length() == 0) {
				System.out.println("No choice detected, please try again.");
			} // end if empty
			//check if choice is not one digit
			else if(userChoice.length() > 1 || !Character.isDigit(userChoice.charAt(0))) {
				System.out.println("Invalid choice detected, please enter 1,2 or 3.");
			} // end if invalid input
			else {
				int numChoice = Integer.parseInt(userChoice);
				
				switch(numChoice) {
					case 1: {
						//Upload Tweet
						System.out.println("Please enter your tweet below.");
						String tweet = br.readLine();
						String uploadResults = helper.uploadTweet(tweet);
						System.out.println(uploadResults);
						break;
					} // end case 1
					case 2: {
						//Download Tweets
						String tweets = helper.downloadTweets();
						System.out.println(tweets);
						break;
					} // end case 2
					case 3: {
						//Log off
						done = true;
						String logOffResults = helper.logUserOut(loginDetails);
						System.out.println(logOffResults);
						break;
					} // end case 3
					default: {
						System.out.println("Invalid number choice detected, please choose 1,2 or 3.");
					} // end default
				} // end switch
			} // end else
		} // end while not done loop
	} // end handleClientMessages
} // end class
