package org.wrapdota.deserializer;

import com.google.gson.*;
import org.wrapdota.model.DotaMatch;
import org.wrapdota.model.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DotaMatchDeserializer implements JsonDeserializer<DotaMatch>{

    @Override
    public DotaMatch deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Long matchId = jsonObject.get("match_id").getAsLong();
        Long matchSeqNum = jsonObject.get("match_seq_num").getAsLong();
        Long matchStartTime = jsonObject.get("start_time").getAsLong();
        Integer lobbyType = jsonObject.get("lobby_type").getAsInt();

       JsonArray playerJsonArray = jsonObject.get("players").getAsJsonArray();
        List<Player> players = new ArrayList<Player>();

        GsonBuilder playerGsonBuilder = new GsonBuilder();
        playerGsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        Gson playerDeserializer = playerGsonBuilder.create();

        for(int i = 0; i<playerJsonArray.size(); i ++) {
           JsonElement playerJsonElement= playerJsonArray.get(i);
            Player player = playerDeserializer.fromJson(playerJsonElement, Player.class);
            players.add(player);
        }

        return new DotaMatch(matchId, matchSeqNum,matchStartTime,lobbyType,players);
    }
}
