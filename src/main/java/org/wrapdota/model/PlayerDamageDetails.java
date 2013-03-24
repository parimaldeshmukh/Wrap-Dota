package org.wrapdota.model;

public class PlayerDamageDetails {
    private final Integer heroDamage;
    private final Integer towerDamage;
    private final Integer heroHealing;

    public PlayerDamageDetails(Integer heroDamage, Integer towerDamage, Integer heroHealing) {
        this.heroDamage = heroDamage;
        this.towerDamage = towerDamage;
        this.heroHealing = heroHealing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerDamageDetails)) return false;

        PlayerDamageDetails that = (PlayerDamageDetails) o;

        if (!heroDamage.equals(that.heroDamage)) return false;
        if (!heroHealing.equals(that.heroHealing)) return false;
        if (!towerDamage.equals(that.towerDamage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = heroDamage.hashCode();
        result = 31 * result + towerDamage.hashCode();
        result = 31 * result + heroHealing.hashCode();
        return result;
    }
}
