package org.wrapdota.deserializer;

import com.google.gson.*;
import org.wrapdota.model.Player;

import java.lang.reflect.Type;

public class PlayerDeserializer implements JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Long accountId = jsonObject.get("account_id").getAsLong();
        Integer playerSlot = jsonObject.get("player_slot").getAsInt();
        Integer heroId = jsonObject.get("hero_id").getAsInt();


        return new Player(accountId, playerSlot, heroId);
    }
}
