package com.wkb.chat.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class IndexCtrl {
	

	
	@GetMapping(value="index")
	public String index(Model model) {
		return "index";
	}
	@GetMapping(value="video")
	public String video() {
		return "video";
	}
	@GetMapping(value="sound")
	public String sound() {
		return "sound";
	}

}
