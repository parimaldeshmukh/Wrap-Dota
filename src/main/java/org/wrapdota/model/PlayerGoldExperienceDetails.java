package org.wrapdota.model;

class PlayerGoldExperienceDetails {
    private final Integer gold;
    private final Integer goldPerMinute;
    private final Integer goldSpent;
    private final Integer level;
    private final Integer experiencePerMinute;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerGoldExperienceDetails)) return false;

        PlayerGoldExperienceDetails that = (PlayerGoldExperienceDetails) o;

        if (!experiencePerMinute.equals(that.experiencePerMinute)) return false;
        if (!gold.equals(that.gold)) return false;
        if (!goldPerMinute.equals(that.goldPerMinute)) return false;
        if (!goldSpent.equals(that.goldSpent)) return false;
        if (!level.equals(that.level)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gold.hashCode();
        result = 31 * result + goldPerMinute.hashCode();
        result = 31 * result + goldSpent.hashCode();
        result = 31 * result + level.hashCode();
        result = 31 * result + experiencePerMinute.hashCode();
        return result;
    }

    public PlayerGoldExperienceDetails(Integer gold, Integer goldPerMinute, Integer goldSpent, Integer level, Integer experiencePerMinute) {
        this.gold = gold;
        this.goldPerMinute = goldPerMinute;
        this.goldSpent = goldSpent;
        this.level = level;
        this.experiencePerMinute = experiencePerMinute;
    }
}
