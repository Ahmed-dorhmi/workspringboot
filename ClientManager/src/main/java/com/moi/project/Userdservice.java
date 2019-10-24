/**
 * 
 */
package com.moi.project;

import javax.sound.midi.SysexMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moi.project.model.User;
import com.moi.project.repo.IUser;

/**
 * @author moi
 *
 */
@Service
public class Userdservice implements UserDetailsService{

	
	@Autowired
	IUser userepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userepo.findByUsername(username);
		u.getAuthorities().stream().
		forEach(x->System.out.println(x.getAuthority()));
		return u;
	}

}
