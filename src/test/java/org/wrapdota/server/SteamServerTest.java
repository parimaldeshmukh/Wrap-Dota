package org.wrapdota.server;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wrapdota.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SteamServerTest {

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

    @Test
    public void itGetsLatestMatches() throws IOException {

        //given
        SteamServer steamServer = new SteamServer("my_dummy_api_key", mockHTTPClient);
        HttpEntity stringEntity = new StringEntity(history);


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


        given(mockHttpResponse.getEntity()).willReturn(new StringEntity(details));

        DotaMatch expectedDotaMatch = new DotaMatch(1705566L, 1646464L, 1362930291L, 0, setupDetailedPlayers());
        expectedDotaMatch.addDetails(98L, 7, Faction.RADIANT, 1000L, 2047, 1926, 63,63, 181, 0, 2,0,1);

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


    private List<Player> setupDetailedPlayers() {

        ArrayList<Player> players = new ArrayList<Player>(10);

        Long accountId = 4294967295L;
        Integer playerSlot = 0;
        Integer heroId = 21;
        Player sentinelPlayer = new Player(accountId, playerSlot, heroId);

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


        sentinelPlayer.addDetails(abilities, itemAndSlots, kills, deaths, assists, leaverStatus, gold, lastHits, denies, goldPerMinute, experiencePerMinute, goldSpent, heroDamage, towerDamage, heroHealing, level);


        accountId = 9874962395L;
        playerSlot = 128;
        heroId = 42;
        Player direPlayer = new Player(accountId, playerSlot, heroId);

        abilities = setupOtherAbilityUpgrades();
        itemAndSlots = setupOtherItemsAndSlots();
        kills = 2;
        deaths = 9;
        assists = 0;
        leaverStatus = true;
        gold = 2691;
        lastHits = 1;
        denies = 0;
        goldPerMinute = 214;
        experiencePerMinute = 104;
        goldSpent = 206;
        heroDamage = 915;
        towerDamage= 0;
        heroHealing= 0;
        level= 5;

        direPlayer.addDetails(abilities, itemAndSlots, kills, deaths, assists, leaverStatus, gold, lastHits, denies, goldPerMinute, experiencePerMinute, goldSpent, heroDamage, towerDamage, heroHealing, level);

        players.add(sentinelPlayer);
        players.add(direPlayer);
        return players;
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


    private List<ItemAndSlot> setupOtherItemsAndSlots() {

        List<ItemAndSlot> itemAndSlots = new ArrayList<ItemAndSlot>();
        itemAndSlots.add(new ItemAndSlot(16,0));
        itemAndSlots.add(new ItemAndSlot(16,1));
        itemAndSlots.add(new ItemAndSlot(39,2));
        itemAndSlots.add(new ItemAndSlot(0,3));
        itemAndSlots.add(new ItemAndSlot(0,4));
        itemAndSlots.add(new ItemAndSlot(0,5));
        return itemAndSlots;
    }

    private List<AbilityUpgrade> setupOtherAbilityUpgrades() {
        List<AbilityUpgrade> abilityUpgrades = new ArrayList<AbilityUpgrade>();
        abilityUpgrades.add(new AbilityUpgrade(5086,122L,1));
        abilityUpgrades.add(new AbilityUpgrade(5087,298L,2));
        abilityUpgrades.add(new AbilityUpgrade(5088,934L,3));
        abilityUpgrades.add(new AbilityUpgrade(5086,1035L,4));
        abilityUpgrades.add(new AbilityUpgrade(5086,1036L,5));

        return abilityUpgrades;
    }



}
