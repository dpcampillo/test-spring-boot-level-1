package coop.tecso.examen.service.rules;

import coop.tecso.examen.model.Account;
import coop.tecso.examen.repository.MovementRepository;

public class AccountRule {

	private MovementRepository movementRepository;

	public AccountRule(MovementRepository movementRepository) {
		this.movementRepository = movementRepository;
	}

	public boolean canDeleteAccount(Account account) {
		return movementRepository.countByAccount(account) == 0L;
	}

}
