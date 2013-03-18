import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Test;
import org.wrapdota.model.DotaMatch;
import org.wrapdota.model.Player;
import org.wrapdota.server.SteamServer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SteamServerTests {

    @Test()
    public void itGetsLatestMatches() throws IOException {

        //given
        HttpClient mockHTTPClient = mock(HttpClient.class);
        HttpResponse mockHttpResponse = mock(HttpResponse.class);

        HttpEntity stringEntity = new StringEntity("{\n" +
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
                "}");




        given(mockHTTPClient.execute(any(HttpGet.class))).willReturn(mockHttpResponse);
        given(mockHttpResponse.getEntity()).willReturn(stringEntity);

        SteamServer steamServer = new SteamServer("my_dummy_api_key", mockHTTPClient);
        DotaMatch expectedMatchOne = new DotaMatch(1705566L,1646464L,1362930291L,0, setupFirstPlayers());
        DotaMatch expectedMatchTwo = new DotaMatch(1705533L, 1646432L,1362930245L,1, setupSecondPlayers());

        //when
        List<DotaMatch> matches = steamServer.getLatestMatches();

        //then
        assert(expectedMatchOne.equals(matches.get(0)));
        assert(expectedMatchTwo.equals(matches.get(1)));
    }


    private List<Player> setupFirstPlayers() {

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

    private List<Player> setupSecondPlayers() {

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


}
