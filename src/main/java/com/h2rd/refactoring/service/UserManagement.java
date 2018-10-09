package com.h2rd.refactoring.service;

import java.util.List;

import com.h2rd.refactoring.domain.User;
import com.h2rd.refactoring.repository.UserDao;

/**
 * Interface: UserManagement
 * @author kcmmac
 *
 *
 */
public interface UserManagement {
	
	/**
	 * saveUser: check the validation of the inputed email and the role and then save data
	 * 
	 * @param user object
	 * @return added User object
	 */
	public User saveUser(User user);
	
	/**
	 * getUsers: get all users
	 * 
	 * @return a list of User Object
	 */
	public List<User> getUsers();
	
	/**
	 * deleteUser: delete an user by uid
	 * 
	 * @param uid
	 * @return true/false if the result is successful.
	 */
	public boolean deleteUser(long uid);
	
	/**
	 * updateUser: check the validation of inputed role and update user's data
	 * 
	 * @param uid
	 * @param user object
	 * @return true/false if the result is successful.
	 */
	public boolean updateUser(long uid, User user);
	
	/**
	 * findUser: find an user by uid
	 *  
	 * @param uid
	 * @return User object
	 */
	public User findUser(long uid);
	
	/**
	 * setUserDao: setter for DI 
	 * 
	 * @param userDao
	 */
	public void setUserDao(UserDao userDao);
}
