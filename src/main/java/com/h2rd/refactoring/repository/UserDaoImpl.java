package com.h2rd.refactoring.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.h2rd.refactoring.domain.User;

/**
 * Class: UserDaoImpl
 * @author kcmmac
 * 
 * A implementation that handles the management of User object
 * using ArrayList
 *  
 */
@Repository("memoryDataDao")
@Scope("singleton")
public class UserDaoImpl implements UserDao{
	
	// ========================================
	// - mutable resource in singleton bean
	// - the access to it should be thread-safe
	// - so, I used add 'synchronized' to methods
	// ========================================
    private ArrayList<User> users;
    long userNb = 0;
    
    
    public synchronized User saveUser(User user) {
    	
        if (users == null) {
            users = new ArrayList<User>();
        }
        
        // set user id number
        user.setUid(++userNb);
        users.add(user);
        
        return user;
    }

    
    public synchronized List<User> getUsers() {
    		if (users == null) {
            users = new ArrayList<User>();
        }
    	
        return users;
        
    }

    
    public synchronized boolean deleteUser(long uid) {
    		
    		if(users == null)
    			return false;
    		    	
        try {
            for (User user : users) {
                if (user.getUid() == uid ) {		// == -> equals
                    return users.remove(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public synchronized boolean updateUser(long uid, User userToUpdate) {
    		if(users == null)
			return false;
    	
        try {
            for (User user : users) {
                if (user.getUid() == uid ) {
                		// update user data except email
                		// email is is a unique key
                		user.setName( userToUpdate.getName() );
                    user.setRoles( userToUpdate.getRoles());
                    
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public synchronized User findUser(long uid) {
    		if( users == null)
    			return null;
    	
        try {
            for (User user : users) {
                if (user.getUid() == uid ) {
                    return user;
                }
            }
        } catch (Exception e) {	
            e.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    public synchronized User findUserEmail(String email) {
	    	if( users == null)
				return null;
		
	    try {
	        for (User user : users) {
	            if (user.getEmail().equals(email) ) {
	                return user;
	            }
	        }
	    } catch (Exception e) {	
	        e.printStackTrace();
	    }
	    return null;
    		
    }
}
