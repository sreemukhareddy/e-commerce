package com.ecom.poc.ecompoc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecom.poc.ecompoc.models.CreditCard;

@Repository
@Transactional
public class CreditCardReposiotry {

	@Autowired
	private EntityManager entityManager;
	
	public void addCreditCard(CreditCard creditCard) {
		CreditCard findCreditCard = null;
		findCreditCard = findCreditCard(creditCard.getCredit_card_number(), creditCard.getCustomer().getName());
		if(findCreditCard == null) {
			entityManager.persist(creditCard);
			return;
		}
		Integer id= findCreditCard.getId();
		entityManager.remove(findCreditCard);
		findCreditCard.setCustomer(creditCard.getCustomer());
		findCreditCard.setCredit_card_number(creditCard.getCredit_card_number());
		entityManager.persist(findCreditCard);
	}
	
	public CreditCard findCreditCard(Integer credit_card_number, String cus_name) {
		
		Query createNamedQuery = entityManager.createNamedQuery("get_credit_card_by_credit_number");
		//createNamedQuery.setParameter("cre_card_num", credit_card_number);
		createNamedQuery.setParameter("cus_name", cus_name);
		List<CreditCard> resultList;
		resultList= createNamedQuery.getResultList();
		
		if(resultList != null && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		
		return null;
	}
}
