package org.wrapdota.model;

import java.util.List;

public class DotaMatch {
    private final Long match_id;
    private final Long match_seq_num;
    private final Long start_time;
    private final Integer lobby_type;
    private final List<Player> players;
    private Faction winningFaction;
    private Long duration;
    private Integer radiantTowerStatus;
    private Integer direTowerStatus;
    private Integer radiantBarrackStatus;
    private Integer direBarrackStatus;
    private Integer cluster;
    private Integer humanPlayers;
    private Integer leagueId;
    private Integer positiveVotes;
    private Integer negativeVotes;
    private Integer gameMode;
    private Long firstBloodTime;
    private Integer season;

    public DotaMatch(Long match_id, Long match_seq_num, Long start_time, Integer lobby_type, List<Player> players) {

        this.match_id = match_id;
        this.match_seq_num = match_seq_num;
        this.start_time = start_time;
        this.lobby_type = lobby_type;
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DotaMatch)) return false;

        DotaMatch dotaMatch = (DotaMatch) o;

        if (!lobby_type.equals(dotaMatch.lobby_type)) return false;
        if (!match_id.equals(dotaMatch.match_id)) return false;
        if (!match_seq_num.equals(dotaMatch.match_seq_num)) return false;
        if (!players.equals(dotaMatch.players)) return false;
        if (!start_time.equals(dotaMatch.start_time)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = match_id.hashCode();
        result = 31 * result + match_seq_num.hashCode();
        result = 31 * result + start_time.hashCode();
        result = 31 * result + lobby_type.hashCode();
        result = 31 * result + players.hashCode();
        return result;
    }

    public Faction winningFaction() {
        return winningFaction;
    }

    public Long duration() {
        return duration;
    }

    public Integer radiantTowerStatus() {
        return radiantTowerStatus;
    }

    public Integer direTowerStatus() {
        return direTowerStatus;
    }

    public void addDetails(Long firstBloodTime, Integer season, Faction winningFaction, Long duration, Integer radiantTowerStatus, Integer direTowerStatus, Integer radiantBarrackStatus, Integer direBarrackStatus, Integer cluster, Integer humanPlayers, Integer leagueId, Integer positiveVotes, Integer negativeVotes, Integer gameMode) {

        this.firstBloodTime = firstBloodTime;
        this.season = season;
        this.winningFaction = winningFaction;
        this.duration = duration;
        this.radiantTowerStatus = radiantTowerStatus;
        this.direTowerStatus = direTowerStatus;
        this.radiantBarrackStatus = radiantBarrackStatus;
        this.direBarrackStatus = direBarrackStatus;
        this.cluster = cluster;
        this.humanPlayers = humanPlayers;
        this.leagueId = leagueId;
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;
        this.gameMode = gameMode;
    }
}
