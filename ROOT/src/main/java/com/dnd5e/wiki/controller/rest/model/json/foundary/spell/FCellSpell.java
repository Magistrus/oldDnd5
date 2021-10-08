package com.dnd5e.wiki.controller.rest.model.json.foundary.spell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FCellSpell {
    public int value;
    public Object override;
    public int max;
}