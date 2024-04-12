package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import server.handlers.LoginHandler;
import server.handlers.RegisterHandler;
import server.handlers.TestHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class server {
public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/register", RegisterHandler.getInstance());
        server.createContext("/login", LoginHandler.getInstance());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
