package com.dnd5e.wiki.builder.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.dnd5e.wiki.builder.model.ClassInfo;
import com.dnd5e.wiki.builder.model.HeroModel;
import com.dnd5e.wiki.dto.ClassDto;
import com.dnd5e.wiki.repository.ClassRepository;

@Component
public class ClassBuilderHandler {
	@Autowired
	private ClassRepository classRepo;
	
	public void addClass(HeroModel heroModel, ClassInfo classInfo) {
		ClassInfo newClass = new ClassInfo();
		newClass.setLevel(1);
		newClass.setId(classInfo.getId());
		newClass.setName(classInfo.getName());
		heroModel.addClass(newClass);
		heroModel.setSelected(false);
	}

	public void removeClass(HeroModel heroModel, ClassInfo classInfo) {
		heroModel.getClasses().remove(classInfo.getId());
		if (heroModel.getClasses().isEmpty()) {
			heroModel.setSelected(true);
		}
	}

	public void changeClassLevel(HeroModel heroModel, ClassInfo classInfo) {
		ClassInfo changeClass = heroModel.getClasses().get(classInfo.getId());
		changeClass.setLevel(classInfo.getLevel());
	}
	
	public void addMulticlass(HeroModel heroModel, ClassInfo classInfo) {
		heroModel.setSelected(true);
	}

	public void cancelMulticlass(HeroModel heroModel, ClassInfo classInfo) {
		heroModel.setSelected(false);
	}	
	
	@Transactional
	public List<ClassDto> getClasses(){
		return classRepo.findAll(Sort.by("name")).stream().map(ClassDto::new).collect(Collectors.toList());
	}

	public void addSkill(HeroModel heroModel, ClassInfo classInfo) {
		heroModel.getTrainedSkills().remove(heroModel.isSkillTrained(classInfo.getSkillIndex()));
		heroModel.getTrainedSkills().add(classInfo.getSkill());
	}
	
	public String getClassOrder() {
		return "[[ 1, 'asc' ]]";
	}
}