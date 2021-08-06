package com.nickrexrode.web.request;

import com.nickrexrode.web.response.TestResponse;

public final class TestRequest extends Request<TestResponse> {

    public TestRequest() {
        super("https://americas.api.riotgames.com/lol/match/v5/matches/NA1_3885044733?api_key=apikey");
    }

    @Override
    public Class<TestResponse> getResponseClass() {
        return TestResponse.class;
    }
}
