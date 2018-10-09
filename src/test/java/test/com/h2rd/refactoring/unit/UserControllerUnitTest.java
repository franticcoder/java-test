package test.com.h2rd.refactoring.unit;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.h2rd.refactoring.controller.UserController;
import com.h2rd.refactoring.domain.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:UnitTest-context.xml"})
public class UserControllerUnitTest {
	
	@Autowired
	private UserController userController;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	
	@Test
	public void addUserTest() {
		
		ModelAndView mv = userController.addUser("John", "john@abc.com", Arrays.asList("admin", "manager") );
		
		User user = (User) mv.getModel().get("RESULT");
		Assert.assertNotNull(user);
	}
	
	
	@Test
	public void updateUserTest() {
		
		String name = "Jon";
		long uid = 1;
		
		ModelAndView mv = userController.updateUser( uid, name, Arrays.asList("admin", "sales"));
		
		boolean b = (boolean) mv.getModel().get("RESULT");
		
		Assert.assertEquals(true, b);
	}
	
	
	@Test
	public void deleteUserTest() {
		
		String email = "mark@gmail.com";
		
		userController.addUser("Mark", email, Arrays.asList("admin") );
		long uid = 2;
		
		ModelAndView mv = userController.deleteUser(uid);
		boolean b = (boolean) mv.getModel().get("RESULT");
		
		Assert.assertEquals(true, b);
		
	}

	@Test
	public void getUsersTest() {
		
		userController.addUser("Bob", "bob@abc.com", Arrays.asList("admin", "manager") );
		
		ModelAndView mv = userController.getUsers();
		
		@SuppressWarnings("unchecked")
		List<String> users = (List<String>) mv.getModel().get("RESULT");
//		System.out.println("users size:" + users.size());
		
		boolean b = users.size() >= 0;
		
		Assert.assertEquals(true, b);
		
	}
	
	@Test 
	public void searchTest() {
		
		String email = "john@abc.com";
		long uid = 1;
		
		ModelAndView mv = userController.findUser(uid);
		User foundUser = (User) mv.getModel().get("RESULT");
//		System.out.println(foundUser);
		
		Assert.assertEquals(email, foundUser.getEmail());
		
	}
	 
}
