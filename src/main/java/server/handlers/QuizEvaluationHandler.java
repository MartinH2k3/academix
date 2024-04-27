package server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.FacultyDTO;
import server.database.faculty.FacultyGetter;
import server.handlers.util.ParamParser;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;


public class QuizEvaluationHandler implements HttpHandler {
    private static QuizEvaluationHandler instance;

    private QuizEvaluationHandler() {
    }

    public static QuizEvaluationHandler getInstance() {
        if (instance == null) {
            instance = new QuizEvaluationHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            FacultyDTO dto = FacultyGetter.getFacultyAfterQuiz(params.get("field"), params.get("grade"));
            Gson gson = new Gson();
            response = gson.toJson(dto);
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
