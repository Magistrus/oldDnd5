package com.dnd5e.wiki.controller.rest.model.json.foundary;

import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.util.StringUtils;

import com.dnd5e.wiki.controller.rest.model.json.foundary.token.FToken;
import com.dnd5e.wiki.model.creature.ArmorType;
import com.dnd5e.wiki.model.creature.Creature;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCreature {
	private String name;
	private String type;
	private String img;
	private FCreatureData data;
	public FToken token;
	private List<FItem> items = new ArrayList<>();

	public FCreature(Creature creature) {
		name = creature.getName();
		type = "npc";
		data = new FCreatureData(creature);
		img = StringUtils.capitalizeWords(String.format("https://5e.tools/img/%s/%s.png",
				creature.getBook().getSource(), creature.getEnglishName()));
		token = new FToken(creature);
		creature.getActions().stream()
			.map(FItem::new)
			.forEach(i -> items.add(i));
		creature.getReactions().stream()
			.map(FItem::new)
			.forEach(i -> items.add(i));
		creature.getLegendaries().stream()
			.map(FItem::new)
			.forEach(i -> items.add(i));
		creature.getFeats().stream()
			.map(FItem::new)
			.forEach(i -> items.add(i));
		creature.getArmorTypes().stream()
			.filter(t -> t != ArmorType.NATURAL)
			.forEach(a -> items.add(new FItem(a)));
	}
}