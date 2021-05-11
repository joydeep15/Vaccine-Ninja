import org.joydeep.utils.HttpGet;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HttpTest {

    @Test
    public void httpGetTest() throws IOException {
        String data = HttpGet.sendHttpGet("https://httpbin.org/get");
        Assert.assertFalse(data.isEmpty());
    }


}
