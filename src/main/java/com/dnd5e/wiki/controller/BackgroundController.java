package com.dnd5e.wiki.controller;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnd5e.wiki.model.hero.Background;
import com.dnd5e.wiki.model.hero.Personalization;
import com.dnd5e.wiki.model.hero.PersonalizationFeature;
import com.dnd5e.wiki.model.hero.PersonalizationType;
import com.dnd5e.wiki.repository.BackgroundRepository;
import com.dnd5e.wiki.repository.PersonalizationFeatureRepository;

@Controller
@RequestMapping("/backgrounds")
public class BackgroundController {
	private static final Random rnd = new Random();
	
	@Autowired
	private BackgroundRepository repo;
	
	@Autowired
	private PersonalizationFeatureRepository repoFeature;
	
	@GetMapping
	public String getBackgrounds(Model model, Device device) {
		model.addAttribute("device", device);
		if (device.isMobile()) {
			return "datatable/backgrounds";	
		}
		return "datatable/backgrounds2";
	}
	
	@GetMapping("/{name}")
	public String getBackground(Model model, Device device, @PathVariable String name) {
		model.addAttribute("device", device);
		Background background = repo.findOne((root, query, cb) -> cb.equal(root.get("englishName"), name.replace("_", " ")))
				.orElseThrow(IllegalArgumentException::new);
		model.addAttribute("background", background);
		Map<PersonalizationType, List<Personalization>> map = background.getPersonalizations().stream()
				.collect(Collectors.groupingBy(Personalization::getType, () -> new EnumMap<>(PersonalizationType.class), Collectors.toList()));
		List<Personalization> personalizations = new ArrayList<>();

		for (PersonalizationType type:map.keySet()) {
			List<Personalization> list = map.get(type);
			personalizations.add(list.get(rnd.nextInt(list.size())));
		}
		model.addAttribute("personalizations", personalizations);
		model.addAttribute("tables", map);
		List<PersonalizationFeature> features = repoFeature.findAll();
		String feature = features.get(rnd.nextInt(features.size())).getText();
		model.addAttribute("feature", feature);
		return "hero/backgroundView";
	}

	@GetMapping("/fragment/{id}")
	public String getBackgroundFragment(Model model, Device device, @PathVariable Integer id) {
		model.addAttribute("device", device);
		Background background = repo.findById(id).orElseThrow(IllegalArgumentException::new);
		model.addAttribute("background", background);
		Map<PersonalizationType, List<Personalization>> map = background.getPersonalizations().stream()
				.collect(Collectors.groupingBy(Personalization::getType, () -> new EnumMap<>(PersonalizationType.class), Collectors.toList()));
		List<Personalization> personalizations = new ArrayList<>();

		for (PersonalizationType type:map.keySet()) {
			List<Personalization> list = map.get(type);
			personalizations.add(list.get(rnd.nextInt(list.size())));
		}
		model.addAttribute("personalizations", personalizations);
		model.addAttribute("tables", map);
		List<PersonalizationFeature> features = repoFeature.findAll();
		String feature = features.get(rnd.nextInt(features.size())).getText();
		model.addAttribute("feature", feature);
		return "hero/backgroundView :: background";
	}

	@GetMapping("/personalizare/feature")
	@ResponseBody
	public String getRandomPersonalizare(@RequestParam int id, @RequestParam PersonalizationType type) {
		List<Personalization> personalizations = repo.findAllByIdAndByType(id, type);
		return personalizations.get(rnd.nextInt(personalizations.size())).getText();
	}
}