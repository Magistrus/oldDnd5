package com.dnd5e.wiki.controller.rest.model.json.foundary.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FMaterials {
    public String value;
    public boolean consumed;
    public int cost;
    public int supply;
}