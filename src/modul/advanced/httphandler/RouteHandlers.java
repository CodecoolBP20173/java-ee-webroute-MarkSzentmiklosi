package modul.advanced.httphandler;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class RouteHandlers {

    @WebRoute("/test")
    public static void onTest(HttpExchange requestData) throws IOException {
        String response = "This is the response";
        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
