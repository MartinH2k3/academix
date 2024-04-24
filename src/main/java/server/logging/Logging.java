package server.logging;

import com.sun.tools.javac.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {
    private static Logging instance; // Singleton instance
    private final Logger logger;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private Logging() {
        this.logger = LogManager.getLogger(Main.class);
        Configurator.initialize(null, "src/main/resources/logger/loggerconfig.xml");
    }

    public static synchronized Logging getInstance() {
        if (instance == null) {
            instance = new Logging();
        }
        return instance;
    }

    public void logException(Exception e, String message) {
        logger.error(message, e);
    }

    public void logLogin(String username) {
        logger.info("User login: {} at {}", username, dtf.format(LocalDateTime.now()));
    }

    public void logServerRequestReceived(String path ,String requestMethod, String body){
        logger.info("Server request received: Path: {}\nMethod: {}\n Body: {} ", path, requestMethod, body);
    }

    public void logServerResponse(String statusCode,String message){
        logger.info("Server response sent: SC: {} Message: {}", statusCode,message);

    }

    public void logServerWarning(String message){
        logger.warn("Server warning: {}", message);
    }
}
