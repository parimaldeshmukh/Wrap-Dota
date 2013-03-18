package org.wrapdota.model;

public class Player {

    private final Long account_id;
    private final Integer player_slot;
    private final Integer hero_id;

    public Player(Long account_id, Integer player_slot, Integer hero_id) {

        this.account_id = account_id;
        this.player_slot = player_slot;
        this.hero_id = hero_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (!account_id.equals(player.account_id)) return false;
        if (!hero_id.equals(player.hero_id)) return false;
        if (!player_slot.equals(player.player_slot)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account_id.hashCode();
        result = 31 * result + player_slot.hashCode();
        result = 31 * result + hero_id.hashCode();
        return result;
    }
}
