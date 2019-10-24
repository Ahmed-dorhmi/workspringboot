/**
 * 
 */
package com.moi.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author moi
 *
 */
@Entity
public class Role implements GrantedAuthority{

	@Id
	@GeneratedValue
	Long idrole;
	String role;
	
	@ManyToMany
	List<User> users=new ArrayList<User>();

	/**
	 * @return the idrole
	 */
	public Long getIdrole() {
		return idrole;
	}

	/**
	 * @param idrole the idrole to set
	 */
	public void setIdrole(Long idrole) {
		this.idrole = idrole;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}
	
	
	
}
