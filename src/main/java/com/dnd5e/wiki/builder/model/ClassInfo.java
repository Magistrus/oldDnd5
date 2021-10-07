package com.dnd5e.wiki.builder.model;

import java.io.Serializable;

import com.dnd5e.wiki.model.creature.SkillType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Integer level;
	
	private SkillType skill;
	private int skillIndex;
}