package com.dnd5e.wiki.controller.rest.model.json.foundary.data;

import com.dnd5e.wiki.model.creature.Creature;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FResources {
	public FValueMax legact;
	public FValueMax legres;
	public FLair lair;
	public FResources(Creature creature) {
		legact = new FValueMax();
		legres = new FValueMax();
		if (creature.getLair() != null) {
			lair = new FLair(true, 20);
		}
		else
		{
			lair = new FLair(false, 0);
		}
	}
}