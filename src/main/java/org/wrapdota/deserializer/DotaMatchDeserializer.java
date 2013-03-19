package org.wrapdota.deserializer;

import com.google.gson.*;
import org.wrapdota.model.DotaMatch;
import org.wrapdota.model.Faction;
import org.wrapdota.model.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DotaMatchDeserializer implements JsonDeserializer<DotaMatch> {

    @Override
    public DotaMatch deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        DotaMatch match = createDotaMatchFrom(jsonObject);

        if (jsonObject.has("cluster") && jsonObject.has("duration") && jsonObject.has("game_mode")) {
            addDetailsTo(match, jsonObject);
        }

        return match;
    }

    private void addDetailsTo(DotaMatch match, JsonObject jsonObject) {

        Long firstBloodTime = jsonObject.get("first_blood_time").getAsLong();
        Integer season = jsonObject.get("season").getAsInt();
        Faction winningFaction = null;
        if (jsonObject.get("radiant_win").getAsBoolean()) {
            winningFaction = Faction.RADIANT;
        } else {
            winningFaction = Faction.DIRE;
        }
        Long duration = jsonObject.get("duration").getAsLong();
        Integer radiantTowerStatus = jsonObject.get("tower_status_radiant").getAsInt();
        Integer direTowerStatus = jsonObject.get("tower_status_dire").getAsInt();
        Integer radiantBarrackStatus = jsonObject.get("barracks_status_radiant").getAsInt();
        Integer direBarrackStatus = jsonObject.get("barracks_status_dire").getAsInt();
        Integer cluster = jsonObject.get("cluster").getAsInt();
        Integer humanPlayers = jsonObject.get("human_players").getAsInt();
        Integer leagueId = jsonObject.get("leagueid").getAsInt();
        Integer positiveVotes = jsonObject.get("positive_votes").getAsInt();
        Integer negativeVotes = jsonObject.get("negative_votes").getAsInt();
        Integer gameMode = jsonObject.get("game_mode").getAsInt();

        match.addDetails(firstBloodTime, season, winningFaction, duration, radiantTowerStatus, direTowerStatus, radiantBarrackStatus, direBarrackStatus, cluster, humanPlayers, leagueId, positiveVotes, negativeVotes, gameMode);

    }

    private DotaMatch createDotaMatchFrom(JsonObject jsonObject) {

        Long matchId = jsonObject.get("match_id").getAsLong();
        Long matchSeqNum = jsonObject.get("match_seq_num").getAsLong();
        Long matchStartTime = jsonObject.get("start_time").getAsLong();
        Integer lobbyType = jsonObject.get("lobby_type").getAsInt();

        JsonArray playerJsonArray = jsonObject.get("players").getAsJsonArray();
        List<Player> players = createPlayersFrom(playerJsonArray);

        return new DotaMatch(matchId, matchSeqNum, matchStartTime, lobbyType, players);
    }


    private List<Player> createPlayersFrom(JsonArray playerJsonArray) {
        List<Player> players = new ArrayList<Player>();

        GsonBuilder playerGsonBuilder = new GsonBuilder();
        playerGsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        Gson playerDeserializer = playerGsonBuilder.create();
//
//        for (int i = 0; i < playerJsonArray.size(); i++) {
//            JsonElement playerJsonElement = playerJsonArray.get(i);
//            Player player = playerDeserializer.fromJson(playerJsonElement, Player.class);
//            players.add(player);
//        }

        for(JsonElement playerJson : playerJsonArray){
            players.add(playerDeserializer.fromJson(playerJson, Player.class));
        }

        return players;
    }
}
