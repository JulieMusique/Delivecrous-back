/**
 * 
 */
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.imt.framework.web.delivecrous.entities.Command;
import com.imt.framework.web.delivecrous.entities.Users;
import com.imt.framework.web.delivecrous.repositories.CommandRepository;
import com.imt.framework.web.delivecrous.repositories.ComposeRepository;
import com.imt.framework.web.delivecrous.repositories.DishRepository;
import com.imt.framework.web.delivecrous.repositories.UserRepository;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 9 sept. 2023
 */
public class CommandServices {
	@Autowired
	private CommandRepository commandRepository;

	@Autowired
	private ComposeRepository composeRepository;
	
	@Autowired
	private DishRepository dishRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Command> listCommandsByUser(Users user){
		return commandRepository.findCommandsByidUser(user.getIdUser());
	}
	
	/*public List<Command> listCommandsDeliveredByUser(Users user){
		return commandRepository.
	}*/
}
