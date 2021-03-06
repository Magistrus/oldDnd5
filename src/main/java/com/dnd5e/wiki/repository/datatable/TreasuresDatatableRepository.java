package com.dnd5e.wiki.repository.datatable;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dnd5e.wiki.model.spell.GroupByCount;
import com.dnd5e.wiki.model.treasure.Treasure;
import com.dnd5e.wiki.model.treasure.TreasureType;

@Repository
public interface TreasuresDatatableRepository extends DataTablesRepository<Treasure, Integer>{

	@Query("SELECT t.type AS field, COUNT(t.type) AS total FROM Treasure t GROUP BY t.type")
	List<GroupByCount<TreasureType>> countTotalType();
}
