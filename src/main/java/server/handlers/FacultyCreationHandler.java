package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.faculty.FacultyCreator;
import server.handlers.util.ParamParser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class FacultyCreationHandler implements HttpHandler {
    private static FacultyCreationHandler instance = null;

    private FacultyCreationHandler() {
    }

    public static FacultyCreationHandler getInstance() {
        if (instance == null) {
            instance = new FacultyCreationHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            response = FacultyCreator.addFaculty(params.get("username"), params.get("university_name"), params.get("faculty_name"), params.get("description"), params.get("field"), params.get("minimal_grade"), params.get("website_url"), params.get("title_image_url"));
        } else {
            response = "Wrong request method";
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
