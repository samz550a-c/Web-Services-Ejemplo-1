package servicioweb;

import com.sun.net.httpserver.HttpServer;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Lanzador_RestStartup {

    private final static int PUERTO = 9998;
    private final static String DIRECCION_HOST = "http://localhost/";

    public static void main(String[] args) 
    {
        URI uri = UriBuilder.fromUri(DIRECCION_HOST).port(PUERTO).build();
        
        ResourceConfig resourceConfig = new ResourceConfig(WebService_Real.class);
        
        HttpServer server = JdkHttpServerFactory.createHttpServer(uri, resourceConfig);
    }
}
