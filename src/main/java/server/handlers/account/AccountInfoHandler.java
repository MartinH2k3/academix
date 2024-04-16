package server.handlers.account;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.user.AccountInfoUpdate;
import server.handlers.util.ParamParser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class AccountInfoHandler implements HttpHandler {
    public static AccountInfoHandler instance = null;

    private AccountInfoHandler() {
    }

    public static AccountInfoHandler getInstance() {
        if (instance == null) {
            instance = new AccountInfoHandler();
        }
        return instance;
    }

    /**
     * Handle the account update request. It updates the user's account information, which consists of
     * first name, last name, email, or phone number. All parameters are optional.
     * @param exchange HttpExchange (username, first_name, last_name, email, phone_number)
     * @return String
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException {
        String response;
        String username;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) { // PUT more correct, but POST, so it's more uniform
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            // check what parameters are present and call corresponding method
            response = "";

            if (!params.containsKey("username")){
                response = "Username not provided";
                exchange.sendResponseHeaders(400, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }

            username = params.get("username");

            if (params.containsKey("first_name")) {
                response += AccountInfoUpdate.updateFirstName(username, params.get("first_name"));
            }

            if (params.containsKey("last_name")) {
                response += AccountInfoUpdate.updateLastName(username, params.get("last_name"));
            }

            if (params.containsKey("email")) {
                response += AccountInfoUpdate.updateEmail(username, params.get("email"));
            }

            if (params.containsKey("phone_number")) {
                response += AccountInfoUpdate.updatePhoneNumber(username, params.get("phone_number"));
            }

            if (response.equals("")) {
                response = "No parameters provided";
            }

        } else {
            response = "Wrong request method";
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
