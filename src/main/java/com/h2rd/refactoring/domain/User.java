package com.h2rd.refactoring.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class User {
	
	// set as private for member fields below
	private long uid;		//user ID
    private String name;
    private String email;
    private List<String> roles;
    
    
    public User() {}
    
    public User(String name, String email, List<String> roles) {
    		this.name = name;
    		this.email = email;
    		this.roles = roles;
	}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", roles=" + roles + "]";
	}
    
}
