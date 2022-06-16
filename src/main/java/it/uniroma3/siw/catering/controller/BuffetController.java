package it.uniroma3.siw.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.controller.validator.BuffetValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService bfs;

	@Autowired
	private BuffetValidator bfv;

	@Autowired
	private ChefService cs;

	@GetMapping("/elencoBuffet")
	public String getBuffetList(Model model) {
		List<Buffet> list = this.bfs.findAll();
		model.addAttribute("buffetList", list);
		return "buffet/allBuffet.html";
	}

	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.bfs.findById(id).get();
		model.addAttribute("buffet", buffet);
		return "buffet/buffet.html";
	}
	
	//Modifica buffet di uno chef
	@GetMapping("/modificaBuffet/{idBuffet}")
	public String editBuffet(@PathVariable("idBuffet") Long idBuffet, Model model) {
		Buffet buffet = this.bfs.findById(idBuffet).get();
		model.addAttribute("buffet", buffet);
		return "buffet/modificaBuffet.html";
	}

	//Form modifica buffet di uno chef
	@PostMapping("/modificaBuffet/{idBuffet}")
	public String editBuffet(@PathVariable("idBuffet") Long idBuffet, @Valid @ModelAttribute Buffet newBuffet,
			BindingResult buffetBindingResult, Model model) {
		Buffet buffet = this.bfs.findById(idBuffet).get();
		this.bfv.validate(newBuffet, buffetBindingResult);
		if (!buffetBindingResult.hasErrors()) {
			this.bfs.update(buffet, newBuffet);
			List<Buffet> buffetList = this.bfs.findAll();
			model.addAttribute("chef", buffet.getChef());
			model.addAttribute("buffetList", buffetList);
			return "chef/chef.html";
		}
		return "buffet/modificaBuffet.html";
	}

	//Aggiungi buffet di uno chef
	@GetMapping("/chef/{id}/aggiungiBuffet")
	public String newBuffet(@PathVariable("id") Long id, Model model) {
		Chef chef = this.cs.findById(id).get();
		model.addAttribute("chef", chef);
		model.addAttribute("buffet", new Buffet());
		return "buffet/newBuffet.html";
	}

	//Form per aggiungere un buffet di uno chef
	@PostMapping("/chef/{id}/aggiungiBuffet")
	public String newBuffet(@Valid @ModelAttribute Buffet buffet, BindingResult buffetBindingResult,
			@PathVariable("id") Long id, Model model) {

		Chef chef = cs.findById(id).get();
		bfv.validate(buffet, buffetBindingResult);
		if (!buffetBindingResult.hasErrors()) {
			buffet.setChef(chef);
			chef.getBuffet().add(buffet);
			cs.save(chef);
			model.addAttribute("chef", chef);
			model.addAttribute("buffetList", chef.getBuffet());
			return "chef/chef.html";
		}
		return "buffet/newBuffet.html";
	}

	//Elimina un buffet di uno chef
	@GetMapping("/eliminaBuffet/{idBuffet}")
	public String deleteBuffet(@PathVariable("idBuffet") Long idBuffet, Model model) {
		Buffet buffet = this.bfs.findById(idBuffet).get();
		this.bfs.delete(buffet);
		model.addAttribute("chef", buffet.getChef());
		model.addAttribute("buffetList", buffet.getChef().getBuffet());
		return "chef/chef.html";
	}
}