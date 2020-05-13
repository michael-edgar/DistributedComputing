import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TwitterClient {
    private static final String endMessage = ".";
    // https://stackoverflow.com/questions/29064436/how-to-build-restful-request-over-in-java
    // Bhargav Modi

    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        try {
            System.out.println("Welcome to Twitter Client, Please enter your username or enter '.' to exit");
            String username = br.readLine();
            if(!(username.trim()).equals(endMessage)) {
                System.out.println("Please enter your password or enter '.' to exit");
                String password = br.readLine();

                if(!(password.trim()).equals(endMessage)) {
                    URL url = new URL("http://localhost:8080/TwitterMessagingProtocol_war_exploded/login/"+username+"/"+password);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("GET");

                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String output;
                    System.out.println("Output from server: \n");
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }

                    conn.disconnect();
                }
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
