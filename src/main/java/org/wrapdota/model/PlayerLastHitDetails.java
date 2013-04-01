package org.wrapdota.model;

class PlayerLastHitDetails {
    private final Integer lastHits;
    private final Integer denies;

    public PlayerLastHitDetails(Integer lastHits, Integer denies) {
        this.lastHits = lastHits;
        this.denies = denies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerLastHitDetails)) return false;

        PlayerLastHitDetails that = (PlayerLastHitDetails) o;

        if (!denies.equals(that.denies)) return false;
        if (!lastHits.equals(that.lastHits)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastHits.hashCode();
        result = 31 * result + denies.hashCode();
        return result;
    }
}
