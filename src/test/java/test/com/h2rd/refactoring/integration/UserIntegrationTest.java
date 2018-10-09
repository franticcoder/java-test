package test.com.h2rd.refactoring.integration;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.h2rd.refactoring.domain.User;
import com.h2rd.refactoring.service.UserManagement;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:IntegrationTest-context.xml"})

public class UserIntegrationTest {
	
	@Autowired
	private UserManagement userManagement;
	
	
	@Before
	public void setUp() throws Exception {
	} 
	
	
	/**
	 * Add an user and delete an user 
	 * and compare the total users 
	 */
	@Test
	public void ChkUserCntAddAfterDeleteTest() {
		
		
		// get total user number
		List<User> users = userManagement.getUsers();
		int beforeUserCnt = users.size();
		
		// add an user
		User user = new User("Jack", "jack@apple.com", Arrays.asList("manager"));
		User addedUser = userManagement.saveUser(user);
		Assert.assertEquals("Jack", addedUser.getName());
		
		// delete the added user
		boolean bDel = userManagement.deleteUser(addedUser.getUid());
		Assert.assertEquals(true, bDel);
		
		List<User> users_2 = userManagement.getUsers();
		int afterUserCnt = users_2.size();
		
		Assert.assertEquals(beforeUserCnt, afterUserCnt);
		
	}
	
	/**
	 * 
	 */
	@Test
	public void ChkUpdateAndFind() {
		// add an user
		User user = new User("Bob", "bob@apple.com", Arrays.asList("director"));
		User addedUser = userManagement.saveUser(user);
		Assert.assertEquals("Bob", addedUser.getName());
		
		// update bob's role
		User newUser = new User();
		newUser.setName("Bob Smith");
		newUser.setRoles(Arrays.asList("president"));
		
		boolean b = userManagement.updateUser(addedUser.getUid(), newUser);
		Assert.assertEquals(true, b);
		
		// compare updated data
		User foundUser = userManagement.findUser(addedUser.getUid());
		Assert.assertEquals("Bob Smith", foundUser.getName());
		
	}
	
	
	
	
	
//	@Test
//	public void createUserTest() {
//		UserResource userResource = new UserResource();
//		
//		User integration = new User();
//        integration.setName("integration");
//        integration.setEmail("initial@integration.com");
//        integration.setRoles(new ArrayList<String>());
//        
//        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
//        Assert.assertEquals(200, response.getStatus());
//	}
//
//	@Test
//	public void updateUserTest() {
//		UserResource userResource = new UserResource();
//		
//		createUserTest();
//        
//        User updated = new User();
//        updated.setName("integration");
//        updated.setEmail("updated@integration.com");
//        updated.setRoles(new ArrayList<String>());
//        
//        Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
//        Assert.assertEquals(200, response.getStatus());
//	}
}
