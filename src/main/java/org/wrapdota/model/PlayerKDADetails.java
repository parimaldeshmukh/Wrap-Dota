package org.wrapdota.model;


class PlayerKDADetails {
    private final Integer kills;
    private final Integer deaths;
    private final Integer assists;

    public PlayerKDADetails(Integer kills, Integer deaths, Integer assists) {
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerKDADetails)) return false;

        PlayerKDADetails that = (PlayerKDADetails) o;

        if (!assists.equals(that.assists)) return false;
        if (!deaths.equals(that.deaths)) return false;
        if (!kills.equals(that.kills)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kills.hashCode();
        result = 31 * result + deaths.hashCode();
        result = 31 * result + assists.hashCode();
        return result;
    }
}
