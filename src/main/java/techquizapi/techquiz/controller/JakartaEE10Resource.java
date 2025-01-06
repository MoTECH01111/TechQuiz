package techquizapi.techquiz.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author  Morris Ouedraogo
 */
@Path("jakartaee10")
public class JakartaEE10Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
}
