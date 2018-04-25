package net.nilsghesquiere.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.nilsghesquiere.entities.ClientSettings;
import net.nilsghesquiere.entities.InfernalSettings;
import net.nilsghesquiere.entities.User;
import net.nilsghesquiere.service.web.ClientSettingsService;
import net.nilsghesquiere.service.web.InfernalSettingsService;
import net.nilsghesquiere.util.facades.AuthenticationFacade;
import net.nilsghesquiere.web.annotations.ViewController;
import net.nilsghesquiere.web.dto.ClientSettingsDTO;
import net.nilsghesquiere.web.dto.InfernalSettingsDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ViewController
@RequestMapping("/settings")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class SettingsController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SettingsController.class);
	private static final String CLIENTS_VIEW = "settings/clients";
	private static final String CLIENTSETTINGS_VIEW = "settings/clientsettings";
	private static final String INFERNAL_VIEW = "settings/infernal";
	
	@Autowired
	private AuthenticationFacade authenticationFacade;
	
	@Autowired InfernalSettingsService infernalSettingsService;
	
	@Autowired ClientSettingsService clientSettingsService;

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public ModelAndView viewClients(HttpServletRequest request){
		return new ModelAndView(CLIENTS_VIEW);
	}
	
	@RequestMapping(value = "/clientsettings", method = RequestMethod.GET)
	public ModelAndView viewClientSettings(HttpServletRequest request){
		User user = authenticationFacade.getAuthenticatedUser();
		List<ClientSettings> clientSettingsList = clientSettingsService.findByUser(user);
		return new ModelAndView(CLIENTSETTINGS_VIEW).addObject("clientSettingsList",clientSettingsList);
	}
	
	@RequestMapping(value = "/clientsettings/new", method = RequestMethod.GET)
	public ModelAndView newClientSettingsForm(HttpServletRequest request){
		//The authenticated user
		User user = authenticationFacade.getAuthenticatedUser();
		//List of client settings for the authenticated user
		List<ClientSettings> clientSettingsList = clientSettingsService.findByUser(user);
		//new ClientSettingsDTO
		ClientSettingsDTO dto = new ClientSettingsDTO();
		return new ModelAndView(CLIENTSETTINGS_VIEW).addObject("clientSettingsList",clientSettingsList).addObject("settings",dto);
	}
	
	@RequestMapping(value = "/clientsettings/new", method = RequestMethod.POST)
	public String newClientSettings(@ModelAttribute("settings") @Valid ClientSettingsDTO settingsDTO,BindingResult bindingResult, Model model, HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("failM", "Failure creating Client settings");
			return CLIENTSETTINGS_VIEW;
		}
		//The authenticated user
		User user = authenticationFacade.getAuthenticatedUser();
		
		//Check if name exists already for that user
		ClientSettings alreadyExistingSettings = clientSettingsService.findByUserIdAndName(user.getId(), settingsDTO.getName());
		if(alreadyExistingSettings != null){
			model.addAttribute("failM", "Client settings with the name '"+ settingsDTO.getName() +"' already exist for this user!");
			return CLIENTSETTINGS_VIEW;
		}
		//build clientSettings from the dto
		ClientSettings newClientSettings = new ClientSettings(settingsDTO);
		
		//set user
		newClientSettings.setUser(user);
		
		//create the settings in the database
		clientSettingsService.create(newClientSettings);
		
		request.getSession().setAttribute("resultM","Succesfully created Client settings '" + settingsDTO.getName() + "'");
		return "redirect:/settings/clientsettings";
	}
	
	@RequestMapping(value = "/infernal", method = RequestMethod.GET)
	public ModelAndView viewInfernalSettings(HttpServletRequest request){
		InfernalSettings settings = infernalSettingsService.getByUserId(authenticationFacade.getAuthenticatedUser().getId());
		InfernalSettingsDTO dto = new InfernalSettingsDTO(settings);
		String result = (String) request.getSession().getAttribute("resultM");
		if (result != null && !result.isEmpty()){
			request.getSession().removeAttribute("resultM");
			if(result.toLowerCase().contains("success")){
				return new ModelAndView(INFERNAL_VIEW).addObject("settings",dto).addObject("successM", result);
			}
			return new ModelAndView(INFERNAL_VIEW).addObject("settings",dto).addObject("resultM", result);
		} else {
			return new ModelAndView(INFERNAL_VIEW).addObject("settings",dto);
		}
	}
	
	@RequestMapping(value = "/infernal", method = RequestMethod.POST)
	public String editInfernalSettings(@ModelAttribute("settings") @Valid InfernalSettingsDTO settingsDTO,BindingResult bindingResult, Model model, HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("failM", "Failure updating InfernalBot settings");
			return INFERNAL_VIEW;
		}
		InfernalSettings oldSettings = infernalSettingsService.getByUserId(authenticationFacade.getAuthenticatedUser().getId());
		InfernalSettings newSettings = new InfernalSettings(settingsDTO);
		newSettings.setUser(oldSettings.getUser());
		newSettings.setSets(oldSettings.getSets());
		newSettings.setId(oldSettings.getId());
		newSettings.setReplaceConfig(oldSettings.getReplaceConfig());
		newSettings.setLolHeight(oldSettings.getLolHeight());
		newSettings.setLolWidth(oldSettings.getLolWidth());
		infernalSettingsService.update(newSettings);
		request.getSession().setAttribute("resultM","Succesfully changed InfernalBot settings!");
		return "redirect:/settings/infernal";
	}

}