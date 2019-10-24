/**
 * 
 */
package com.moi.project.repo;

import org.springframework.data.repository.CrudRepository;

import com.moi.project.model.User;

/**
 * @author moi
 *
 */
public interface IUser extends CrudRepository<User, Long>{
		public User findByUsername(String name);
}
