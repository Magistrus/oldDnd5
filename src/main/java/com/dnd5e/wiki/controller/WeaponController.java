package com.dnd5e.wiki.controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dnd5e.wiki.controller.rest.SettingRestController;
import com.dnd5e.wiki.dto.user.Setting;
import com.dnd5e.wiki.model.TypeBook;
import com.dnd5e.wiki.model.creature.DamageType;
import com.dnd5e.wiki.model.stock.Currency;
import com.dnd5e.wiki.model.stock.Weapon;
import com.dnd5e.wiki.model.stock.WeaponProperty;
import com.dnd5e.wiki.model.stock.WeaponType;
import com.dnd5e.wiki.repository.WeaponPropertyRepository;
import com.dnd5e.wiki.repository.WeaponRepository;
import com.dnd5e.wiki.util.SourceUtil;

@Controller
@RequestMapping("/stock/weapons")
public class WeaponController {

	@Autowired
	private WeaponRepository repo;

	@Autowired
	private WeaponPropertyRepository propertyRepo;
	
	@Autowired
	private HttpSession session;

	@GetMapping
	public String getWeapons(Model model, Device device) {
		model.addAttribute("device", device);
		List<Weapon> weapons = repo.findAll();
		Setting settings = (Setting) session.getAttribute(SettingRestController.SETTINGS);
		Set<TypeBook> sources = SourceUtil.getSources(settings);

		boolean official = settings == null || !settings.isHomeRule();
		Predicate<Weapon> predicate = w -> w.getBook().getType() != TypeBook.CUSTOM;
		if (official) {
			predicate = w -> w.getBook().getType() == TypeBook.OFFICAL;
		}
		else
		{
			predicate = w -> true;
		}
		
		Map<WeaponType, List<Weapon>> typeToWeapons = weapons.stream()
				.filter(w -> sources.contains(w.getBook().getType()))
				.collect(Collectors.groupingBy(Weapon::getType));
		model.addAttribute("weapons", typeToWeapons);
		Map<WeaponProperty, List<Weapon>> propertyByWeapons = weapons.stream().filter(predicate)
				.flatMap(weapon -> weapon.getProperties().stream().map(property -> new SimpleEntry<>(property, weapon)))
                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())));
		
		Map<DamageType, List<Weapon>> damageTypeByWeapons = new EnumMap<>(DamageType.class); 
		weapons.forEach(w -> damageTypeByWeapons.computeIfAbsent(w.getDamageType(), a -> new ArrayList<>()).add(w));
		
		model.addAttribute("properties", propertyByWeapons);
		List<WeaponType> types = new ArrayList<>(Arrays.asList(WeaponType.values()));
		types.retainAll(typeToWeapons.keySet());
		model.addAttribute("types", types);
		model.addAttribute("damageTypes", damageTypeByWeapons);
		model.addAttribute("currencies", Currency.values());
		return "equipment/weapons";
	}

	@GetMapping("/property/{id}")
	public String getPropertyForm(Model model,  @PathVariable Integer id) {
		model.addAttribute("property", propertyRepo.findById(id).orElseGet(WeaponProperty::new));
		return "equipment/weaponPropertyView";
	}
}