package com.dnd5e.wiki.controller.rest.model.json.foundary.data;

import com.dnd5e.wiki.controller.rest.model.json.foundary.FAC;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FHP;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FInit;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FMovement;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FSenses;
import com.dnd5e.wiki.model.creature.ArmorType;
import com.dnd5e.wiki.model.creature.Creature;
import com.dnd5e.wiki.model.creature.Spellcater;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FAttributes {
	private FAC ac;
	private FHP hp;
	private FInit init;
	private FMovement movement;
	public FSenses senses;
	public String spellcasting = "";
	private byte prof;
	private byte spelldc = 10;
	private byte spellLevel;

	public FAttributes(Creature creature) {
		if (creature.getArmorTypes().contains(ArmorType.NATURAL)) {
			ac = new FAC(creature.getAC(), "natural", "", (byte) 0);
		} else {
			ac = new FAC(creature.getAC(), "default", "", (byte) 0);
		}
		hp = new FHP(creature);
		init = new FInit();
		movement = new FMovement(creature);
		senses = new FSenses(creature);
		if (!creature.getSpellcasters().isEmpty()) {
			for (Spellcater spellcaster : creature.getSpellcasters()) {
				if (spellcaster.getLevel() > 0) {
					spellcasting = spellcaster.getSpellAbility().name().toLowerCase().substring(0, 3);
					spelldc = spellcaster.getDc();
				}
			}
		}
	}
}