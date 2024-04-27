package server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.support.Helpline;
import server.handlers.util.HttpStreamManager;
import server.handlers.util.ParamParser;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class QuizHandler implements HttpHandler {
    private static QuizHandler instance;

    private QuizHandler() {
    }

    public static QuizHandler getInstance() {
        if (instance == null) {
            instance = new QuizHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            try {
                response = Files.readString(Paths.get("src/main/java/server/quiz.json"));
            } catch (IOException e) {
                response = "Error reading quiz file";
                Logging.getInstance().logServerWarning(response);
            }
            Gson gson = new Gson();
            gson.fromJson(response, Object.class);
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
