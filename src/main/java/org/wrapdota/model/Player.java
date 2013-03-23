package org.wrapdota.model;

import java.util.List;

public class Player {

    private final Long accountId;
    private final Integer playerSlot;
    private final Integer heroId;

    public Player(Long accountId, Integer playerSlot, Integer heroId) {

        this.accountId = accountId;
        this.playerSlot = playerSlot;
        this.heroId = heroId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (!accountId.equals(player.accountId)) return false;
        if (!heroId.equals(player.heroId)) return false;
        if (!playerSlot.equals(player.playerSlot)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId.hashCode();
        result = 31 * result + playerSlot.hashCode();
        result = 31 * result + heroId.hashCode();
        return result;
    }

    public void addDetails(List<AbilityUpgrade> abilities, List<ItemAndSlot> itemAndSlots, Integer kills, Integer deaths, Integer assists, Boolean leaverStatus, Integer gold, Integer lastHits, Integer denies, Integer goldPerMinute, Integer experiencePerMinute, Integer goldSpent, Integer heroDamage, Integer towerDamage, Integer heroHealing, Integer level) {


    }
}
