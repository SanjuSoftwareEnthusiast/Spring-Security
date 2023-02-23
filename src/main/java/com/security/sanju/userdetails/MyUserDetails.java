package com.security.sanju.userdetails;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.sanju.Repositories.UserRepository;
import com.security.sanju.models.User;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> users = userRepository.findByUsername(username);
		
		if(users==null)
		{
			throw new UsernameNotFoundException("User does exist with the given username");
		}
		
		return new MyUserDetail(users.get());
	}

}
