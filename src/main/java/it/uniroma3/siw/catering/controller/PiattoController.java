package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.controller.validator.PiattoValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService pts;
	
	@Autowired
	private BuffetService bfs;
	
	@Autowired
	private PiattoValidator ptv;
	
	//aggiunge un nuovo piatto ad un buffet
	@GetMapping("/buffet/{id}/aggiungiPiatto")
	public String addPiatto(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.bfs.findById(id).get();
		model.addAttribute("buffet", buffet);
		model.addAttribute("piatto", new Piatto());
		return "piatto/newPiatto.html";
	}
	
	//Form aggiunge un nuovo piatto ad un buffet
	@PostMapping("/buffet/{id}/aggiungiPiatto")
	public String addPiattoForm(@PathVariable("id") Long id, @Valid @ModelAttribute Piatto piatto, BindingResult bindingResult, Model model) {
		Buffet buffet = this.bfs.findById(id).get();
		this.ptv.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			buffet.getPiatti().add(piatto);
			this.bfs.save(buffet);
			model.addAttribute("buffet", buffet);
			return "buffet/buffet.html";
		}
		return "piatto/newPiatto.html";
	}
	
	//elimina il piatto di un buffet
	@GetMapping("/buffet/{idBuffet}/eliminaPiatto/{idPiatto}")
	public String deletePiatto(@PathVariable("idBuffet") Long idBuffet, @PathVariable("idPiatto") Long idPiatto, Model model) {
		Buffet buffet = this.bfs.findById(idBuffet).get();
		Piatto piatto = this.pts.findById(idPiatto).get();
		this.pts.delete(piatto);
		model.addAttribute("buffet", buffet);
		return "buffet/buffet.html";
	}
	
	//Modifica di un piatto di un buffet
	@GetMapping("/buffet/{idBuffet}/modificaPiatto/{idPiatto}")
	public String modificaPiatto(@PathVariable("idBuffet") Long idBuffet, @PathVariable("idPiatto") Long idPiatto, Model model) {
		Buffet buffet = this.bfs.findById(idBuffet).get();
		Piatto piatto = this.pts.findById(idPiatto).get();
		model.addAttribute("buffet", buffet);
		model.addAttribute("piatto", piatto);
		return "piatto/modificaPiatto.html";
	}
	
	//Form modifica di un piatto di un buffet
	@PostMapping("/buffet/{idBuffet}/modificaPiatto/{idPiatto}")
	public String modificaPiattoForm(@PathVariable("idBuffet") Long idBuffet, @PathVariable("idPiatto") Long idPiatto, 
									@Valid @ModelAttribute Piatto newPiatto, BindingResult bindingResult, Model model) {
		Buffet buffet = this.bfs.findById(idBuffet).get();
		Piatto piatto = this.pts.findById(idPiatto).get();
		this.ptv.validate(newPiatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.pts.update(piatto, newPiatto);
			model.addAttribute("buffet", buffet);
			return "buffet/buffet.html";
		}
		return "piatto/modificaPiatto.html";
	}
	
	//visualizza un piatto di uno specifico buffet
	@GetMapping("/piatto/{idPiatto}")
	public String getPiatto(@PathVariable("idPiatto") Long idPiatto, Model model) {
		Piatto piatto = this.pts.findById(idPiatto).get();		
		model.addAttribute("piatto", piatto);		
		return "piatto/piatto.html";
	}
	
	//visualizza l'elenco dei piatto di un buffet
	@GetMapping("/buffet/{id}/elencoPiatti")
	public String getPiatti(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.bfs.findById(id).get();
		model.addAttribute("buffet", buffet);
		model.addAttribute("piatti", buffet.getPiatti());
		return "buffet/buffet.html";
	}
}