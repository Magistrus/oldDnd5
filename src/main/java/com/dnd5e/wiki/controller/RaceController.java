package com.dnd5e.wiki.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dnd5e.wiki.controller.rest.SettingRestController;
import com.dnd5e.wiki.dto.user.Setting;
import com.dnd5e.wiki.model.ImageType;
import com.dnd5e.wiki.model.TypeBook;
import com.dnd5e.wiki.model.hero.race.Feature;
import com.dnd5e.wiki.model.hero.race.Race;
import com.dnd5e.wiki.repository.ImageRepository;
import com.dnd5e.wiki.repository.RaceRepository;
import com.dnd5e.wiki.util.SourceUtil;

@Controller
@RequestMapping("/races")
public class RaceController {
	@Autowired
	private RaceRepository repo;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ImageRepository imageRepo;
	
	@GetMapping
	public String getRaces(Model model, Device device) {
		model.addAttribute("device", device);
		List<Race> races = repo.findByParentIsNull(Sort.by(Sort.Direction.ASC, "name"));
		Setting settings = (Setting) session.getAttribute(SettingRestController.SETTINGS);
		Set<TypeBook> sources = SourceUtil.getSources(settings);
		if (device.isMobile()) {
			races = races.stream()
					.filter(r -> sources.contains(r.getBook().getType()))
					.collect(Collectors.toList());
			model.addAttribute("races", races);
			return "hero/races";
		}
		model.addAttribute("order", "[[ 1, 'asc' ]]");
		return "hero/races2";
	}
	
	@GetMapping("/tiles")
	public String getOldViewRaces(Model model, Device device) {
		model.addAttribute("device", device);
		List<Race> races = repo.findByParentIsNull(Sort.by(Sort.Direction.ASC, "name"));
		Setting settings = (Setting) session.getAttribute(SettingRestController.SETTINGS);
		Set<TypeBook> sources = SourceUtil.getSources(settings);
		races = races.stream()
					.filter(r -> sources.contains(r.getBook().getType()))
					.collect(Collectors.toList());
		model.addAttribute("races", races);
		return "hero/races";
	}
	
	@GetMapping("/{name}")
	public String getRaceNew(Model model, @PathVariable String name, Device device) {
		Race race = repo.findOneByEnglishName(name.replace("_", " ")).orElseThrow(NoSuchElementException::new);
		model.addAttribute("race", race);
		Setting settings = (Setting) session.getAttribute(SettingRestController.SETTINGS);
		Set<TypeBook> sources = SourceUtil.getSources(settings);
		List<Feature> features = new ArrayList<>();
		List<Feature> parentFeatures = new ArrayList<>();
		if (race.getParent() != null) {
			model.addAttribute("subRaces", race.getParent().getSubRaces().stream()
					.filter(r -> sources.contains(r.getBook().getType()))
					.collect(Collectors.toList()));
			features.addAll(race.getFeatures());
			Set<Integer> exludedFeatureIds = race.getFeatures().stream()
					.map(Feature::getReplaceFeatureId)
					.filter(Objects::nonNull)
					.collect(Collectors.toSet());
			parentFeatures.addAll(race.getParent().getFeatures().stream()
					.filter(f -> !exludedFeatureIds.contains(f.getId()))
					.collect(Collectors.toList()));
		}
		else {
			model.addAttribute("subRaces", race.getSubRaces().stream()
					.filter(r -> sources.contains(r.getBook().getType()))
					.collect(Collectors.toList()));
			features.addAll(race.getFeatures());
		}
		model.addAttribute("features", features);
		model.addAttribute("parentFeatures", parentFeatures);
		model.addAttribute("device", device);
		model.addAttribute("urls", imageRepo.findAllByTypeAndRefId(ImageType.RACE, race.getId()));
		return "hero/raceView";
	}
	
	@GetMapping("/race/{id}")
	public String getRace(Model model, @PathVariable Integer id, Device device) {
		Race race = repo.findById(id).orElseThrow(NoSuchElementException::new);
		model.addAttribute("race", race);
		Setting settings = (Setting) session.getAttribute(SettingRestController.SETTINGS);
		Set<TypeBook> sources = SourceUtil.getSources(settings);
		List<Feature> features = new ArrayList<>();
		List<Feature> parentFeatures = new ArrayList<>();
		if (race.getParent() != null) {
			model.addAttribute("subRaces", race.getParent().getSubRaces().stream()
					.filter(r -> sources.contains(r.getBook().getType()))
					.collect(Collectors.toList()));
			features.addAll(race.getFeatures());
			Set<Integer> exludedFeatureIds = race.getFeatures().stream()
					.map(Feature::getReplaceFeatureId)
					.filter(Objects::nonNull)
					.collect(Collectors.toSet());
			parentFeatures.addAll(race.getParent().getFeatures().stream()
					.filter(f -> !exludedFeatureIds.contains(f.getId()))
					.collect(Collectors.toList()));
		}
		else {
			model.addAttribute("subRaces", race.getSubRaces().stream()
					.filter(r -> sources.contains(r.getBook().getType()))
					.collect(Collectors.toList()));
			features.addAll(race.getFeatures());
		}
		model.addAttribute("features", features);
		model.addAttribute("parentFeatures", parentFeatures);
		model.addAttribute("device", device);
		model.addAttribute("urls", imageRepo.findAllByTypeAndRefId(ImageType.RACE, id));
		return "hero/raceView";
	}

	@GetMapping("/fragment/{id}")
	public String getFragmentRace(Model model, @PathVariable Integer id, Device device) {
		Race race = repo.findById(id).orElseThrow(NoSuchElementException::new);
		model.addAttribute("race", race);
		Setting settings = (Setting) session.getAttribute(SettingRestController.SETTINGS);
		Set<TypeBook> sources = SourceUtil.getSources(settings);
		List<Feature> features = new ArrayList<>();
		List<Feature> parentFeatures = new ArrayList<>();
		if (race.getParent() != null) {
			model.addAttribute("subRaces", race.getParent().getSubRaces().stream()
					.filter(r -> sources.contains(r.getBook().getType()))
					.collect(Collectors.toList()));
			features.addAll(race.getFeatures());
			Set<Integer> exludedFeatureIds = race.getFeatures().stream()
					.map(Feature::getReplaceFeatureId)
					.filter(Objects::nonNull)
					.collect(Collectors.toSet());
			parentFeatures.addAll(race.getParent().getFeatures().stream()
					.filter(f -> !exludedFeatureIds.contains(f.getId()))
					.collect(Collectors.toList()));
		}
		else {
			model.addAttribute("subRaces", race.getSubRaces().stream()
					.filter(r -> sources.contains(r.getBook().getType()))
					.collect(Collectors.toList()));
			features.addAll(race.getFeatures());
		}
		model.addAttribute("features", features);
		model.addAttribute("parentFeatures", parentFeatures);
		model.addAttribute("device", device);
		model.addAttribute("urls", imageRepo.findAllByTypeAndRefId(ImageType.RACE, id));
		return "hero/raceView :: race";		
	}
}