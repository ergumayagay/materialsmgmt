package com.emil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.emil.domain.Borrower;
import com.emil.domain.Item;
import com.emil.domain.Transaction;

import com.emil.repository.TransactionRepository;

@Controller
@RequestMapping(value="/transaction")
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;
	
	Transaction transaction;
	
	@GetMapping
	public String getTransactions(Model model){
		model.addAttribute("transactions",transactionRepository.findAll());
		return "transactions";
	}
	
	@GetMapping(path="/new")
	public String addNewTransaction(Model model){
		transaction = new Transaction();
		model.addAttribute("transaction",transaction);
		return "transaction";
	}
	
	@RequestMapping(path="/save")
	public String saveNewTransaction(@ModelAttribute Transaction transaction,BindingResult bindingResult){
		transaction = this.transaction;
		if(bindingResult.hasErrors()){
			return "transaction";
		}
		transactionRepository.save(transaction);
		return "redirect:/transaction";
		
}
	
	@RequestMapping(value="/item/{item}")
	public String selectItem(@PathVariable Item item, Model model){
		transaction.setItem(item);
		model.addAttribute("transaction",transaction);
		return "transaction";
	}
	
	@RequestMapping(value="/borrower/{borrower}")
	public String selectBorrower(@PathVariable Borrower borrower, Model model){
		transaction.setBorrower(borrower);
		model.addAttribute("transaction",transaction);
		return "transaction";
	}
	
	@GetMapping(path="/edit/{transactionId}")
	public String editTransaction(@PathVariable Long transactionId ,Model model){
		transaction = transactionRepository.findOne(transactionId);
		model.addAttribute("transaction", transaction);
		return "transaction";
	}
	
	
}
