import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.Test;
import org.wrapdota.model.DotaMatch;
import org.wrapdota.model.Player;
import org.wrapdota.server.SteamServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SteamServerTests {

    HttpClient mockHTTPClient = mock(HttpClient.class);
    HttpResponse mockHttpResponse = mock(HttpResponse.class);

    @Before
    public void setup() throws IOException {
        given(mockHTTPClient.execute(any(HttpGet.class))).willReturn(mockHttpResponse);
    }

    @Test()
    public void itGetsLatestMatches() throws IOException {

        //given
        SteamServer steamServer = new SteamServer("my_dummy_api_key", mockHTTPClient);
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
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 0,\n" +
                "\"hero_id\": 21\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294962395,\n" +
                "\"player_slot\": 1,\n" +
                "\"hero_id\": 71\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 1291232395,\n" +
                "\"player_slot\": 2,\n" +
                "\"hero_id\": 5\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 1294969875,\n" +
                "\"player_slot\": 3,\n" +
                "\"hero_id\": 97\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 1294964565,\n" +
                "\"player_slot\": 4,\n" +
                "\"hero_id\": 4\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 9874962395,\n" +
                "\"player_slot\": 128,\n" +
                "\"hero_id\": 42\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 2464962395,\n" +
                "\"player_slot\": 129,\n" +
                "\"hero_id\": 1\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 5794962395,\n" +
                "\"player_slot\": 130,\n" +
                "\"hero_id\": 32\n" +
                "},\n" +
                "{\n" +
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
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 0,\n" +
                "\"hero_id\": 11\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294962395,\n" +
                "\"player_slot\": 1,\n" +
                "\"hero_id\": 35\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 1291232395,\n" +
                "\"player_slot\": 2,\n" +
                "\"hero_id\": 10\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 1294969875,\n" +
                "\"player_slot\": 3,\n" +
                "\"hero_id\": 48\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 1294964565,\n" +
                "\"player_slot\": 4,\n" +
                "\"hero_id\": 8\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 9874962395,\n" +
                "\"player_slot\": 128,\n" +
                "\"hero_id\": 21\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 2464962395,\n" +
                "\"player_slot\": 129,\n" +
                "\"hero_id\": 2\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 5794962395,\n" +
                "\"player_slot\": 130,\n" +
                "\"hero_id\": 17\n" +
                "},\n" +
                "{\n" +
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


        given(mockHttpResponse.getEntity()).willReturn(new StringEntity("{\n" +
                "\"result\": {\n" +
                "\"players\": [\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 0,\n" +
                "\"hero_id\": 21,\n" +
                "\"item_0\": 102,\n" +
                "\"item_1\": 50,\n" +
                "\"item_2\": 16,\n" +
                "\"item_3\": 77,\n" +
                "\"item_4\": 0,\n" +
                "\"item_5\": 0,\n" +
                "\"kills\": 3,\n" +
                "\"deaths\": 1,\n" +
                "\"assists\": 4,\n" +
                "\"leaver_status\": 0,\n" +
                "\"gold\": 2360,\n" +
                "\"last_hits\": 45,\n" +
                "\"denies\": 2,\n" +
                "\"gold_per_min\": 412,\n" +
                "\"xp_per_min\": 313,\n" +
                "\"gold_spent\": 4993,\n" +
                "\"hero_damage\": 6152,\n" +
                "\"tower_damage\": 989,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 9,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5131,\n" +
                "\"time\": 147,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5130,\n" +
                "\"time\": 315,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5132,\n" +
                "\"time\": 371,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5131,\n" +
                "\"time\": 447,\n" +
                "\"level\": 4\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5131,\n" +
                "\"time\": 560,\n" +
                "\"level\": 5\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5130,\n" +
                "\"time\": 631,\n" +
                "\"level\": 6\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5131,\n" +
                "\"time\": 783,\n" +
                "\"level\": 7\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5130,\n" +
                "\"time\": 836,\n" +
                "\"level\": 8\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5130,\n" +
                "\"time\": 1065,\n" +
                "\"level\": 9\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 1,\n" +
                "\"hero_id\": 71,\n" +
                "\"item_0\": 63,\n" +
                "\"item_1\": 172,\n" +
                "\"item_2\": 38,\n" +
                "\"item_3\": 0,\n" +
                "\"item_4\": 0,\n" +
                "\"item_5\": 0,\n" +
                "\"kills\": 3,\n" +
                "\"deaths\": 0,\n" +
                "\"assists\": 4,\n" +
                "\"leaver_status\": 0,\n" +
                "\"gold\": 2855,\n" +
                "\"last_hits\": 33,\n" +
                "\"denies\": 0,\n" +
                "\"gold_per_min\": 346,\n" +
                "\"xp_per_min\": 305,\n" +
                "\"gold_spent\": 3530,\n" +
                "\"hero_damage\": 4487,\n" +
                "\"tower_damage\": 813,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 9,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5355,\n" +
                "\"time\": 111,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5353,\n" +
                "\"time\": 267,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5354,\n" +
                "\"time\": 399,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5355,\n" +
                "\"time\": 491,\n" +
                "\"level\": 4\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5355,\n" +
                "\"time\": 600,\n" +
                "\"level\": 5\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5356,\n" +
                "\"time\": 746,\n" +
                "\"level\": 6\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5354,\n" +
                "\"time\": 889,\n" +
                "\"level\": 7\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5354,\n" +
                "\"time\": 919,\n" +
                "\"level\": 8\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5354,\n" +
                "\"time\": 1079,\n" +
                "\"level\": 9\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 2,\n" +
                "\"hero_id\": 5,\n" +
                "\"item_0\": 214,\n" +
                "\"item_1\": 60,\n" +
                "\"item_2\": 73,\n" +
                "\"item_3\": 0,\n" +
                "\"item_4\": 22,\n" +
                "\"item_5\": 21,\n" +
                "\"kills\": 4,\n" +
                "\"deaths\": 1,\n" +
                "\"assists\": 1,\n" +
                "\"leaver_status\": 0,\n" +
                "\"gold\": 1398,\n" +
                "\"last_hits\": 42,\n" +
                "\"denies\": 0,\n" +
                "\"gold_per_min\": 407,\n" +
                "\"xp_per_min\": 450,\n" +
                "\"gold_spent\": 5160,\n" +
                "\"hero_damage\": 2563,\n" +
                "\"tower_damage\": 1190,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 11,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5126,\n" +
                "\"time\": 131,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5128,\n" +
                "\"time\": 235,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5128,\n" +
                "\"time\": 256,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5126,\n" +
                "\"time\": 301,\n" +
                "\"level\": 4\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5128,\n" +
                "\"time\": 321,\n" +
                "\"level\": 5\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5126,\n" +
                "\"time\": 428,\n" +
                "\"level\": 6\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5128,\n" +
                "\"time\": 468,\n" +
                "\"level\": 7\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5126,\n" +
                "\"time\": 550,\n" +
                "\"level\": 8\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5127,\n" +
                "\"time\": 761,\n" +
                "\"level\": 9\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5127,\n" +
                "\"time\": 900,\n" +
                "\"level\": 10\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5127,\n" +
                "\"time\": 963,\n" +
                "\"level\": 11\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 3,\n" +
                "\"hero_id\": 97,\n" +
                "\"item_0\": 180,\n" +
                "\"item_1\": 1,\n" +
                "\"item_2\": 0,\n" +
                "\"item_3\": 0,\n" +
                "\"item_4\": 0,\n" +
                "\"item_5\": 0,\n" +
                "\"kills\": 1,\n" +
                "\"deaths\": 2,\n" +
                "\"assists\": 7,\n" +
                "\"leaver_status\": 0,\n" +
                "\"gold\": 1309,\n" +
                "\"last_hits\": 18,\n" +
                "\"denies\": 1,\n" +
                "\"gold_per_min\": 288,\n" +
                "\"xp_per_min\": 252,\n" +
                "\"gold_spent\": 3735,\n" +
                "\"hero_damage\": 2994,\n" +
                "\"tower_damage\": 828,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 8,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5520,\n" +
                "\"time\": 173,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5518,\n" +
                "\"time\": 269,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5520,\n" +
                "\"time\": 367,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5518,\n" +
                "\"time\": 463,\n" +
                "\"level\": 4\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5518,\n" +
                "\"time\": 537,\n" +
                "\"level\": 5\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5521,\n" +
                "\"time\": 668,\n" +
                "\"level\": 6\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5518,\n" +
                "\"time\": 696,\n" +
                "\"level\": 7\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5519,\n" +
                "\"time\": 837,\n" +
                "\"level\": 8\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 4,\n" +
                "\"hero_id\": 4,\n" +
                "\"item_0\": 63,\n" +
                "\"item_1\": 182,\n" +
                "\"item_2\": 11,\n" +
                "\"item_3\": 0,\n" +
                "\"item_4\": 0,\n" +
                "\"item_5\": 0,\n" +
                "\"kills\": 6,\n" +
                "\"deaths\": 1,\n" +
                "\"assists\": 0,\n" +
                "\"leaver_status\": 0,\n" +
                "\"gold\": 1,\n" +
                "\"last_hits\": 38,\n" +
                "\"denies\": 8,\n" +
                "\"gold_per_min\": 345,\n" +
                "\"xp_per_min\": 238,\n" +
                "\"gold_spent\": 4320,\n" +
                "\"hero_damage\": 4419,\n" +
                "\"tower_damage\": 469,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 8,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5016,\n" +
                "\"time\": 154,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5015,\n" +
                "\"time\": 268,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5017,\n" +
                "\"time\": 348,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5016,\n" +
                "\"time\": 440,\n" +
                "\"level\": 4\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5016,\n" +
                "\"time\": 524,\n" +
                "\"level\": 5\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5018,\n" +
                "\"time\": 570,\n" +
                "\"level\": 6\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5016,\n" +
                "\"time\": 621,\n" +
                "\"level\": 7\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5015,\n" +
                "\"time\": 671,\n" +
                "\"level\": 8\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 128,\n" +
                "\"hero_id\": 42,\n" +
                "\"item_0\": 16,\n" +
                "\"item_1\": 16,\n" +
                "\"item_2\": 39,\n" +
                "\"item_3\": 0,\n" +
                "\"item_4\": 0,\n" +
                "\"item_5\": 0,\n" +
                "\"kills\": 2,\n" +
                "\"deaths\": 9,\n" +
                "\"assists\": 0,\n" +
                "\"leaver_status\": 1,\n" +
                "\"gold\": 2691,\n" +
                "\"last_hits\": 1,\n" +
                "\"denies\": 0,\n" +
                "\"gold_per_min\": 214,\n" +
                "\"xp_per_min\": 104,\n" +
                "\"gold_spent\": 206,\n" +
                "\"hero_damage\": 915,\n" +
                "\"tower_damage\": 0,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 5,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5086,\n" +
                "\"time\": 122,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5087,\n" +
                "\"time\": 298,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5088,\n" +
                "\"time\": 934,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5086,\n" +
                "\"time\": 1035,\n" +
                "\"level\": 4\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5086,\n" +
                "\"time\": 1036,\n" +
                "\"level\": 5\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 129,\n" +
                "\"hero_id\": 1,\n" +
                "\"item_0\": 0,\n" +
                "\"item_1\": 0,\n" +
                "\"item_2\": 0,\n" +
                "\"item_3\": 182,\n" +
                "\"item_4\": 56,\n" +
                "\"item_5\": 16,\n" +
                "\"kills\": 1,\n" +
                "\"deaths\": 1,\n" +
                "\"assists\": 0,\n" +
                "\"leaver_status\": 1,\n" +
                "\"gold\": 1,\n" +
                "\"last_hits\": 18,\n" +
                "\"denies\": 5,\n" +
                "\"gold_per_min\": 176,\n" +
                "\"xp_per_min\": 150,\n" +
                "\"gold_spent\": 1471,\n" +
                "\"hero_damage\": 1025,\n" +
                "\"tower_damage\": 0,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 6,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5004,\n" +
                "\"time\": 125,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5003,\n" +
                "\"time\": 280,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5004,\n" +
                "\"time\": 340,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5003,\n" +
                "\"time\": 465,\n" +
                "\"level\": 4\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5004,\n" +
                "\"time\": 546,\n" +
                "\"level\": 5\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5006,\n" +
                "\"time\": 638,\n" +
                "\"level\": 6\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 130,\n" +
                "\"hero_id\": 32,\n" +
                "\"item_0\": 71,\n" +
                "\"item_1\": 29,\n" +
                "\"item_2\": 0,\n" +
                "\"item_3\": 0,\n" +
                "\"item_4\": 0,\n" +
                "\"item_5\": 0,\n" +
                "\"kills\": 0,\n" +
                "\"deaths\": 2,\n" +
                "\"assists\": 1,\n" +
                "\"leaver_status\": 1,\n" +
                "\"gold\": 2134,\n" +
                "\"last_hits\": 4,\n" +
                "\"denies\": 0,\n" +
                "\"gold_per_min\": 190,\n" +
                "\"xp_per_min\": 171,\n" +
                "\"gold_spent\": 1280,\n" +
                "\"hero_damage\": 1702,\n" +
                "\"tower_damage\": 0,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 7,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5143,\n" +
                "\"time\": 153,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5142,\n" +
                "\"time\": 280,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5143,\n" +
                "\"time\": 320,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5144,\n" +
                "\"time\": 418,\n" +
                "\"level\": 4\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5143,\n" +
                "\"time\": 543,\n" +
                "\"level\": 5\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5145,\n" +
                "\"time\": 769,\n" +
                "\"level\": 6\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5143,\n" +
                "\"time\": 869,\n" +
                "\"level\": 7\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 131,\n" +
                "\"hero_id\": 86,\n" +
                "\"item_0\": 44,\n" +
                "\"item_1\": 29,\n" +
                "\"item_2\": 0,\n" +
                "\"item_3\": 16,\n" +
                "\"item_4\": 16,\n" +
                "\"item_5\": 16,\n" +
                "\"kills\": 1,\n" +
                "\"deaths\": 3,\n" +
                "\"assists\": 0,\n" +
                "\"leaver_status\": 1,\n" +
                "\"gold\": 2,\n" +
                "\"last_hits\": 5,\n" +
                "\"denies\": 2,\n" +
                "\"gold_per_min\": 115,\n" +
                "\"xp_per_min\": 78,\n" +
                "\"gold_spent\": 1259,\n" +
                "\"hero_damage\": 2616,\n" +
                "\"tower_damage\": 0,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 4,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5448,\n" +
                "\"time\": 107,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5450,\n" +
                "\"time\": 288,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5448,\n" +
                "\"time\": 400,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5450,\n" +
                "\"time\": 447,\n" +
                "\"level\": 4\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"account_id\": 4294967295,\n" +
                "\"player_slot\": 132,\n" +
                "\"hero_id\": 8,\n" +
                "\"item_0\": 11,\n" +
                "\"item_1\": 0,\n" +
                "\"item_2\": 16,\n" +
                "\"item_3\": 16,\n" +
                "\"item_4\": 18,\n" +
                "\"item_5\": 44,\n" +
                "\"kills\": 0,\n" +
                "\"deaths\": 4,\n" +
                "\"assists\": 0,\n" +
                "\"leaver_status\": 2,\n" +
                "\"gold\": 1,\n" +
                "\"last_hits\": 8,\n" +
                "\"denies\": 0,\n" +
                "\"gold_per_min\": 101,\n" +
                "\"xp_per_min\": 89,\n" +
                "\"gold_spent\": 1159,\n" +
                "\"hero_damage\": 1683,\n" +
                "\"tower_damage\": 0,\n" +
                "\"hero_healing\": 0,\n" +
                "\"level\": 5,\n" +
                "\"ability_upgrades\": [\n" +
                "{\n" +
                "\"ability\": 5028,\n" +
                "\"time\": 109,\n" +
                "\"level\": 1\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5027,\n" +
                "\"time\": 310,\n" +
                "\"level\": 2\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5028,\n" +
                "\"time\": 422,\n" +
                "\"level\": 3\n" +
                "},\n" +
                "{\n" +
                "\"ability\": 5027,\n" +
                "\"time\": 609,\n" +
                "\"level\": 4\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "}\n" +
                "]\n" +
                ",\n" +
                "\"season\": 7,\n" +
                "\"radiant_win\": true,\n" +
                "\"duration\": 1000,\n" +
                "\"start_time\": 1362930291,\n" +
                "\"match_id\": 1705566,\n" +
                "\"match_seq_num\": 1646464,\n" +
                "\"tower_status_radiant\": 2047,\n" +
                "\"tower_status_dire\": 1926,\n" +
                "\"barracks_status_radiant\": 63,\n" +
                "\"barracks_status_dire\": 63,\n" +
                "\"cluster\": 181,\n" +
                "\"first_blood_time\": 98,\n" +
                "\"lobby_type\": 0,\n" +
                "\"human_players\": 10,\n" +
                "\"leagueid\": 0,\n" +
                "\"positive_votes\": 2,\n" +
                "\"negative_votes\": 0,\n" +
                "\"game_mode\": 1\n" +
                "}\n" +
                "}\n"));

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
