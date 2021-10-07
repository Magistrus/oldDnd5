package com.dnd5e.wiki.controller.rest.model.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;

@Getter
public class ReactionVO implements Serializable{
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String name;
	@XmlElement
	private String text;
	
	public ReactionVO(String name, String text) {
		this.name = name;
		this.text = text;
	}
}