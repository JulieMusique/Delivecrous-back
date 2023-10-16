package com.imt.framework.web.delivecrous;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imt.framework.web.delivecrous.entities.User;
import com.imt.framework.web.delivecrous.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.repositories.DishRepository;


@SpringBootApplication
public class DelivecrousApplication {

	public static void main(String[] args) {
		SpringApplication.run(DelivecrousApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(DishRepository dishRepository){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Dish>> typeReference = new TypeReference<List<Dish>>(){};
			InputStream inputStream = getClass().getResourceAsStream("/json/dish.json");
			try{
				List<Dish> dishes = mapper.readValue(inputStream, typeReference);
				for (Dish dish : dishes) {
					boolean alreadyIn = false;
					List<Dish> dishesalreadyIn = dishRepository.findAll();
					for(Dish dishalreadyIn : dishesalreadyIn){
						if(dishalreadyIn.equals(dish)){
							alreadyIn = true;
							break;
						}
					}
					if(!alreadyIn){
						dish = dish.initDish(dish);
						dishRepository.save(dish);
						System.out.println("Dishes : " + dish.getTitle() + " added successfully");
					} else
						System.out.println("Dishes : " + dish.getTitle() + " already existing");
				}
			} catch (IOException e){
				System.out.println("Unable to save dishes: " + e.getMessage());
			}
		};
	}

}