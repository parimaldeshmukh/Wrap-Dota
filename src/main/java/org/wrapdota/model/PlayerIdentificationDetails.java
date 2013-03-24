package org.wrapdota.model;

public class PlayerIdentificationDetails {
    private final Long accountId;
    private final Integer playerSlot;
    private final Integer heroId;

    public PlayerIdentificationDetails(Long accountId, Integer playerSlot, Integer heroId) {
        this.accountId = accountId;
        this.playerSlot = playerSlot;
        this.heroId = heroId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerIdentificationDetails)) return false;

        PlayerIdentificationDetails playerIdentificationDetails = (PlayerIdentificationDetails) o;

        if (!accountId.equals(playerIdentificationDetails.accountId)) return false;
        if (!heroId.equals(playerIdentificationDetails.heroId)) return false;
        if (!playerSlot.equals(playerIdentificationDetails.playerSlot)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId.hashCode();
        result = 31 * result + playerSlot.hashCode();
        result = 31 * result + heroId.hashCode();
        return result;
    }
}
