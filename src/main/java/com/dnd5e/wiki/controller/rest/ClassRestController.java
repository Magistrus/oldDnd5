package com.dnd5e.wiki.controller.rest;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dnd5e.wiki.dto.ClassDto;
import com.dnd5e.wiki.dto.user.Setting;
import com.dnd5e.wiki.model.TypeBook;
import com.dnd5e.wiki.model.hero.classes.HeroClass;
import com.dnd5e.wiki.repository.datatable.ClassDatatableRepository;
import com.dnd5e.wiki.util.SourceUtil;

@RestController
public class ClassRestController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ClassDatatableRepository repo;

	@GetMapping("/data/classes")
	public DataTablesOutput<ClassDto> getData(@Valid DataTablesInput input, @RequestParam Map<String, String> queryParameters) {
		Setting settings = (Setting) session.getAttribute(SettingRestController.SETTINGS);
		input.parseSearchPanesFromQueryParams(queryParameters, Arrays.asList("diceHp"));

		Set<TypeBook> sources = SourceUtil.getSources(settings);
		Specification<HeroClass> specification = bySources(sources);
		return repo.findAll(input, specification, specification, ClassDto::new);
	}

	
	private <T> Specification<T> bySources(Set<TypeBook> types) {
		return (root, query, cb) -> root.get("book").get("type").in(types);
	}
}