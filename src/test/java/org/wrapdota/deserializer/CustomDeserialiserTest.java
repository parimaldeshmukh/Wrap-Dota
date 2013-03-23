package org.wrapdota.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.wrapdota.model.AbilityUpgrade;
import org.wrapdota.model.DotaMatch;
import org.wrapdota.model.ItemAndSlot;
import org.wrapdota.model.Player;

import java.util.ArrayList;
import java.util.List;

public class CustomDeserialiserTest {

    @Test
    public void itDeserialisesPlayerFromGetHistoryResponse() {
        //given

        String json = "\t{\n" +
                "\"account_id\": 1291232395,\n" +
                "\"player_slot\": 2,\n" +
                "\"hero_id\": 5\n" +
                "}";

        Player expectedPlayer = new Player(1291232395L, 2, 5);

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());

        //when

        Gson playerGson = gsonBuilder.create();
        Player actualPlayer = playerGson.fromJson(json, Player.class);

        //then
        assert (expectedPlayer.equals(actualPlayer));

    }

    @Test
    public void itDeserialisesPlayerFromGetDetailsResponse() {
        //given

        String json = "{" +
                "account_id:4294967295," +
                "player_slot:0," +
                "hero_id:21," +
                "item_0:102," +
                "item_1:50," +
                "item_2:16," +
                "item_3:77," +
                "item_4:0," +
                "item_5:0," +
                "kills:3," +
                "deaths:1," +
                "assists:4," +
                "leaver_status:0," +
                "gold:2360," +
                "last_hits:45," +
                "denies:2," +
                "gold_per_min:412," +
                "xp_per_min:313," +
                "gold_spent:4993," +
                "hero_damage:6152," +
                "tower_damage:989," +
                "hero_healing:0," +
                "level:9," +
                "ability_upgrades:[" +
                "{" +
                "ability:5131," +
                "time:147," +
                "level:1" +
                "}," +
                "{" +
                "ability:5130," +
                "time:315," +
                "level:2" +
                "}," +
                "{" +
                "ability:5132," +
                "time:371," +
                "level:3" +
                "}," +
                "{" +
                "ability:5131," +
                "time:447," +
                "level:4" +
                "}," +
                "{" +
                "ability:5131," +
                "time:560," +
                "level:5" +
                "}," +
                "{" +
                "ability:5130," +
                "time:631," +
                "level:6" +
                "}," +
                "{" +
                "ability:5131," +
                "time:783," +
                "level:7" +
                "}," +
                "{" +
                "ability:5130," +
                "time:836," +
                "level:8" +
                "}," +
                "{" +
                "ability:5130," +
                "time:1065," +
                "level:9" +
                "}" +
                "]" +
                "}";

        Player expectedPlayer = new Player(4294967295L, 0, 21);
        List<AbilityUpgrade> abilities = setupAbilityUpgrades();
        List<ItemAndSlot> itemAndSlots = setupItemsAndSlots();
        Integer kills = 3;
        Integer deaths = 1;
        Integer assists = 4;
        Boolean leaverStatus = false;
        Integer gold = 2360;
        Integer lastHits = 45;
        Integer denies = 2;
        Integer goldPerMinute = 412;
        Integer experiencePerMinute = 313;
        Integer goldSpent = 4993;
        Integer heroDamage = 6152;
        Integer towerDamage= 989;
        Integer heroHealing= 0;
        Integer level= 9;


        expectedPlayer.addDetails(abilities, itemAndSlots, kills, deaths, assists, leaverStatus, gold, lastHits, denies, goldPerMinute, experiencePerMinute, goldSpent, heroDamage, towerDamage, heroHealing, level);

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());

        //when

        Gson playerGson = gsonBuilder.create();
        Player actualPlayer = playerGson.fromJson(json, Player.class);

        //then
        assert (expectedPlayer.equals(actualPlayer));

    }

    private List<ItemAndSlot> setupItemsAndSlots() {

        List<ItemAndSlot> itemAndSlots = new ArrayList<ItemAndSlot>();
        itemAndSlots.add(new ItemAndSlot(102,0));
        itemAndSlots.add(new ItemAndSlot(50,1));
        itemAndSlots.add(new ItemAndSlot(16,2));
        itemAndSlots.add(new ItemAndSlot(77,3));
        itemAndSlots.add(new ItemAndSlot(0,4));
        itemAndSlots.add(new ItemAndSlot(0,5));
        return itemAndSlots;
    }

    private List<AbilityUpgrade> setupAbilityUpgrades() {
        List<AbilityUpgrade> abilityUpgrades = new ArrayList<AbilityUpgrade>();
        abilityUpgrades.add(new AbilityUpgrade(5131,147L,1));
        abilityUpgrades.add(new AbilityUpgrade(5130,315L,2));
        abilityUpgrades.add(new AbilityUpgrade(5132,371L,3));
        abilityUpgrades.add(new AbilityUpgrade(5131,447L,4));
        abilityUpgrades.add(new AbilityUpgrade(5131,560L,5));
        abilityUpgrades.add(new AbilityUpgrade(5130,631L,6));
        abilityUpgrades.add(new AbilityUpgrade(5131,783L,7));
        abilityUpgrades.add(new AbilityUpgrade(5130,836L,8));
        abilityUpgrades.add(new AbilityUpgrade(5130,1065L,9));

        return abilityUpgrades;
    }

    @Test
    public void itDeserializesDotaMatchFromGetHistoryResponse() {
        String json = "{\n" +
                "\"match_id\": 1705566,\n\"" +
                "match_seq_num\": 1646464,\n" +
                "\"start_time\": 1362930291,\n" +
                "\"lobby_type\": 0,\n" +
                "\"players\": [\n" +
                "\t{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 0,\n" +
                "\"hero_id\": 21\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 4294962395,\n" +
                "\"player_slot\": 1,\n" +
                "\"hero_id\": 71\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 1291232395,\n" +
                "\"player_slot\": 2,\n" +
                "\"hero_id\": 5\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 1294969875,\n" +
                "\"player_slot\": 3,\n" +
                "\"hero_id\": 97\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 1294964565,\n" +
                "\"player_slot\": 4,\n" +
                "\"hero_id\": 4\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 9874962395,\n" +
                "\"player_slot\": 128,\n" +
                "\"hero_id\": 42\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 2464962395,\n" +
                "\"player_slot\": 129,\n" +
                "\"hero_id\": 1\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 5794962395,\n" +
                "\"player_slot\": 130,\n" +
                "\"hero_id\": 32\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 9634962395,\n" +
                "\"player_slot\": 131,\n" +
                "\"hero_id\": 86\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 3324962395,\n" +
                "\"player_slot\": 132,\n" +
                "\"hero_id\": 8\n" +
                "}\n" +
                "]\n" +
                "}";

        DotaMatch expectedDotaMatch = new DotaMatch(1705566L, 1646464L, 1362930291L, 0, setupPlayers());

        //when
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DotaMatch.class, new DotaMatchDeserializer());

        Gson dotaMatchGson = gsonBuilder.create();

        DotaMatch actualDotaMatch = dotaMatchGson.fromJson(json, DotaMatch.class);

        //then

        assert (actualDotaMatch.equals(expectedDotaMatch));

    }

    private List<Player> setupPlayers() {

        ArrayList<Player> players = new ArrayList<Player>(10);

        Long accountId = 4294967295L;
        Integer playerSlot = 0;
        Integer heroId = 21;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 4294962395L;
        playerSlot = 1;
        heroId = 71;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1291232395L;
        playerSlot = 2;
        heroId = 5;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1294969875L;
        playerSlot = 3;
        heroId = 97;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1294964565L;
        playerSlot = 4;
        heroId = 4;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 9874962395L;
        playerSlot = 128;
        heroId = 42;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 2464962395L;
        playerSlot = 129;
        heroId = 1;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 5794962395L;
        playerSlot = 130;
        heroId = 32;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 9634962395L;
        playerSlot = 131;
        heroId = 86;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 3324962395L;
        playerSlot = 132;
        heroId = 8;
        players.add(new Player(accountId, playerSlot, heroId));

        return players;
    }
}
