package org.wrapdota.server;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.wrapdota.game.DotaMatch;

import java.io.*;
import java.util.List;

public class SteamServer {

    String API_KEY;
    String TEST_API_URL = "IDOTA2Match_205790/";
    String API_URL = "IDOTA2Match_570/";
    String TEST_METHOD = "GetMatchHistory";
    HttpClient client;
    String STEAM_API_URL = "https://api.steampowered.com/";

    public SteamServer(String API_KEY, HttpClient client) {
        this.API_KEY = API_KEY;
        this.client = client;

    }

    public List<DotaMatch> getLatestMatches() throws IOException {

        String URLToGetLatestMatches = STEAM_API_URL + TEST_API_URL + "GetMatchHistory" + "/V001/?key=" + API_KEY;
        HttpGet request = new HttpGet(URLToGetLatestMatches);
        HttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

        StringWriter stringWriter = new StringWriter();
        IOUtils.copy(inputStreamReader, stringWriter);



        return null;
    }
}
