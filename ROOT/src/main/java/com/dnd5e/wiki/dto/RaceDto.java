package com.dnd5e.wiki.dto;

import java.io.Serializable;

import com.dnd5e.wiki.model.hero.race.Race;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RaceDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String englishName;
	private String speed;
	private String size;
	public String abilityBonuses;
	public boolean hasSubRaces;
	private String book;

	public RaceDto(Race race) {
		this.id = race.getId();
		this.name = race.getCapName();
		this.englishName = race.getEnglishName();
		this.speed = race.getSpeed() + " фт.";
		if (race.getFly() != null) {
			this.speed += String.format(", летая %d фт.", race.getFly());
		}
		if (race.getClimb() != null) {
			this.speed += String.format(", лазая %d фт.", race.getClimb());
		}
		if (race.getSwim() != null) {
			this.speed += String.format(", плавая %d фт.", race.getSwim());
		}
		this.size = race.getSize().getCyrilicName();
		this.abilityBonuses= race.getAbilityBonuses();
		this.hasSubRaces = !race.getSubRaces().isEmpty();
		this.book = race.getBook().getName();
	}
}