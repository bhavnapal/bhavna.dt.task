package net.bp.collaboration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntryController {	
	@RequestMapping(value = {"/","/index"})
	public String index() {
		return "index";
	}
}
