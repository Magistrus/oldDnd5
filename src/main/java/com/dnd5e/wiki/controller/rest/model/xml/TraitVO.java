package com.dnd5e.wiki.controller.rest.model.xml;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import org.thymeleaf.util.StringUtils;

import com.dnd5e.wiki.model.hero.race.Feature;

import lombok.Getter;

@Getter
public class TraitVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private String name;

	@XmlElement
	private List<String> text;

	@XmlElement(required = false)
	private Integer charges;

	@XmlElement(required = false)
	private Integer recharge;
	
	@XmlElement(name = "modifier", required = false)
	private List<ModifierVO> modifiers;
	
	public TraitVO() {
	}

	public TraitVO(String name, String text) {
		this.name = StringUtils.capitalize(name.toLowerCase().trim());
		this.text = Arrays.stream(text.split("<p>"))
				.filter(Objects::nonNull)
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.map(Compendium::removeHtml)
				.map(String::trim)
				.collect(Collectors.toList());
		addRecharge();
	}

	public TraitVO(Feature feature) {
		name = feature.getName().trim();
		addRecharge();
		text = Arrays.stream(feature.getDescription().split("<p>"))
				.filter(Objects::nonNull)
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.map(Compendium::removeHtml)
				.map(String::trim)
				.collect(Collectors.toList());
	}

	private void addRecharge() {
		if (name.contains("2-6")) {
			recharge = 2;
		} else if (name.contains("3-6")) {
			recharge = 3;
		} else if (name.contains("4-6")) {
			recharge = 4;
		} else if (name.contains("5-6")) {
			recharge = 5;
		} else if (name.contains("6") && !name.toLowerCase().contains("/день")) {
			recharge = 6;
		}
		if (name.toLowerCase().contains("/день")) {
			recharge = 1;
			if (name.contains("1")) {
				charges = 1;
			} else if (name.contains("2")) {
				charges = 2;
			} else if (name.contains("3")) {
				charges = 3;
			} else if (name.contains("4")) {
				charges = 4;
			} else if (name.contains("5")) {
				charges = 5;
			}
			if (name.contains("перезарядка после короткого")) {
				recharge = 7;
			}
		}
	}
}