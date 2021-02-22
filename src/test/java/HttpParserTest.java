import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpParserTest extends TestCase {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass(){
        httpParser = new HttpParser();
    }

    @Test
    public void testParseHttpRequest() {
        httpParser.parseHttpRequest(generateValidTestCase());
    }
    
    private InputStream generateValidTestCase(){
        String rawInput = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8\r\n" +
                "Cookie: username-localhost-8888=\"2|1:0|10:1610562947|23:username-localhost-8888|44:OTllYWE3NmE2MzFjNDBkNmFhODU1NjE5MTExNTY1MzQ=|2d3a022379f057d26ccc35ef0d68579c4937e1785a04b644d5ccaeae9631fb72\"; _xsrf=2|ea54392b|3959dc63b4d8ce53a404c7807b28172d|1610562947";
        InputStream inputStream = new ByteArrayInputStream(
                rawInput.getBytes(StandardCharsets.US_ASCII
                ));
        return inputStream;
    }
}