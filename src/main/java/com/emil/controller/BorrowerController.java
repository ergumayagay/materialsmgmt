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
import com.emil.repository.BorrowerRepository;

@Controller
@RequestMapping(value="/borrower")
public class BorrowerController {
	
	@Autowired
	BorrowerRepository borrowerRepository;
	
	@GetMapping
	public String getBorrowers(Model model){
		model.addAttribute("borrowers", borrowerRepository.findAll());
		return "borrowers";
	}
	
	@GetMapping(path="/new")
	public String addNewBorrower(Model model){
		model.addAttribute("borrower",new Borrower());
		return "newBorrower";
	}
	
	@PostMapping(path="/new")
	public String saveNewBorrower(@ModelAttribute Borrower borrower, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "newBorrower";
		}
		borrowerRepository.save(borrower);
		return "redirect:/borrower";
	}
	
	@GetMapping(path="/edit/{borrowerId}")
	public String editBorrower(@PathVariable Long borrowerId, Model model){
		model.addAttribute("borrower", borrowerRepository.findOne(borrowerId));
		return "editBorrower";
		
	}
	 
	@PostMapping(path="/edit/{borrowerId}")
	public String saveEditBorrower(@ModelAttribute Borrower borrower, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "redirect:/borrower/edit/"+borrower.getBorrowerId();
		}
		borrowerRepository.save(borrower);
		return "redirect:/borrower";
		
	}
	
	@GetMapping("/select")
	public String selectBorrower(Model model){
		model.addAttribute("borrowers", borrowerRepository.findAll());
		return "selectBorrower";
	}
	
	
	

}
