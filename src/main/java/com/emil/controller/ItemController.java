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

import com.emil.domain.Item;
import com.emil.repository.ItemRepository;

@Controller
@RequestMapping(value="/item")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	

	
	@GetMapping
	public String getItems(Model model){
		model.addAttribute("items", itemRepository.findAll());
		return "items";
	}
	
	@GetMapping("/select")
	public String selectItem(Model model){
		model.addAttribute("items", itemRepository.findAll());
		return "selectItem";
	}
	
	@GetMapping(path="/new")
	public String addNewItem(Model model){
		model.addAttribute("item", new Item());
		return "newItem";	
	}
	
	@PostMapping(path="/new")
	public String saveNewItem(@ModelAttribute Item item, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "newItem";
		}
		itemRepository.save(item);
		return "redirect:/item";
		
	}
	
	@GetMapping(path="/edit/{stockId}")
	public String editItem(@PathVariable Long stockId, Model model){
		model.addAttribute("item", itemRepository.findOne(stockId));
		return "editItem";	
		}
	
	@PostMapping(path="/edit/{stockId}")
	public String saveEditItem(@ModelAttribute Item item, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "redirect:/item/edit/"+item.getStockId();
		}
		itemRepository.save(item);
		return "redirect:/item";
		
	}

}
