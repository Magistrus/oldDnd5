package com.dnd5e.wiki.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dnd5e.wiki.model.ImageType;
import com.dnd5e.wiki.model.creature.Action;
import com.dnd5e.wiki.model.creature.Creature;
import com.dnd5e.wiki.model.creature.CreatureRace;
import com.dnd5e.wiki.model.hero.Condition;
import com.dnd5e.wiki.repository.ConditionRepository;
import com.dnd5e.wiki.repository.CreatureRaceRepository;
import com.dnd5e.wiki.repository.CreatureRepository;
import com.dnd5e.wiki.repository.ImageRepository;

@Controller
@RequestMapping({ "/creatures" })
public class CreatureController {
	@Autowired
	private CreatureRepository creatureRepo;

	@Autowired
	private CreatureRaceRepository creatureRaceRepo;

	@Autowired
	private ConditionRepository conditionRepo;
	
	@Autowired
	private ImageRepository imageRepo;
	
	@GetMapping
	public String getCreatures(Model model, Device device) {
		model.addAttribute("device", device);
		model.addAttribute("creatureRaces", creatureRaceRepo.findAll(Sort.by("name")));
		if (device.isMobile())
		{
			return "datatable/creatures";
		}
		return "datatable/creatures2";
	}

	@GetMapping("/id/{id:\\d+}")
	public String getCreatureView(Model model, Device device, @PathVariable Integer id) {
		model.addAttribute("device", device);
		Creature creature = creatureRepo.findById(id).orElseThrow(NoSuchElementException::new);
		model.addAttribute("creature", creature);
		List<Action> actions = creature.getActions();
		model.addAttribute("actions", actions);
		List<Action> reactions = creature.getReactions();
		model.addAttribute("reactions", reactions);
		List<Action> legendary = creature.getLegendaries();
		model.addAttribute("legendary", legendary);
		Map<Integer, Condition> conditions = conditionRepo.findAll().stream().collect(Collectors.toMap(Condition::getId, Function.identity(), (o1, o2) -> o1));
		model.addAttribute("conditions", conditions);
		return "creatureView";
	}

	@GetMapping("/{name}")
	public String getCreatureNameView(Model model, Device device, @PathVariable String name) {
		model.addAttribute("device", device);
		Creature creature = creatureRepo.findOneByEnglishName(name.replace("_", " ")).orElseThrow(NoSuchElementException::new);
		model.addAttribute("creature", creature);
		List<Action> actions = creature.getActions();
		model.addAttribute("actions", actions);
		List<Action> reactions = creature.getReactions();
		model.addAttribute("reactions", reactions);
		List<Action> legendary = creature.getLegendaries();
		model.addAttribute("legendary", legendary);

		Map<Integer, Condition> conditions = conditionRepo.findAll().stream()
				.collect(Collectors.toMap(Condition::getId, Function.identity(), (o1, o2) -> o1));
		model.addAttribute("conditions", conditions);
		model.addAttribute("urls", imageRepo.findAllByTypeAndRefId(ImageType.CREATURE, creature.getId()));
		return "creatureView";
	}

	@GetMapping("/statblock/{id}")
	public String getCreatureStatblockView(Model model, Device device, @PathVariable Integer id) {
		model.addAttribute("device", device);
		Creature creature = creatureRepo.findById(id).orElseThrow(NoSuchElementException::new);
		model.addAttribute("creature", creature);
		List<Action> actions = creature.getActions();
		model.addAttribute("actions", actions);
		List<Action> reactions = creature.getReactions();
		model.addAttribute("reactions", reactions);
		List<Action> legendary = creature.getLegendaries();
		model.addAttribute("legendary", legendary);
		model.addAttribute("firstElement", new FirstElement());

		Map<Integer, Condition> conditions = conditionRepo.findAll().stream().collect(Collectors.toMap(Condition::getId, Function.identity(), (o1, o2) -> o1));
		model.addAttribute("conditions", conditions);
		return "creatureStatblockView";
	}

	@GetMapping("/tag/{id}")
	public String getCreatureRace(Model model, @PathVariable Integer id) {
		CreatureRace race = creatureRaceRepo.getOne(id);
		model.addAttribute("creatureRaces", creatureRaceRepo.findAll(Sort.by("name")));
		model.addAttribute("race", race);
		model.addAttribute("creatures", creatureRepo.findAllByRaceIdOrderByExpAsc(id));
		return "creatureTagView";
	}

	public static class FirstElement{
		private boolean first = true;
		public boolean isFirst() {
			boolean first = this.first;
			this.first = false;
			return first;
		}
	}
}