package org.wrapdota.model;

import java.util.List;

public class DotaMatch {

    private Faction winningFaction;
    private DotaMatchIdentificationDetails identificationDetails;
    private DotaMatchQualityDetails qualityDetails;
    private DotaMatchStructureDetails structureDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DotaMatch)) return false;

        DotaMatch dotaMatch = (DotaMatch) o;

        if (!identificationDetails.equals(dotaMatch.identificationDetails)) return false;
        if (!players.equals(dotaMatch.players)) return false;
        if (qualityDetails != null ? !qualityDetails.equals(dotaMatch.qualityDetails) : dotaMatch.qualityDetails != null)
            return false;
        if (structureDetails != null ? !structureDetails.equals(dotaMatch.structureDetails) : dotaMatch.structureDetails != null)
            return false;
        if (!timingDetails.equals(dotaMatch.timingDetails)) return false;
        if (winningFaction != dotaMatch.winningFaction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = winningFaction != null ? winningFaction.hashCode() : 0;
        result = 31 * result + identificationDetails.hashCode();
        result = 31 * result + (qualityDetails != null ? qualityDetails.hashCode() : 0);
        result = 31 * result + (structureDetails != null ? structureDetails.hashCode() : 0);
        result = 31 * result + timingDetails.hashCode();
        result = 31 * result + players.hashCode();
        return result;
    }

    private DotaMatchTimingDetails timingDetails;
    private List<Player> players;


    public DotaMatch(Long matchId, Long matchSeqNum, Long startTime, Integer lobbyType, List<Player> players) {
        this.players = players;
        identificationDetails = new DotaMatchIdentificationDetails(matchId, matchSeqNum, lobbyType);
        timingDetails = new DotaMatchTimingDetails(startTime);
    }

    public void addDetails(Long firstBloodTime, Integer season, Faction winningFaction, Long duration,
                           Integer radiantTowerStatus, Integer direTowerStatus, Integer radiantBarrackStatus,
                           Integer direBarrackStatus, Integer cluster, Integer leagueId,
                           Integer positiveVotes, Integer negativeVotes, Integer gameMode) {

        structureDetails = new DotaMatchStructureDetails(radiantTowerStatus,direTowerStatus,radiantBarrackStatus,
                direBarrackStatus);

        qualityDetails = new DotaMatchQualityDetails(positiveVotes,negativeVotes);

        identificationDetails.addDetails(cluster, leagueId, season, gameMode);

        timingDetails.addDetails(duration, firstBloodTime);

        this.winningFaction = winningFaction;

    }
}