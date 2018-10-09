package com.h2rd.refactoring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2rd.refactoring.domain.User;
import com.h2rd.refactoring.service.UserManagement;


@Controller
public class UserController{
	
	// Dependency Injection
	@Resource(name="userBasicService")
    UserManagement userManagement;
	
	/**
	 * addUser: add an user
	 * @param name
	 * @param email
	 * @param roles
	 * @return return added user object
	 */
    @RequestMapping(value= "/users", method=RequestMethod.POST )
    public ModelAndView addUser(@RequestParam String name, @RequestParam String email,
    							@RequestParam List<String> roles) {
    	
        User user = new User(name, email, roles);
        
        User addedUser = userManagement.saveUser(user);
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("RESULT", addedUser);
        return mv; 
        
    }
    
    /**
     * updateUser: update an user's data 
     * @param uid
     * @param newName
     * @param newRoles
     * @return return the result if it is success
     */
    @RequestMapping(value="/users/{uid}", method=RequestMethod.PUT)
    public ModelAndView updateUser(@PathVariable long uid, @RequestParam String newName, 
    								@RequestParam List<String> newRoles) {

    		User user = new User();
    		user.setName(newName);
    		user.setRoles(newRoles);
        
        boolean isSuccess = userManagement.updateUser(uid, user);
        
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("RESULT", isSuccess);
        return mv;
    }
    
    /**
     * deleteUser: delete an user by uid    
     * @param uid
     * @return return the result if it is success
     */
    @RequestMapping(value= "/users/{uid}", method=RequestMethod.DELETE )
    public ModelAndView deleteUser(@PathVariable long uid) {
    	
        boolean isSuccess = userManagement.deleteUser(uid);
        
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("RESULT", isSuccess);
        return mv;
    }
    
    /**
     * getUsers: get all currently stored users
     * @return list of User object.
     */
    @RequestMapping(value= "/users", method=RequestMethod.GET )
    public ModelAndView getUsers() {
    	    	
	    	List<User> users = userManagement.getUsers();
	    	if (users == null) {
	    		users = new ArrayList<User>();
	    	}
	    	
	    	System.out.println(userManagement + "users size=" + users.size());

	    	ModelAndView mv = new ModelAndView("main");
	    mv.addObject("RESULT", users);
        return mv;
    }
    
    /**
     * findUser: find a specific user with uid
     * @param uid
     * @return a User object.
     */
    @RequestMapping(value= "/users/{uid}", method=RequestMethod.GET )
    public ModelAndView findUser(@PathVariable long uid) {
        
        User user = userManagement.findUser(uid);
        
        System.out.println(uid+"]found user:" + user);
        
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("RESULT", user);
        return mv;
    }

	public void setUserManagement(UserManagement userManagement) {
		this.userManagement = userManagement;
	}

    	
}
