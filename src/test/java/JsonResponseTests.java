import com.google.gson.Gson;
import org.junit.Test;
import org.wrapdota.model.DotaMatch;
import org.wrapdota.model.Player;
import org.wrapdota.server.JsonResponse;
import org.wrapdota.server.ResponseResult;

import java.util.ArrayList;
import java.util.List;

public class JsonResponseTests {

    @Test
    public void itCreatesDotaMatchFromJsonBeta() {
        //given
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

        Long matchId = 1705566L;
        Long matchSequenceNumber = 1646464L;
        Long startTime = 1362930291L;
        Integer lobbyType = 0;
        List<Player> players;

        players = setupFirstMatchPlayers();

        DotaMatch expectedDotaMatch = new DotaMatch(matchId, matchSequenceNumber, startTime, lobbyType, players);


        //when
        Gson gson = new Gson();
        DotaMatch actualDotaMatch = gson.fromJson(json, DotaMatch.class);

        //then
        assert(expectedDotaMatch.equals(actualDotaMatch));
    }

        private List<Player> setupFirstMatchPlayers() {

            ArrayList<Player> players = new ArrayList<Player>(10);

            Long accountId = 4294967295L; Integer playerSlot = 0; Integer heroId = 21;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 4294962395L; playerSlot = 1; heroId = 71;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 1291232395L; playerSlot = 2; heroId = 5;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 1294969875L; playerSlot = 3; heroId = 97;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 1294964565L; playerSlot = 4; heroId = 4;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 9874962395L; playerSlot = 128; heroId = 42;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 2464962395L; playerSlot = 129; heroId = 1;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 5794962395L; playerSlot = 130; heroId = 32;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 9634962395L; playerSlot = 131; heroId = 86;
            players.add(new Player(accountId, playerSlot, heroId));

            accountId = 3324962395L; playerSlot = 132; heroId = 8;
            players.add(new Player(accountId, playerSlot, heroId));

            return players;
        }

    private List<Player> setupSecondMatchPlayers() {

        ArrayList<Player> players = new ArrayList<Player>(10);

        Long accountId = 4294967295L; Integer playerSlot = 0; Integer heroId = 11;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 4294962395L; playerSlot = 1; heroId = 35;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1291232395L; playerSlot = 2; heroId = 10;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1294969875L; playerSlot = 3; heroId = 48;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1294964565L; playerSlot = 4; heroId = 8;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 9874962395L; playerSlot = 128; heroId = 21;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 2464962395L; playerSlot = 129; heroId = 2;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 5794962395L; playerSlot = 130; heroId = 17;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 9634962395L; playerSlot = 131; heroId = 43;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 3324962395L; playerSlot = 132; heroId = 16;
        players.add(new Player(accountId, playerSlot, heroId));

        return players;
    }


    @Test
    public void itCreatesDotaMatchFromJson() {
        //given
        String json = "{\n" +
                "\"result\" : {\n" +
                "\"status\" : 1,\n" +
                "\"num_results\" : 2,\n" +
                "\"total_results\" : 4,\n" +
                "\"results_remaining\" : 2," +
                "\"matches\" : [\n" +
                "{\n" +
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
                "},\n" +
                "{\n" +
                "\"match_id\": 1705533,\n\"" +
                "match_seq_num\": 1646432,\n" +
                "\"start_time\": 1362930245,\n" +
                "\"lobby_type\": 1,\n" +
                "\"players\": [\n" +
                "\t{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 0,\n" +
                "\"hero_id\": 11\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 4294962395,\n" +
                "\"player_slot\": 1,\n" +
                "\"hero_id\": 35\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 1291232395,\n" +
                "\"player_slot\": 2,\n" +
                "\"hero_id\": 10\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 1294969875,\n" +
                "\"player_slot\": 3,\n" +
                "\"hero_id\": 48\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 1294964565,\n" +
                "\"player_slot\": 4,\n" +
                "\"hero_id\": 8\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 9874962395,\n" +
                "\"player_slot\": 128,\n" +
                "\"hero_id\": 21\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 2464962395,\n" +
                "\"player_slot\": 129,\n" +
                "\"hero_id\": 2\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 5794962395,\n" +
                "\"player_slot\": 130,\n" +
                "\"hero_id\": 17\n" +
                "\t},\n" +
                "\t{\n" +
                "\"account_id\": 9634962395,\n" +
                "\"player_slot\": 131,\n" +
                "\"hero_id\": 43\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 3324962395,\n" +
                "\"player_slot\": 132,\n" +
                "\"hero_id\": 16\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "}";

        Long matchIdOne = 1705566L;
        Long matchSequenceNumberOne = 1646464L;
        Long startTimeOne = 1362930291L;
        Integer lobbyTypeOne = 0;
        List<Player> playersOne, playersTwo;

        playersOne = setupFirstMatchPlayers();
        playersTwo = setupSecondMatchPlayers();

        DotaMatch expectedDotaMatchOne = new DotaMatch(matchIdOne, matchSequenceNumberOne, startTimeOne, lobbyTypeOne, playersOne);
        Long matchIdTwo = 1705533L;
        Long matchSequenceNumberTwo = 1646432L;
        Long startTimeTwo = 1362930245L;
        Integer lobbyTypeTwo = 1;
        DotaMatch expectedDotaMatchTwo = new DotaMatch(matchIdTwo, matchSequenceNumberTwo, startTimeTwo, lobbyTypeTwo, playersTwo);


        //when
        JsonResponse response = new Gson().fromJson(json, JsonResponse.class);
        ResponseResult responseResult = response.get();
        List<DotaMatch> matches = responseResult.getMatches();

        //then
        assert(expectedDotaMatchOne.equals(matches.get(0)));
        assert(expectedDotaMatchTwo.equals(matches.get(1)));
    }

}
