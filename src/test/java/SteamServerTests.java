import org.junit.*;
import org.wrapdota.game.DotaMatch;
import org.wrapdota.server.SteamServer;

import java.io.IOException;
import java.util.List;

public class SteamServerTests {

    @Test()
    public void itGetsLatestMatches() throws IOException {

        //given
        SteamServer steamServer = new SteamServer("7C5DA0C928D213C67156C62E56CC1117");

        //when
        List<DotaMatch> matches = steamServer.getLatestMatches();

        //then
        assert (matches.size() == 25);
    }


}
