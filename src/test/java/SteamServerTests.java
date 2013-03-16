import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Test;
import org.wrapdota.game.DotaMatch;
import org.wrapdota.server.SteamServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SteamServerTests {

    @Test()
    public void itGetsLatestMatches() throws IOException {

        //given
        HttpClient mockHTTPClient = mock(HttpClient.class);
        HttpResponse response = new BasicHttpResponse(new BasicStatusLine(new HttpVersion(1,1), 200, "test phrase"));


        List<DotaMatch> mockedList = new ArrayList<DotaMatch>();
        for (int count = 0; count < 26; count++) {
            mockedList.add(count, new DotaMatch());
        }


        given(mockHTTPClient.execute(any(HttpGet.class))).willReturn(response);
        SteamServer steamServer = new SteamServer("7C5DA0C928D213C67156C62E56CC1117", mockHTTPClient);

        //when
        List<DotaMatch> matches = steamServer.getLatestMatches();

        //then
//        assert (matches.size() == 25);
    }


}
