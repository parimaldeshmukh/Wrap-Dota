package org.wrapdota.model;

import java.util.List;

public class Player {

    private final PlayerIdentificationDetails playerIdentificationDetails;

    private List<AbilityUpgrade> abilities;
    private List<ItemAndSlot> itemAndSlots;
    private PlayerGoldExperienceDetails playerGoldExperienceDetails;
    private PlayerKDADetails playerKDADetails;
    private Boolean leaverStatus;
    private PlayerDamageDetails playerDamageDetails;
    private PlayerLastHitDetails playerLastHitDetails;

    public Player(Long accountId, Integer playerSlot, Integer heroId) {
        this.playerIdentificationDetails = new PlayerIdentificationDetails(accountId,playerSlot,heroId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (abilities != null ? !abilities.equals(player.abilities) : player.abilities != null) return false;
        if (itemAndSlots != null ? !itemAndSlots.equals(player.itemAndSlots) : player.itemAndSlots != null)
            return false;
        if (leaverStatus != null ? !leaverStatus.equals(player.leaverStatus) : player.leaverStatus != null)
            return false;
        if (playerDamageDetails != null ? !playerDamageDetails.equals(player.playerDamageDetails) : player.playerDamageDetails != null)
            return false;
        if (playerGoldExperienceDetails != null ? !playerGoldExperienceDetails.equals(player.playerGoldExperienceDetails) : player.playerGoldExperienceDetails != null)
            return false;
        if (!playerIdentificationDetails.equals(player.playerIdentificationDetails)) return false;
        if (playerKDADetails != null ? !playerKDADetails.equals(player.playerKDADetails) : player.playerKDADetails != null)
            return false;
        if (playerLastHitDetails != null ? !playerLastHitDetails.equals(player.playerLastHitDetails) : player.playerLastHitDetails != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerIdentificationDetails.hashCode();
        result = 31 * result + (abilities != null ? abilities.hashCode() : 0);
        result = 31 * result + (itemAndSlots != null ? itemAndSlots.hashCode() : 0);
        result = 31 * result + (playerGoldExperienceDetails != null ? playerGoldExperienceDetails.hashCode() : 0);
        result = 31 * result + (playerKDADetails != null ? playerKDADetails.hashCode() : 0);
        result = 31 * result + (leaverStatus != null ? leaverStatus.hashCode() : 0);
        result = 31 * result + (playerDamageDetails != null ? playerDamageDetails.hashCode() : 0);
        result = 31 * result + (playerLastHitDetails != null ? playerLastHitDetails.hashCode() : 0);
        return result;
    }

    public void addDetails(List<AbilityUpgrade> abilities, List<ItemAndSlot> itemAndSlots, Integer kills, Integer deaths, Integer assists, Boolean leaverStatus, Integer gold, Integer lastHits, Integer denies, Integer goldPerMinute, Integer experiencePerMinute, Integer goldSpent, Integer heroDamage, Integer towerDamage, Integer heroHealing, Integer level) {

        this.abilities = abilities;
        this.itemAndSlots = itemAndSlots;
        this.playerKDADetails = new PlayerKDADetails(kills, deaths, assists);
        this.playerGoldExperienceDetails = new PlayerGoldExperienceDetails(gold, goldPerMinute, goldSpent, level, experiencePerMinute);
        this.playerDamageDetails = new PlayerDamageDetails(heroDamage, towerDamage, heroHealing);
        this.playerLastHitDetails = new PlayerLastHitDetails(lastHits, denies);
        this.leaverStatus = leaverStatus;
    }

}
