package com.dnd5e.wiki.controller.rest.model.json.foundary.data;

import com.dnd5e.wiki.controller.rest.model.json.foundary.FValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FValueMax extends FValue {
    public int max;
}