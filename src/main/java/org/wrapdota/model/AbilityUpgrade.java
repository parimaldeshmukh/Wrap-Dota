package org.wrapdota.model;


public class AbilityUpgrade {
    Integer abilityId;
    Long atTime;
    Integer atLevel;

    public AbilityUpgrade(Integer abilityId, Long atTime, Integer atLevel) {
        this.abilityId = abilityId;
        this.atTime = atTime;

        this.atLevel = atLevel;
    }
}
