package com.packt.cardatabase.services;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.packt.cardatabase.models.User;
import com.packt.cardatabase.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository userRepo;
	
	public UserDetailServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentUser = userRepo.findUserByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPassword(),
				true, true, true, true, AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
	}

}
