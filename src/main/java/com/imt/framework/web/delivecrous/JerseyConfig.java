package com.imt.framework.web.delivecrous;


import com.imt.framework.web.delivecrous.ressources.CommandResource;
import com.imt.framework.web.delivecrous.ressources.DishResource;
import com.imt.framework.web.delivecrous.ressources.UserResource;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("DelivCROUS")
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(DishResource.class);
        register(CommandResource.class);
        register(UserResource.class);
    }

}