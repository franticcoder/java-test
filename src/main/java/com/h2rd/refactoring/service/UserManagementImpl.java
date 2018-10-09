package com.h2rd.refactoring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.h2rd.refactoring.domain.User;
import com.h2rd.refactoring.repository.UserDao;

/**
 * Class: UserManagementImpl
 * @author kcmmac
 *
 * an implementation that handles business logic of the app. 
 */
@Service("userBasicService")
public class UserManagementImpl implements UserManagement {
	
	
	@Resource(name="memoryDataDao")
    UserDao userDao;
	
	
	@Override
	public User saveUser(User user) {
		
		if( isUserExist(user.getEmail()) || !isValidRole(user.getRoles())  ) {
			return null;
		}
		
		return userDao.saveUser(user);
	}
	

	@Override
	public boolean updateUser(long uid, User user) {
		
		if( !isValidRole(user.getRoles()) ) {
			return false;
		}
		
		return userDao.updateUser(uid, user);
	}
	
	
	@Override
	public boolean deleteUser(long uid) {
		
		// check if the user exists before deleting
		User user = userDao.findUser(uid);
		if(user == null)			// no user found
			return false;
		
		return userDao.deleteUser(uid);
	}


	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}


	@Override
	public User findUser(long uid) {
		
		if(uid == 0)
			return null;
		
		return userDao.findUser(uid);
	}
	
	/**
	 * isUserExist: check if there is an user with the given email
	 * 
	 * @param email
	 * @return true/false if the result is successful.
	 */
	public boolean isUserExist(String email) {
		
		if(email == null || "".equals(email))
			return false;
		
		// check if email is unique
		User existingUser = userDao.findUserEmail(email);
		if(existingUser != null) 
			return true;
		
		return false;
	}
	
	/**
	 * isValidRole: check if inputed roles are valid
	 * @param roles
	 * @return true/false if the result is successful.
	 */
	public boolean isValidRole(List<String> roles) {
		
		if(roles == null)
			return false;
		
		// check if user has at least 1 role
		if(roles.size() >= 1)
			return true;
		else
			return false;
		
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	

}
