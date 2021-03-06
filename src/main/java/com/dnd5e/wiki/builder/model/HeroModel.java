package com.dnd5e.wiki.builder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.dnd5e.wiki.model.AbilityType;
import com.dnd5e.wiki.model.creature.SkillType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private HeroInfo heroInfo;
	private RaceInfo raceInfo;
	private boolean selected = true;
	private Map<Integer, ClassInfo> classes = new LinkedHashMap<>();
	private AbilityInfo abilityInfo = new AbilityInfo();
	private Set<SkillType> trainedSkills = new LinkedHashSet<>();

	public List<SkillType> getSkills(List<SkillType> availableSkills, int index){
		availableSkills.removeAll(trainedSkills);
		availableSkills.add(isSkillTrained(index));
		return availableSkills;
	}

	public SkillType isSkillTrained(int index) {
		if (index >= trainedSkills.size()) {
			return null;
		}
		return new ArrayList<>(trainedSkills).get(index);
	}
	
	/**
	 * @param classInfo информация о классе
	 */
	public void addClass(ClassInfo classInfo) {
		classes.put(classInfo.getId(), classInfo);
	}

	public int getLevel() {
		return classes.values().stream()
				.collect(Collectors.summingInt(ClassInfo::getLevel));
	}

	public byte getStrength() {
		byte value = 0;
		if (raceInfo != null) {
			value += raceInfo.getStrength();
		}
		if (abilityInfo != null) {
			value += abilityInfo.getStrength();
		}
		return value;
	}

	public byte getDexterity() {
		byte value = 0;
		if (raceInfo != null) {
			value += raceInfo.getDexterity();
		}
		if (abilityInfo != null) {
			value += abilityInfo.getDexterity();
		}
		return value;
	}

	public byte getConstitution() {
		byte value = 0;
		if (raceInfo != null) {
			value += raceInfo.getConstitution();
		}
		if (abilityInfo != null) {
			value += abilityInfo.getConstitution();
		}
		return value;
	}

	public byte getIntellect() {
		byte value = 0;
		if (raceInfo != null) {
			value += raceInfo.getIntellect();
		}
		if (abilityInfo != null) {
			value += abilityInfo.getIntellect();
		}
		return value;
	}

	public byte getWizdom() {
		byte value = 0;
		if (raceInfo != null) {
			value += raceInfo.getWizdom();
		}
		if (abilityInfo != null) {
			value += abilityInfo.getWizdom();
		}
		return value;
	}

	public byte getCharisma() {
		byte value = 0;
		if (raceInfo != null) {
			value += raceInfo.getCharisma();
		}
		if (abilityInfo != null) {
			value += abilityInfo.getCharisma();
		}
		return value;
	}
	
	public String getStrengthMod() {
		return String.format("%+d", AbilityType.getModifier(getStrength()));
	}
	public String getDexterityMod() {
		return String.format("%+d", AbilityType.getModifier(getDexterity()));
	}
	public String getConstitutionMod() {
		return String.format("%+d", AbilityType.getModifier(getConstitution()));
	}
	public String getIntellectMod() {
		return String.format("%+d", AbilityType.getModifier(getIntellect()));
	}
	public String getWizdomMod() {
		return String.format("%+d", AbilityType.getModifier(getWizdom()));
	}
	public String getCharismaMod() {
		return String.format("%+d", AbilityType.getModifier(getCharisma()));
	}
}