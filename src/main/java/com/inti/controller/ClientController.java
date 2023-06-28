package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.model.Client;
import com.inti.repository.IClientRepository;

@Controller
public class ClientController {
	
	@Autowired
	IClientRepository icr;
	
	@GetMapping("creerClient")
	public String creerClient()
	{
		return "creerClient";
	}
	
	@PostMapping("creerClient")
	public String creerClient(@ModelAttribute("client") Client c)
	{		
		icr.save(c);
		
		return "redirect:/listeClient";
	}
	
	@GetMapping("listeClient")
	public String listeClient(Model m)
	{
		m.addAttribute("listeC", icr.findAll());
		
		return "listeClient";
	}
	
	@GetMapping("modifierClient/{id}")
	public String modifierClient(Model m, @PathVariable("id") int id)
	{
		m.addAttribute("client", icr.getReferenceById(id));
		
		return "modifierClient";
	}
	
	@PostMapping("modifierClient/{id}")
	public String modifierClient(@ModelAttribute("client") Client c, @PathVariable("id") int id)
	{
		icr.save(c);
		
		return "redirect:/listeClient";
	}
	
	@GetMapping("deleteClient/{id}")
	public String deleteClient(@PathVariable("id") int id)
	{
		icr.delete(icr.getReferenceById(id));
		
		return "redirect:/listeClient";
	}

}
