package org.wrapdota.model;

import java.util.List;

public class DotaMatch {
    private Long match_id;
    private Long match_seq_num;
    private Long start_time;
    private Integer lobby_type;
    private List<Player> players;
    private Faction winningFaction;
    private Integer duration;
    private Integer radiantTowerStatus;
    private Integer direTowerStatus;

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

    public Integer duration() {
        return duration;
    }

    public Integer radiantTowerStatus() {
        return radiantTowerStatus;
    }

    public Integer direTowerStatus() {
        return direTowerStatus;
    }
}
