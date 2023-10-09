// Source code is decompiled from a .class file using FernFlower decompiler.
package com.imt.framework.web.delivecrous.ressources;

import com.imt.framework.web.delivecrous.entities.User;
import com.imt.framework.web.delivecrous.repositories.UserRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@Path("users")
public class UserResource {
    @Autowired
    private UserRepository userRepository;

    public UserResource() {
    }

    /*
     * @POST
     * 
     * @Path("/login")
     * 
     * @CrossOrigin
     * 
     * @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public User getUserByEmailAndPassword(
     * 
     * @FormParam("email") String email,
     * 
     * @FormParam("password") String password) {
     * User user = userRepository.findByEmailAndPassword(email, password);
     * if (user != null && user.verifyPassword(user.getPassword(), password)) {
     * return user;
     * } else {
     * return null;
     * }
     * };
     */

    @GET
    @Path("/login/{email}/{password}")

    @Produces({ "application/json" })
    public User getUserByEmailAndPassword(

            @PathParam("email") String email,

            @PathParam("password") String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.verifyPassword(user.getPassword(), password)) {
            return user;
        } else {
            return null;
        }

    }

    @GET
    @CrossOrigin
    @Produces({ "application/json" })
    public List<User> getUser(@QueryParam("identifiant") Long identifiant) {
        if (identifiant != null) {
            return this.userRepository.getUserWithIdFilter(identifiant);
        }
        return this.userRepository.findAll();
    }

    @POST
    @Consumes({ "application/json" })
    public Response createUser(@RequestBody @NotNull User user) {
        // Vérifier si l'email existe déjà dans la base de données
        if (userRepository.existsByEmail(user.getEmail())) {
            // L'email existe déjà, renvoyer une réponse d'erreur
            return Response.status(Response.Status.CONFLICT)
                    .entity("L'email existe déjà dans la base de données.")
                    .build();
        }

        // L'email est unique, ajouter l'utilisateur à la base de données
        userRepository.save(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes({ "application/json" })
    @Path("/{id}")
    public void updateUser(@PathParam("id") @NotNull Long id, @RequestBody User user) throws Exception {
        Optional<User> searchedUser = this.userRepository.findById(id);
        if (!searchedUser.isEmpty()) {
            User userToUpdate = (User) searchedUser.get();
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPhone(user.getPhone());
            userToUpdate.setAdresse(user.getAdresse());
            userToUpdate.setLogin(user.getLogin());
            userToUpdate.setPassword(user.getPassword());
            this.userRepository.save(userToUpdate);
        }

    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") @NotNull Long id) {
        this.userRepository.deleteById(id);
    }
}
