package com.dnd5e.wiki.controller.rest.model.json.foundary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FAC {
	private byte flat;
	private String calc = "default";
	private String formula;
	private byte min;
}