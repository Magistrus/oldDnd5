package com.dnd5e.wiki.dto;

import java.util.stream.Collectors;

import org.thymeleaf.util.StringUtils;

import com.dnd5e.wiki.model.creature.SkillType;
import com.dnd5e.wiki.model.hero.Background;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BackgroundDto {
	private Integer id;
	private String name;
	private String englishName;
	private String skillName;
	private String skills;
	private int startMoney;
	private String book;
	
	public BackgroundDto(Background background) {
		id = background.getId();
		name = StringUtils.capitalizeWords(background.getName().toLowerCase())
				.replace(" И ", " и ").replace(" Или ", " или ").replace(" За ", " за ").replace(" С ", " с ").replace(" На ", " на ").replace(" От ", " от ").replace(" По ", " по ")
				.replace(" Над ", " над ").replace(" В ", " в ");
		englishName = background.getEnglishName();
		skillName = background.getSkillName();
		skills = background.getSkills().stream().map(SkillType::getCyrilicName).collect(Collectors.joining(", "));
		if (background.getOtherSkills() != null) {
			skills+=", " + background.getOtherSkills();
		}
		background.getEquipments();
		startMoney = background.getStartMoney();
		book = background.getBook().getName();
	}
}