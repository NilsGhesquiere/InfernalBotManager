package net.nilsghesquiere.facades;

import java.util.Optional;

import net.nilsghesquiere.entities.User;
import net.nilsghesquiere.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
	private final IUserService userService;

	@Autowired
	public AuthenticationFacadeImpl(IUserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails getAuthenticatedUserDetails() {
		return (UserDetails) getAuthentication().getPrincipal();
	}

	@Override
	public Optional<User> getOptionalAuthenticatedUser() {
		String username = getAuthentication().getName();
		if (username != "anonymousUser"){
			return userService.findByUsername(username);
		} 
		return Optional.empty();
	}
	
	@Override
	public User getAuthenticatedUser() {
		return getOptionalAuthenticatedUser().get();
	}
	
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
}
