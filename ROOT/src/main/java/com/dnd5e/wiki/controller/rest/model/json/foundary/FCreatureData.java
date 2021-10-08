package com.dnd5e.wiki.controller.rest.model.json.foundary;

import com.dnd5e.wiki.controller.rest.model.json.foundary.data.FAbilities;
import com.dnd5e.wiki.controller.rest.model.json.foundary.data.FAttributes;
import com.dnd5e.wiki.controller.rest.model.json.foundary.data.FBonuses;
import com.dnd5e.wiki.controller.rest.model.json.foundary.data.FCurrency;
import com.dnd5e.wiki.controller.rest.model.json.foundary.data.FResources;
import com.dnd5e.wiki.controller.rest.model.json.foundary.data.details.FDetails;
import com.dnd5e.wiki.controller.rest.model.json.foundary.spell.FSpells;
import com.dnd5e.wiki.model.creature.Creature;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCreatureData {
    public FAbilities abilities;
    public FAttributes attributes;
    public FDetails details;
    public FTraits traits;
    public FCurrency currency;
    public FSkills skills;
    public FSpells spells;
    public FBonuses bonuses;
    public FResources resources;

	public FCreatureData(Creature creature) {
		abilities = new FAbilities(creature);
		attributes = new FAttributes(creature);
		details = new FDetails(creature);
		traits = new FTraits(creature);
		skills = new  FSkills(creature.getSkills());
		if (!creature.getSpellcasters().isEmpty())
		{
			spells = new FSpells(creature);
		} else {
			spells = new FSpells();
		}
		bonuses = new FBonuses();
		resources = new FResources(creature);
	}
}