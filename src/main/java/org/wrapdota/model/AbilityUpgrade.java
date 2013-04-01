package org.wrapdota.model;


class AbilityUpgrade {
    Integer abilityId;
    Long atTime;
    Integer atLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbilityUpgrade)) return false;

        AbilityUpgrade that = (AbilityUpgrade) o;

        if (!abilityId.equals(that.abilityId)) return false;
        if (!atLevel.equals(that.atLevel)) return false;
        if (!atTime.equals(that.atTime)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = abilityId.hashCode();
        result = 31 * result + atTime.hashCode();
        result = 31 * result + atLevel.hashCode();
        return result;
    }

    public AbilityUpgrade(Integer abilityId, Long atTime, Integer atLevel) {
        this.abilityId = abilityId;
        this.atTime = atTime;
        this.atLevel = atLevel;
    }
}
