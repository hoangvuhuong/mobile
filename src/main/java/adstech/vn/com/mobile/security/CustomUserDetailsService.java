package adstech.vn.com.mobile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import adstech.vn.com.mobile.model.User;
import adstech.vn.com.mobile.repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {
	 @Autowired
	    UserRepository userRepository;
	   
	    @Override
	    @Transactional
	    public UserDetails loadUserByUsername(String email)
	            throws UsernameNotFoundException {
	        User user = userRepository.getByEmail(email)
	                .orElseThrow(() ->
	                        new UsernameNotFoundException("User not found with userName : " + email)
	        );
	       
	        return UserPrincipal.create(user);
	    }

	    @Transactional
	    public UserDetails loadUserById(int id) {
	        User user = userRepository.findByUserId(id).orElseThrow(
	            () -> new UsernameNotFoundException("User not found")
	        );
	        return UserPrincipal.create(user);
	    }
}