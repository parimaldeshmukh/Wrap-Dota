package org.wrapdota.model;


public class DotaMatchIdentificationDetails {
    Long matchId;
    Long matchSequenceNumber;
    Integer cluster;
    Integer leagueId;
    Integer gameMode;
    Integer season;
    Integer lobbyType;

    public DotaMatchIdentificationDetails(Long matchId, Long matchSequenceNumber, Integer lobbyType) {
        this.matchSequenceNumber = matchSequenceNumber;
        this.matchId = matchId;
        this.lobbyType = lobbyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DotaMatchIdentificationDetails)) return false;

        DotaMatchIdentificationDetails that = (DotaMatchIdentificationDetails) o;

        if (cluster != null ? !cluster.equals(that.cluster) : that.cluster != null) return false;
        if (gameMode != null ? !gameMode.equals(that.gameMode) : that.gameMode != null) return false;
        if (leagueId != null ? !leagueId.equals(that.leagueId) : that.leagueId != null) return false;
        if (!lobbyType.equals(that.lobbyType)) return false;
        if (!matchId.equals(that.matchId)) return false;
        if (!matchSequenceNumber.equals(that.matchSequenceNumber)) return false;
        if (season != null ? !season.equals(that.season) : that.season != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = matchId.hashCode();
        result = 31 * result + matchSequenceNumber.hashCode();
        result = 31 * result + (cluster != null ? cluster.hashCode() : 0);
        result = 31 * result + (leagueId != null ? leagueId.hashCode() : 0);
        result = 31 * result + (gameMode != null ? gameMode.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
        result = 31 * result + lobbyType.hashCode();
        return result;
    }

    public void addDetails(Integer cluster, Integer leagueId, Integer season, Integer gameMode) {
        this.cluster = cluster;
        this.gameMode = gameMode;
        this.leagueId = leagueId;
        this.season = season;
    }
}
