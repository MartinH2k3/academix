package server.handlers.faculty;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.FacultyDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import server.database.faculty.FacultyCreator;
import server.database.faculty.FacultyGetter;
import server.handlers.util.ParamParser;
import server.logging.Logging;

public class GetFacultiesHandler implements HttpHandler {
    private static GetFacultiesHandler instance = null;

    private GetFacultiesHandler() {
    }

    public static GetFacultiesHandler getInstance() {
        if (instance == null) {
            instance = new GetFacultiesHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;

        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            List<FacultyDTO> faculties;
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            if (params.containsKey("page") && params.containsKey("page_size")){
                Integer page = Integer.parseInt(params.get("page"));
                Integer page_size = Integer.parseInt(params.get("page_size"));
                if (params.containsKey("name")){
                    faculties = FacultyGetter.getAllFaculties(params.get("name"), page, page_size);
                }
                else {
                    faculties = FacultyGetter.getAllFaculties(page, page_size);
                }
            }
            else {
                faculties = FacultyGetter.getAllFaculties();
                }
            Gson gson = new Gson();
            response = gson.toJson(faculties);
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
