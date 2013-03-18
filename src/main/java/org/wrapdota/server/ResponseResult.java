package org.wrapdota.server;

import com.google.gson.Gson;
import org.wrapdota.model.DotaMatch;

import java.util.List;

public class ResponseResult {
    private int status;
    private int num_results;
    private int total_results;
    private int results_remaining;

    String matches;

    public List<DotaMatch> getMatches() {
    return new Gson().fromJson(matches, List.class);
    }
}