package modul.advanced.httphandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class TestController {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String requestUrl = "" +t.getRequestURI();
            Method[] methods = RouteHandlers.class.getMethods();
            for(Method method:methods){
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation:annotations) {
                    if(annotation instanceof WebRoute){
                        WebRoute myAnnotation = (WebRoute) annotation;
                        if(myAnnotation.value().equals(requestUrl)){
                            try {
                                method.invoke(null,t);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

}