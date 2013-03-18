package org.wrapdota.server;

import com.google.gson.Gson;

public class JsonResponse {
    String result;

    public ResponseResult get(){
       return new Gson().fromJson(result, ResponseResult.class);
    }
}
