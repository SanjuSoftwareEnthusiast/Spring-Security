package com.security.sanju.userdetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.sanju.models.User;

public class MyUserDetail implements UserDetails {
	
	String username;
	String password;
	boolean active;
	String roles;
	List<GrantedAuthority> authorities;
	
	public MyUserDetail(User user) {
		// TODO Auto-generated constructor stub
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.active = user.isActive();
		
		String arr[] = user.getRoles().split(",");
		authorities = new ArrayList<>();
		for(String str: arr)
		{
			authorities.add(new SimpleGrantedAuthority(str));
		}
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}

}
