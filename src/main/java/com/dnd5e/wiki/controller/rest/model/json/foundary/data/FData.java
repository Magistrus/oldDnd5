package com.dnd5e.wiki.controller.rest.model.json.foundary.data;

import java.util.List;

import com.dnd5e.wiki.controller.rest.model.json.foundary.FActivation;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FArmor;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FConsume;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FDamage;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FDuration;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FHP;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FRange;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FSave;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FTarget;
import com.dnd5e.wiki.controller.rest.model.json.foundary.FUses;
import com.dnd5e.wiki.model.creature.Creature;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FData {
    public FDescription description;
    public String source;
    public int quantity;
    public int weight;
    public int price;
    public int attunement;
    public boolean equipped;
    public String rarity;
    public boolean identified;
    public FActivation activation;
    public FDuration duration;
    public FTarget target;
    public FRange range;
    public FUses uses;
    public FConsume consume;
    public String ability;
    public String actionType;
    public Object attackBonus;
    public String chatFlavor;
    public Object critical;
    public FDamage damage;
    public String formula;
    public FSave save;
    public FArmor armor;
    public FHP hp;
    public String weaponType;
    public FProperties properties;
    public boolean proficient;
    public List<String> cptooltipmode;
    public String requirements;
    public FRecharge recharge;
    public int level;
    public String school;
    public FComponents components;
    public FMaterials materials;
    public FPreparation preparation;
    public FScaling scaling;
    public FSpellType spellType;
	
	public FData(Creature creature) {
		description = new FDescription();
	}	
}