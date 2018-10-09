package test.com.h2rd.refactoring.unit;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.h2rd.refactoring.domain.User;
import com.h2rd.refactoring.service.UserManagement;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:UnitTest-context.xml"})
public class UserManagementUnitTest {
	
	@Autowired
	private UserManagement userManagement;
	
	
	/**
	 * When user has no role : false
	 */
	@Test
    public void saveUserNoRoleTest() {
//		System.out.println(userManagement);

        User user = new User();
        user.setName("Fake Name7");
        user.setEmail("fake7@email.com");
        user.setRoles(Arrays.asList());

        User addedUser = userManagement.saveUser(user);
        
        Assert.assertNull(addedUser);
        
    }
	
	/**
	 * When updating user data with no roles
	 */
	@Test
	public void updateUserNoRoleTest() {
		
		userManagement.saveUser(new User("Mark", "fake@email.com", Arrays.asList("admin", "director")));
		
		User user = new User();
        user.setName("Mak");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList());

        boolean b = userManagement.updateUser(1L, user);
        
        Assert.assertEquals(false, b);
		
	}
	
	
	/**
	 * When user update user data with valid inputs
	 */
	@Test
	public void updateUserTest() {
		User user = new User();
        user.setName("Mak");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("guest"));

        boolean b = userManagement.updateUser(1L, user);
        
        Assert.assertEquals(true, b);
	}
	
	/**
	 * When trying to delete a non-existent user
	 */
	@Test
	public void deleteUserNoUserTest() {
		
		boolean b = userManagement.deleteUser(2L);
		Assert.assertEquals(false, b);
	}
	
	
	/**
	 * When deleting an existing user
	 */
	@Test
	public void deleteUserTest() {
		
		boolean b = userManagement.deleteUser(1L);
		Assert.assertEquals(true, b);
	}
	
	
	
	
	

}
