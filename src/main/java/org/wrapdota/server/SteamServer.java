package org.wrapdota.server;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.wrapdota.game.DotaMatch;

import java.io.IOException;
import java.util.List;

public class SteamServer {

    String API_KEY;
    String TEST_API_URL = "IDOTA2Match_205790";
    String API_URL = "IDOTA2Match_570";
    String TEST_METHOD = "GetMatchHistory";
    HttpClient client;
    String STEAM_API_URL = "https://api.steampowered.com/";

    public SteamServer(String API_KEY, HttpClient client) {
        this.API_KEY = API_KEY;
        this.client = client;

    }

    public List<DotaMatch> getLatestMatches() throws IOException {

        HttpGet request = new HttpGet(STEAM_API_URL + TEST_API_URL + "GetMatchHistory" + "/V001/?key=" + API_KEY);
        HttpResponse response = client.execute(request);


        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
