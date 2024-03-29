package net.nilsghesquiere.service.rest;

import java.util.ArrayList;
import java.util.List;

import net.nilsghesquiere.entities.Client;
import net.nilsghesquiere.entities.ClientData;
import net.nilsghesquiere.entities.User;
import net.nilsghesquiere.service.web.ClientDataService;
import net.nilsghesquiere.service.web.ClientService;
import net.nilsghesquiere.service.web.UserService;
import net.nilsghesquiere.util.facades.AuthenticationFacade;
import net.nilsghesquiere.util.wrappers.ClientDataMap;
import net.nilsghesquiere.util.wrappers.ClientDataWrapper;
import net.nilsghesquiere.web.error.ClientNotFoundException;
import net.nilsghesquiere.web.error.UserIsNotOwnerOfResourceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientdata")
public class ClientDataRestController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientDataRestController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClientDataService clientDataService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AuthenticationFacade authenticationFacade;	
	
	@RequestMapping(path = "/user/{userid}", method = RequestMethod.GET)
	public ResponseEntity<ClientDataWrapper> findClientsByUserId(@PathVariable Long userid) {
		//VARS
		ClientDataWrapper wrapper = new ClientDataWrapper();
		String error = "";
		
		//USER CHECK
		User user = userService.findUserByUserId(userid);
		if(!authenticationFacade.getAuthenticatedUser().equals(user)){
			throw new UserIsNotOwnerOfResourceException();
		}
		
		//PROCESSING
		List<ClientData> clientDatas = clientDataService.findByClientUserId(userid);
		
		//RESPONSE
		wrapper.setError(error);
		wrapper.add("data",clientDatas);
		
		
		//RETURN
		return new ResponseEntity<ClientDataWrapper>(wrapper, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/user/{userid}/client/{clientid}", method = RequestMethod.POST)
	public ResponseEntity<ClientDataWrapper> createOrUpdate(@PathVariable Long userid, @PathVariable Long clientid, @RequestBody ClientDataMap clientDataMap) {
		//VARS
		ClientDataWrapper wrapper = new ClientDataWrapper();
		List<ClientData> returnClientDatas = new ArrayList<>();
		String error = "";
		
		//USER CHECK
		User user = userService.findUserByUserId(userid);
		if(!authenticationFacade.getAuthenticatedUser().equals(user)){
			throw new UserIsNotOwnerOfResourceException();
		}
		
		//CLIENT CHECK
		Client client = clientService.read(clientid);
		if (client == null){
			throw new ClientNotFoundException(clientid);
		}
		
		//CLIENT USER CHECK
		if(!client.getUser().equals(user)){
			throw new UserIsNotOwnerOfResourceException();
		}
		
		//LOOP (Will always be 1 clientData for now)
		for(ClientData clientData: clientDataMap.getMap().values()){
			//CHECKS
			if (clientData == null && error.equals("")){
				error = "Client data is empty";
			}
			//PROCESSING
			clientData.setClient(client);
			ClientData clientDataFromDB = clientDataService.findByClientId(clientid);
			if (clientDataFromDB != null){
				clientDataService.delete(clientDataFromDB);
			}
			
			//hier gaat het momenteel mis: onderliggende queuers kennen hun clientdata niet
			//waarschijnlijk opgelost nu
			ClientData newClientData = clientDataService.create(clientData);

			if(newClientData != null){
				returnClientDatas.add(newClientData);
			} else {
				error ="Unspecified failure";
			}
		}
		
		//RESPONSE
		wrapper.add("data",returnClientDatas);
		wrapper.setError(error);

		//RETURN
		return new ResponseEntity<ClientDataWrapper>(wrapper,HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/user/{userid}/delete",method = RequestMethod.POST)
	public ResponseEntity<ClientDataWrapper> delete(@PathVariable Long userid, @RequestBody ClientDataMap clientDataMap) {
		ClientDataWrapper wrapper = new ClientDataWrapper();
		List<ClientData> deletedClientDatas = new ArrayList<>();
		
		//USER CHECK
		User user = userService.findUserByUserId(userid);
		if(!authenticationFacade.getAuthenticatedUser().equals(user)){
			throw new UserIsNotOwnerOfResourceException();
		}
		
		for (ClientData clientData : clientDataMap.getMap().values()){
			clientDataService.deleteById(clientData.getId());
			deletedClientDatas.add(clientData);
		}
		wrapper.add("data",deletedClientDatas);
		return new ResponseEntity<ClientDataWrapper>(wrapper,HttpStatus.OK);
	}	
	
}
