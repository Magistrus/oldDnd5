package com.dnd5e.wiki.controller.rest.model.json.foundary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FAbility {
    public byte value;
    public byte proficient;
    public byte min = 3;
    public byte mod;
    public byte save;
    public byte prof;
    public byte saveBonus;
    public byte checkBonus;
    public byte dc;
}