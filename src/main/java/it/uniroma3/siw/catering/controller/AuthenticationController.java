package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.controller.validator.CredentialsValidator;
import it.uniroma3.siw.catering.controller.validator.UtenteValidator;
import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.model.Utente;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService creds;
	
	@Autowired
	private CredentialsValidator credv;
	
	@Autowired
	private UtenteValidator utv;
	
	@Autowired
	private ChefService chefs;
	
	@Autowired 
	private BuffetService bfs;
	
	//Login
	@GetMapping("/login")
    public String showLoginForm(Model model) {
        return "authentication/loginForm.html";
    }
	
	//Logout
	@GetMapping("/logout")
	public String logout(Model model) {
		return "index.html";
	}
	
	//Pagina index dopo login
	@GetMapping("/default")
	public String defaultAfterLogin(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = creds.getCredentials(userDetails.getUsername());
		
		//non implentata una pagina apposita per admin
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "index.html";
		}
		model.addAttribute("chefs", this.chefs.findAllChef());
		model.addAttribute("buffetList", this.bfs.findAll());
		return "index.html";
	}
	
	//Pagina index dopo oauth
	@GetMapping("/defaultOauth")
	public String oauthLogin(Model model) {
		OAuth2User userDetails = (OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "index.html";
	}
	
	//Registrazione utente
	@GetMapping("/register")
	public String getCredentials(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credentials());
		return "authentication/register.html";
	}
	
	//Form registrazione utente
	@PostMapping("/register")
	public String addCredentials(@Valid @ModelAttribute ("utente") Utente utente, BindingResult utenteBindingResult, @Valid @ModelAttribute("credenziali") Credentials credenziali, BindingResult credenzialiBindingResult, Model model) {
		
		utente.setCognome(utente.getCognome().toLowerCase());
		utente.setNome(utente.getNome().toLowerCase());
		this.utv.validate(utente, utenteBindingResult);
		this.credv.validate(credenziali, credenzialiBindingResult);
		if(!credenzialiBindingResult.hasErrors() && !utenteBindingResult.hasErrors()) {
			credenziali.setUtente(utente);  
			creds.save(credenziali);  // this also stores the User, thanks to Cascade.ALL policy
			return "index.html";
		}
		return "register.html";
	}
}
