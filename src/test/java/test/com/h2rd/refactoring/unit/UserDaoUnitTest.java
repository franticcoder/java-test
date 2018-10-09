package test.com.h2rd.refactoring.unit;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.h2rd.refactoring.domain.User;
import com.h2rd.refactoring.repository.UserDao;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:UnitTest-context.xml"})
public class UserDaoUnitTest {
		
	@Resource(name="memoryDataDao")
	private UserDao userDao;
    
    
    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
    
    
    @Test
    public void saveUserTest() {

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        User ua1 = userDao.saveUser(user);
        
//        System.out.println("u1:" + ua1.getUid() + " u2:" + ua2.getUid());
        
        Assert.assertEquals(true, (ua1 != null) );
        
    }
    
    @Test
    public void getUsersTest() {
    		
    		List<User> users = userDao.getUsers();
//    		System.out.println("users.size:"+ users.size());
    		Assert.assertEquals(true, users.size() >= 0);
    	
    }
    
    @Test 
    public void deleteUserTest() {
    	
    		User user2 = new User();
        user2.setName("Fake Name2");
        user2.setEmail("fake2@email.com");
        user2.setRoles(Arrays.asList("admin", "master"));
        User ua2 = userDao.saveUser(user2);
    		
    		boolean b = userDao.deleteUser(ua2.getUid());
    		Assert.assertEquals(true, b);
    	
    }
    
    @Test 
    public void updateUserTest() {
    		User user = new User();
        user.setName("Fake Name3");
        user.setEmail("fake3@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        User ua1 = userDao.saveUser(user);
        
    		User newuser = new User();
    		newuser.setName("Updated Name");
    		newuser.setEmail("fake3@email.com");
    		newuser.setRoles(Arrays.asList("admin"));
        
        System.out.println(">>> >>>" + userDao.getUsers().size());
        
    		boolean b = userDao.updateUser(ua1.getUid(), user);
    		
    		Assert.assertEquals(true, b);
    	
    }
    
    @Test
    public void findUserTest() {
    		User user = new User();
        user.setName("Fake Name4");
        user.setEmail("fake4@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        User ua1 = userDao.saveUser(user);
    		
    		User foundUser = userDao.findUser(ua1.getUid());
    		
    		Assert.assertNotNull(foundUser);
    }
    
    
    @Test
    public void findUserEmailTest() {
    		User user = new User();
        user.setName("Fake Name5");
        user.setEmail("fake5@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        userDao.saveUser(user);
        
    		String email = "fake5@email.com";
    		User foundUser = userDao.findUserEmail(email);
    		
    		Assert.assertEquals("Fake Name5", foundUser.getName());
    }
    

}