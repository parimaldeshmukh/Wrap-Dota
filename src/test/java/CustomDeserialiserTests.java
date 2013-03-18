import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.wrapdota.deserializer.DotaMatchDeserializer;
import org.wrapdota.deserializer.PlayerDeserializer;
import org.wrapdota.model.DotaMatch;
import org.wrapdota.model.Player;

import java.util.ArrayList;
import java.util.List;

public class CustomDeserialiserTests {

    @Test
    public void itDeserialisesPlayerFromJsonResponse() {
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
    public void itDeserializesDotaMatch() {
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

        DotaMatch expectedDotaMatch = new DotaMatch(1705566L, 1646464L, 1362930291L, 0, setupFirstPlayers());

        //when
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DotaMatch.class, new DotaMatchDeserializer());

        Gson dotaMatchGson = gsonBuilder.create();

        DotaMatch actualDotaMatch = dotaMatchGson.fromJson(json, DotaMatch.class);

        //then

        assert (actualDotaMatch.equals(expectedDotaMatch));

    }

    private List<Player> setupFirstPlayers() {

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
