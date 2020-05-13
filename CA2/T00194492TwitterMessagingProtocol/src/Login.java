import javax.ws.rs.*;
import java.util.ArrayList;

// https://www.jetbrains.com/help/idea/creating-and-running-your-first-restful-web-service.html
// https://stackoverflow.com/questions/43352683/how-to-send-parameters-to-a-restful-service-from-a-java-web-project

// The Java class will be hosted at the URI path "/login"
@Path("/login")
public class Login {
    ArrayList<String> usernames = new ArrayList<>();
    private final String failMessage = "fail";

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("/{username}/{password}")
    public String getLogin(@PathParam("username") String username, @PathParam("password") String password) {
        // Add username to usernames ArrayList
        usernames.add(username);
        String loginResultMessage = "";
        // Return user login successfully
        if(isLoginFail(username, password)) {
            loginResultMessage = "Failed to log user: " +username+ " in.";
        }
        else {
            loginResultMessage = "User: " +username+" successfully logged in.";
        }
        return loginResultMessage;
    }

    private boolean isLoginFail(String username, String password) {
        return (username.equals(failMessage) || password.equals(failMessage));
    }
}
