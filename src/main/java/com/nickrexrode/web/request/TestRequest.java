package com.nickrexrode.web.request;

import com.nickrexrode.web.response.TestResponse;

public final class TestRequest extends Request<TestResponse> {

    public TestRequest() {
        super("https://americas.api.riotgames.com/lol/match/v5/matches/NA1_3885044733?api_key=RGAPI-585d9107-cc50-44ff-8ff5-ed75e5b62c4b");
    }

    @Override
    public Class<TestResponse> getResponseClass() {
        return TestResponse.class;
    }
}
