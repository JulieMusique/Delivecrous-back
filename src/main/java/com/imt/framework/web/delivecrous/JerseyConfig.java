// Source code is decompiled from a .class file using FernFlower decompiler.
package com.imt.framework.web.delivecrous;

import com.imt.framework.web.delivecrous.ressources.UserResource;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("demo")
@Configuration
public class JerseyConfig extends ResourceConfig {
   public JerseyConfig() {
      this.register(UserResource.class);
   }
}
