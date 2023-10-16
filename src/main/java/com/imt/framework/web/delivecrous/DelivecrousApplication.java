package com.imt.framework.web.delivecrous;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.User;
import com.imt.framework.web.delivecrous.repositories.DishRepository;
import com.imt.framework.web.delivecrous.repositories.UserRepository;

@SpringBootApplication
public class DelivecrousApplication {

        public static void main(String[] args) {
                SpringApplication.run(DelivecrousApplication.class, args);
        }

        @Bean
        CommandLineRunner runner(UserRepository userRepository) {
                return args -> {
                        ObjectMapper mapper = new ObjectMapper();
                        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
                        };
                        InputStream inputStream = DelivecrousApplication.class.getResourceAsStream("/user.json");
                        try {
                                List<User> users = mapper.readValue(inputStream, typeReference);
                                for (User user : users) {
                                        boolean alreadyIn = false;
                                        List<User> usersAlreadyIn = userRepository.findAll();
                                        for (User u : usersAlreadyIn) {
                                                if (u.equals(user)) {
                                                        alreadyIn = true;
                                                        break;
                                                }
                                        }
                                        if (!alreadyIn) {
                                                userRepository.save(user);
                                                System.out.println("Users : " + user.getFirstName()
                                                                + " added successfully");
                                        } else {
                                                System.out.println(
                                                                "Users : " + user.getFirstName() + " already exists");
                                        }

                                }
                        } catch (IOException e) {
                                System.out.println("Unable to save users: " + e.getMessage());
                        }
                };
        }

}
