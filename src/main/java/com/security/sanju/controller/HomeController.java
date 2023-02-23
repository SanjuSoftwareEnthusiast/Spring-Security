package com.security.sanju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.sanju.Repositories.UserRepository;
import com.security.sanju.models.AuthenticationRequest;
import com.security.sanju.models.AuthenticationResponse;
import com.security.sanju.models.JwtUtil;
import com.security.sanju.userdetails.MyUserDetails;

@RestController
public class HomeController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetails myUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/")
	public ResponseEntity<?> getDefault(){
		return ResponseEntity.ok("Welcome");
	}
	
	@GetMapping("/user")
	public String getUser() {
		return "User Access Page";
	}
	
	@GetMapping("/admin")
	public String getAdmin()
	{
		return "Admin Access Page";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> getToken(@RequestBody AuthenticationRequest authRequest)
	{
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body("Password and Username doesn't match");
		}
		
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());
		
		String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
}

