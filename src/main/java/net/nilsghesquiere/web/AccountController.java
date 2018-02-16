package net.nilsghesquiere.web;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import net.nilsghesquiere.entities.AppUser;
import net.nilsghesquiere.entities.LolAccount;
import net.nilsghesquiere.facades.AuthenticationFacade;
import net.nilsghesquiere.services.LolAccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/accounts")
@PreAuthorize("hasAnyAuthority('appadmin','basicuser')")
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	private static final String LIST_VIEW = "accounts/list";
	
	private final LolAccountService lolAccountService;
	private final AuthenticationFacade authenticationFacade;

	@Autowired
	public AccountController(LolAccountService lolAccountService, AuthenticationFacade authenticationFacade) {
		this.lolAccountService = lolAccountService;
		this.authenticationFacade = authenticationFacade;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView list() {
		Optional<AppUser> currentUser = authenticationFacade.getOptionalAuthenticatedUser();
		LOGGER.info("Loading Accounts list for user [" + currentUser.get().getUsername() + "].");
		return new ModelAndView(LIST_VIEW).addObject("currentUser", currentUser.get());
		}
}