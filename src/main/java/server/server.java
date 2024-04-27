package server;

import com.sun.net.httpserver.HttpServer;
import server.handlers.QuizEvaluationHandler;
import server.handlers.QuizHandler;
import server.handlers.account.*;
import server.handlers.faculty.FacultyCreationHandler;
import server.handlers.faculty.GetFacultiesHandler;
import server.handlers.support.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class server {
public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        // Account
        server.createContext("/register", RegisterHandler.getInstance());
        server.createContext("/login", LoginHandler.getInstance());
        server.createContext("/account/update", AccountInfoHandler.getInstance());
        server.createContext("/account_info", GetAcountInfoHandler.getInstance());
        server.createContext("/account/reset_password", PasswordResetHandler.getInstance());
        server.createContext("/account/delete", AccountDeletionHandler.getInstance());
        // Support
        server.createContext("/pending_questions", PendingQuestionsHandler.getInstance());
        server.createContext("/submit_question", HelplineQuestionHandler.getInstance());
        server.createContext("/answer_question", HelplineAnswerHandler.getInstance());
        server.createContext("/question_responses", HelplineResponseHandler.getInstance());
        server.createContext("/pending_requests", PendingRequestsHandler.getInstance());
        server.createContext("/answer_request", AcceptRejectHandler.getInstance());
        server.createContext("/create_faculty", FacultyCreationHandler.getInstance());

        // TODO: add the following handlers
        server.createContext("/quiz", QuizHandler.getInstance());
        server.createContext("/quiz_result", QuizEvaluationHandler.getInstance());
        server.createContext("/faculties", GetFacultiesHandler.getInstance());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
