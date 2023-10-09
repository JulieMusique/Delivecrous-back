/**
 * 
 */
package com.imt.framework.web.delivecrous;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.imt.framework.web.delivecrous.repositories.ComposeRepository;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 9 sept. 2023
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CommandTest {
	@Autowired
	private ComposeRepository commandItem;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void test() {
		//Dish dish = entityManager.find(Dish.class, 1);
		assertTrue(true);
	}

}
