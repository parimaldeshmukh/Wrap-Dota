package org.wrapdota.server;

import com.google.gson.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BasicHttpEntity;
import org.wrapdota.deserializer.DotaMatchDeserializer;
import org.wrapdota.model.DotaMatch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SteamServer {

    String API_KEY;
    HttpClient client;
    String TEST_API_URL = "IDOTA2Match_205790/";
    String API_URL = "IDOTA2Match_570/";
    String TEST_METHOD = "GetMatchHistory";

    String STEAM_API_URL = "https://api.steampowered.com/";

    Gson dotaMatchGson;

    public SteamServer(String API_KEY, HttpClient client) {
        this.API_KEY = API_KEY;
        this.client = client;


        GsonBuilder dotaMatchGsonBuilder = new GsonBuilder();
        dotaMatchGsonBuilder.registerTypeAdapter(DotaMatch.class, new DotaMatchDeserializer());

        dotaMatchGson = dotaMatchGsonBuilder.create();

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

        String responseString = stringWriter.toString();

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonResponseElement = jsonParser.parse(responseString);

        JsonObject jsonResponseObject = jsonResponseElement.getAsJsonObject();

        JsonElement resultJsonElement = jsonResponseObject.get("result");

        JsonObject resultJsonObject = resultJsonElement.getAsJsonObject();

        Integer status = resultJsonObject.get("status").getAsInt();
        Integer numberOfResults = resultJsonObject.get("num_results").getAsInt();
        Integer totalResults = resultJsonObject.get("total_results").getAsInt();
        Integer resultsRemaining = resultJsonObject.get("results_remaining").getAsInt();
        JsonArray matches = resultJsonObject.get("matches").getAsJsonArray();

        List<DotaMatch> dotaMatches = new ArrayList<DotaMatch>();
        for(int index = 0; index < matches.size() ; index++){
            dotaMatches.add(dotaMatchGson.fromJson(matches.get(index),DotaMatch.class));
        }

        return dotaMatches;
    }
}