import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wrapdota.model.DotaMatch;
import org.wrapdota.model.Player;
import org.wrapdota.server.SteamServer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SteamServerTests {

    static HttpClient mockHTTPClient = mock(HttpClient.class);
    static HttpResponse mockHttpResponse = mock(HttpResponse.class);
    static String history="";
    static String details="";

    @BeforeClass
    public static void setupDummyResponses() throws IOException {
        FileReader dummyMatchDetails = new FileReader("/home/parimal/petties/Wrap-Dota/src/test/resources/MatchDetailsResponse.json");
        FileReader dummyMatchHistory = new FileReader("/home/parimal/petties/Wrap-Dota/src/test/resources/MatchHistoryResponse.json");

        BufferedReader reader = new BufferedReader(dummyMatchHistory);
        String temp;

        while((temp = reader.readLine()) != null) {
            history = history.concat(temp);
        }

        reader.close();

        reader = new BufferedReader(dummyMatchDetails);

        while((temp= reader.readLine())!=null) {
            details = details.concat(temp);
        }


        given(mockHTTPClient.execute(any(HttpGet.class))).willReturn(mockHttpResponse);

    }

    @Test()
    public void itGetsLatestMatches() throws IOException {

        //given
        SteamServer steamServer = new SteamServer("my_dummy_api_key", mockHTTPClient);
        String serverMatchHistoryResponse = history;
        HttpEntity stringEntity = new StringEntity(serverMatchHistoryResponse);


        given(mockHttpResponse.getEntity()).willReturn(stringEntity);

        DotaMatch expectedMatchOne = new DotaMatch(1705566L, 1646464L, 1362930291L, 0, setupFirstPlayers());
        DotaMatch expectedMatchTwo = new DotaMatch(1705533L, 1646432L, 1362930245L, 1, setupSecondPlayers());

        //when
        List<DotaMatch> matches = steamServer.getLatestMatches();

        //then
        assert (expectedMatchOne.equals(matches.get(0)));
        assert (expectedMatchTwo.equals(matches.get(1)));
    }

    @Test
    public void itGetsDetailsOfMatchByMatchId() throws IOException {
        //given
        SteamServer steamServer = new SteamServer("my_dummy_api_key", mockHTTPClient);
        Long matchId = 1705566L;


        String serverMatchDetailsResponse = details;
        given(mockHttpResponse.getEntity()).willReturn(new StringEntity(serverMatchDetailsResponse));

        DotaMatch expectedDotaMatch = new DotaMatch(1705566L, 1646464L, 1362930291L, 0, setupFirstPlayers());

        //when
        DotaMatch dotaMatch = steamServer.getMatchDetailsBy(matchId);


        //then
        assert (dotaMatch.equals(expectedDotaMatch));
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

    private List<Player> setupSecondPlayers() {

        ArrayList<Player> players = new ArrayList<Player>(10);

        Long accountId = 4294967295L;
        Integer playerSlot = 0;
        Integer heroId = 11;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 4294962395L;
        playerSlot = 1;
        heroId = 35;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1291232395L;
        playerSlot = 2;
        heroId = 10;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1294969875L;
        playerSlot = 3;
        heroId = 48;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 1294964565L;
        playerSlot = 4;
        heroId = 8;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 9874962395L;
        playerSlot = 128;
        heroId = 21;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 2464962395L;
        playerSlot = 129;
        heroId = 2;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 5794962395L;
        playerSlot = 130;
        heroId = 17;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 9634962395L;
        playerSlot = 131;
        heroId = 43;
        players.add(new Player(accountId, playerSlot, heroId));

        accountId = 3324962395L;
        playerSlot = 132;
        heroId = 16;
        players.add(new Player(accountId, playerSlot, heroId));

        return players;
    }


}
