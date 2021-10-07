package com.dnd5e.wiki.dto;

import java.util.stream.Collectors;

import com.dnd5e.wiki.model.creature.Creature;
import com.dnd5e.wiki.model.creature.HabitatType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatureDto {
	private int id; 
	private String name;
	private String englishName;
	private String altName;
	private String type;
	private String size;
	private String alignment;
	private String book;
	private String exp;
 	private String legendary;
	private String habitates;

	public CreatureDto(Creature creature) {
		id = creature.getId();
		exp = creature.getChallengeRating();
		name = creature.getName();
		altName = creature.getAltName();
		englishName = creature.getEnglishName();
		size = creature.getSize().getSizeName(creature.getType());
		type = creature.getType().getCyrilicName();
		alignment = creature.getAlignment().getCyrilicName();
		habitates = creature.getHabitates().stream().map(HabitatType::getName).collect(Collectors.joining(", "));
		book = creature.getBook().getName() + (creature.getPage() != null ? ", стр. " + creature.getPage() : "");
	}
}