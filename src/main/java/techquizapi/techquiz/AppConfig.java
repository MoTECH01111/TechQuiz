/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package techquizapi.techquiz; // Package declaration

/**
 * Date: 30/12/2024
 * @author morrisouedraogo
 * App Configuration 
 */

import org.glassfish.jersey.server.ResourceConfig; //For developing RESTFUL API WEB service.
import jakarta.ws.rs.ApplicationPath; //Identifies the path that serves as the base URI.

@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("techquizapi.techquiz.controller"); //App configuration package with controls for web application

    
    }
}