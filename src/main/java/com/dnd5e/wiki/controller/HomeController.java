package com.dnd5e.wiki.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnd5e.wiki.model.places.Place;
import com.dnd5e.wiki.repository.BookRepository;
import com.dnd5e.wiki.repository.PlaceRepository;
import com.dnd5e.wiki.repository.ReferenceRepository;

@Controller
public class HomeController {
	@Autowired
	private PlaceRepository placeRepo;
	@Autowired
	private ReferenceRepository referenceRepo;

	@Autowired
	private BookRepository bookRepo;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getHome(Model model, Device device) {
		model.addAttribute("device", device);
		return "home";
	}

	@GetMapping("/place/{id}")
	@Transactional
	public String getPlace(Model model, @PathVariable Integer id) {
		Place place = placeRepo.findById(id).get();
		model.addAttribute("place", placeRepo.findById(id).get());
		model.addAttribute("children", place.getChildren());
		return "place";
	}

	@GetMapping("/references")
	public String getReferences(Model model, Device device) {
		model.addAttribute("device", device);

		model.addAttribute("references", referenceRepo.findAll());
		return "references";
	}
	@GetMapping("/sources")
	public String getSources(Model model, Device device) {
		model.addAttribute("device", device);

		model.addAttribute("sources", bookRepo.findAll());
		return "sources";
	}

	@GetMapping("/403")
	public String getAccessError() {
		return "403";
	}
	@GetMapping("/.well-known/pki-validation/A704B7CF7FF9E7E346D9B50FD97D4328.txt")
	@ResponseBody
	public String getSetrifiacateValidation() {
		return "c0ee5c1d37335c7a3678658c1ff161b216a160b12f99e2c145a2c560e84f2363\n" + 
				"comodoca.com\n" + 
				"S77OLq8QpcIjOfpErg4l";
	}
}