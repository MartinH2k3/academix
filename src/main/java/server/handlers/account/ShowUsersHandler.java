package server.handlers.account;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.logging.Logging;
import server.database.user.UserGetter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ShowUsersHandler implements HttpHandler {
    private static ShowUsersHandler instance = null;

    private ShowUsersHandler() {
    }

    public static ShowUsersHandler getInstance() {
        if (instance == null) {
            instance = new ShowUsersHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            List<String> usernames = UserGetter.getAllUsers();
            Gson gson = new Gson();
            response = gson.toJson(usernames);
        } else {
            response = "Wrong request method";
            Logging.getInstance().logServerWarning(response);
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
