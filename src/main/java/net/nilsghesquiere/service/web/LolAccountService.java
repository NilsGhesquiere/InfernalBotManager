package net.nilsghesquiere.service.web;

import java.util.List;
import java.util.Optional;

import net.nilsghesquiere.entities.User;
import net.nilsghesquiere.entities.LolAccount;

public interface LolAccountService {
	LolAccount read(Long id);
	Optional <LolAccount> findOptionalById(Long id);
	List<LolAccount> findAll();
	LolAccount create(LolAccount lolAccount);
	void delete(LolAccount lolAccount);
	LolAccount update(LolAccount lolAccount);
	List<LolAccount> findByUser(User user);
	List<LolAccount> findByUserId(Long userId);
	List<LolAccount> findByUserEmail(String email);
	void deleteById(Long id);
}
