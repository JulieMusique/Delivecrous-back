package com.imt.framework.web.delivecrous.ressources;

import com.imt.framework.web.delivecrous.entities.Users;
import com.imt.framework.web.delivecrous.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@Path("users")
public class UserResource {
    @Autowired
    private UserRepository userRepository;

    public UserResource() {
    }

    @GET
    @Produces({ "application/json" })
    public List<Users> getUser(@QueryParam("identifiant") Long identifiant) {
        if (identifiant != null) {
            return this.userRepository.getUsersWithIdFilter(identifiant);
        }
        return this.userRepository.findAll();
    }

    @POST
    @Consumes({ "application/json" })
    public void createUser(@RequestBody @NotNull Users user) {
        this.userRepository.save(user);
    }

    @PUT
    @Consumes({ "application/json" })
    @Path("/{id}")
    public void updateUser(@PathParam("id") @NotNull Long id, @RequestBody Users user) throws Exception {
        Optional<Users> searchedUser = this.userRepository.findById(id);
        if (!searchedUser.isEmpty()) {
            Users userToUpdate = (Users) searchedUser.get();
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