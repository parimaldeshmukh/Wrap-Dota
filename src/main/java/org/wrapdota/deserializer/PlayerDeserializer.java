package org.wrapdota.deserializer;

import com.google.gson.*;
import org.wrapdota.model.AbilityUpgrade;
import org.wrapdota.model.ItemAndSlot;
import org.wrapdota.model.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlayerDeserializer implements JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Long accountId = jsonObject.get("account_id").getAsLong();
        Integer playerSlot = jsonObject.get("player_slot").getAsInt();
        Integer heroId = jsonObject.get("hero_id").getAsInt();

        Player player = new Player(accountId, playerSlot, heroId);

        if(jsonObject.has("level")) {      //some field which is not in history

            Integer kills = jsonObject.get("kills").getAsInt();
            Integer deaths = jsonObject.get("deaths").getAsInt();
            Integer assists = jsonObject.get("assists").getAsInt();
            Integer gold = jsonObject.get("gold").getAsInt();
            Integer goldPerMinute = jsonObject.get("gold_per_min").getAsInt();
            Integer experiencePerMinute = jsonObject.get("xp_per_min").getAsInt();
            Integer goldSpent = jsonObject.get("gold_spent").getAsInt();
            Integer lastHits = jsonObject.get("last_hits").getAsInt();
            Integer denies = jsonObject.get("denies").getAsInt();
            Integer level = jsonObject.get("level").getAsInt();
            Integer heroDamage = jsonObject.get("hero_damage").getAsInt();
            Integer heroHealing = jsonObject.get("hero_healing").getAsInt();
            Integer towerDamage = jsonObject.get("tower_damage").getAsInt();
            Boolean leaverStatus = jsonObject.get("leaver_status").getAsInt() == 0 ? false : true;

            List<AbilityUpgrade> abilities = setupAbilities(jsonObject.get("ability_upgrades").getAsJsonArray());
            List<ItemAndSlot> itemAndSlots = setupItemAndSlots(jsonObject);

            player.addDetails(abilities, itemAndSlots, kills, deaths, assists, leaverStatus, gold, lastHits, denies, goldPerMinute, experiencePerMinute, goldSpent, heroDamage, towerDamage, heroHealing, level);
        }

        return player;
    }

    private List<ItemAndSlot> setupItemAndSlots(JsonObject jsonObject) {

        List<ItemAndSlot> itemAndSlots = new ArrayList<ItemAndSlot>();

        itemAndSlots.add(new ItemAndSlot(jsonObject.get("item_0").getAsInt(), 0));
        itemAndSlots.add(new ItemAndSlot(jsonObject.get("item_1").getAsInt(), 1));
        itemAndSlots.add(new ItemAndSlot(jsonObject.get("item_2").getAsInt(), 2));
        itemAndSlots.add(new ItemAndSlot(jsonObject.get("item_3").getAsInt(), 3));
        itemAndSlots.add(new ItemAndSlot(jsonObject.get("item_4").getAsInt(), 4));
        itemAndSlots.add(new ItemAndSlot(jsonObject.get("item_5").getAsInt(), 5));

        return itemAndSlots;
    }

    private List<AbilityUpgrade> setupAbilities(JsonArray abilities) {

        List<AbilityUpgrade> abilityUpgrades = new ArrayList<AbilityUpgrade>();
        Integer abilityId;
        Integer atLevel;
        Long atTime;

        for(JsonElement ability: abilities) {
            JsonObject jsonObject = ability.getAsJsonObject();

           abilityId  = jsonObject.get("ability").getAsInt();
           atLevel =  jsonObject.get("level").getAsInt();
           atTime = jsonObject.get("time").getAsLong();

            abilityUpgrades.add(new AbilityUpgrade(abilityId, atTime, atLevel));
        }

        return abilityUpgrades;
    }
}
