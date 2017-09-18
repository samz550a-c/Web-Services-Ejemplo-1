package servicioweb;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("direccionWebService")
public class WebService_Real {

    private static final Logger LOG = Logger.getLogger(WebService_Real.class.getName());

    public WebService_Real() 
    {
        super();
        
        System.out.println("Inicializado el servicio web");
    }

    //==============================================================================================================================================================================================================
    // [GET]
    //==============================================================================================================================================================================================================
    
    /**
     * Sin la anotación PATH hace que, llamando a la [IP:Puerto]/direccionWebService
     * se llame este método en específico.
     * 
     * Ejemplo: http://localhost:9998/direccionWebService
     * 
     * Podría usarse para dar instrucciones de que otros 'métodos' a llamar ofrece y como es el formato que espera recibir
     * en ellos.
     * 
     * Falta ver de que va las notaciones entre los parámetros: @Context Request request
     * 
     * @param request Parece que solo recibe un 'request' petición normal de "página"
     * @return Una cadena de texto que se imprimirá en el navegador que le llame. Se declara la notación @Produces("text/plain") para ello.
     */
    @GET
    @Produces("text/plain")
    public String metodoBienvenidaGET(@Context Request request) 
    {
        System.out.println("____________________________________");
        System.out.println("metodoBienvenidaGET invocado");
        
        String respuestaBienvenida = "";
        
        respuestaBienvenida += "Servicio Web en funcionamiento.";
        respuestaBienvenida += "\n\nLas siguientes son las opciones de configuración en JSON para el servicio:";
        
        return respuestaBienvenida;
    }

    /**
     * Es invocado cuando se llama a [IP:Puerto]/direccionWebService/*rutaGetDevuelveJSON*, donde
     * primeraRutaGet señala específicamente a este método.
     * 
     * @return En el método se indica que retorna un objeto ValueObject pero se usa la etiqueta @Produces("application/json")
     * indicando que lo devolverá como un texto json.
     */
    @GET
    @Path("/rutaGetDevuelveJSON")
    @Produces("application/json")
    public ValueObject metodoGET_retornaValueObjectJSON() 
    {
        System.out.println("____________________________________");
        System.out.println("metodoGET_retornaValueObjectJSON invocado");

        ValueObject valueObject = new ValueObject();
        valueObject.setValorTexto("Este es un valor de texto que debe ir acompañado del número 1097");
        valueObject.setValorEntero(1097);

        return valueObject;

    }
    
    //==============================================================================================================================================================================================================
    // [POST]
    //==============================================================================================================================================================================================================
    
    /**
     * Recibe un JSON pero en formato de solo texto... siendo así, podría mandarsele cualquier cadena de texto.
     * 
     * Al parecer en los post hay que declarar que tipo de datos consume: @Consumes("application/json")
     * 
     * @param request No se que tan necesario es que se declare este parámetro.
     * @param json Cadena de texto donde se supone llega el JSON. 
     * @return Se declara que produce: @Produces("text/plain"). El método marca el valor de retorno de tipo String.
     */
    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    @Path("/primeraRutaPost")
    public String metodoPOST_recibeJSonTexto(@Context Request request, String json) 
    {
        System.out.println("____________________________________");
        System.out.println("metodoPOST_recibeJSonTexto invocado");
        
        System.out.println("Lo que recibió por parámetro: " + json);
        
        return "esto te lo devolvió cordialmente 'primeraRutaPost' = " + json;
    }

    /**
     * Al parecer en el parámetro hace el mapeo como tal del string que viaja por la red al ValueObject.
     * No hay que esforzarse por procesar lo que llega por parámetro.
     * 
     * Este método tiene de especial que da una respuesta de tipo objeto 'Response' estandar... aunque lleva un texto incluido.
     * 
     * @param product Se marca que @Consumes("application/json"). Se espera un ValueObject
     * @return Objeto Response... no se marca que produce nada como tal.
     */
    @POST
    @Path("/segundaRutaRecibeValueObject")
    @Consumes("application/json")
    public Response metodoPOST_recibeJSONValueObject(ValueObject product) 
    {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("________________1____________________");
        System.out.println( name );

        String result = "En teoría guardó en la base de datos lo que se envió por parámetro. : " + product;
        
        return Response.status(201).entity(result).build();

    }

    
}
