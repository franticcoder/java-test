package com.h2rd.refactoring.repository;

import java.util.List;

import com.h2rd.refactoring.domain.User;

/**
 * Interface: UserDao
 * 
 * @author kcmmac
 * 
 */
public interface UserDao {
	
	/**
	 * saveUser: save an user with unique id number
	 * 
	 * @param user object
	 * @return added user object
	 */
	public User saveUser(User user);
	
	
	/**
	 * getUsers: get a list of all user objects
	 * 
	 * @return list of User object
	 */
	public List<User> getUsers();
	
	/**
	 * deleteUser: delete an user with uid
	 * 
	 * @param uid
	 * @return true/false if the result is successful.
	 */
	public boolean deleteUser(long uid);
	
	/**
	 * updateUser: update an user's information with uid
     * - email is key so it cannot be updated.
     * 
	 * @param uid
	 * @param userToUpdate
	 * @return true/false if the result is successful.
	 */
	public boolean updateUser(long uid, User userToUpdate);
	
	/**
	 * findUser: find an user by uid 
	 * 
	 * @param uid
	 * @return the found User object
	 */
	public User findUser(long uid);
	
	/**
	 * findUserEmail: find an user by email
	 * 
	 * @param email
	 * @return found User Object.
	 */
	public User findUserEmail(String email);
	
}
