package com.dnd5e.wiki.controller.rest.model.xml;

import javax.xml.bind.annotation.XmlElement;

import com.dnd5e.wiki.model.hero.classes.Tracker;

public class TrackerVO {
	@XmlElement
	String name;
	@XmlElement
	String value;
	@XmlElement
	String reset;
	TrackerVO(Tracker tracker) {
		this.name = tracker.getName();
		this.value = tracker.getValue();
		this.reset = "" + tracker.getRest().name().charAt(0);
	}
}