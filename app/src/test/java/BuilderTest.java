import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import hammer.jimbaca.net.hammer.CurlBuilder;
import okio.BufferedSink;

@RunWith(MockitoJUnitRunner.class)
public class BuilderTest {

    @Test
    public void testEmptyBuilder() {
        CurlBuilder builder = new CurlBuilder();
        String value = builder.build();
        assert value.equals("curl");
    }

    @Test
    public void testMethodSet() {
        CurlBuilder builder = new CurlBuilder();
        builder.setMethod("POST");
        String value = builder.build();
        assert value.equals("curl -X POST");
    }

    @Test
    public void testUrlSet(){
        CurlBuilder builder = new CurlBuilder();
        builder.setUrl("http://www.google.com");
        String value = builder.build();
        assert value.equals("curl http://www.google.com");
    }

    @Test
    public void testUrlAndMethod(){
        CurlBuilder builder = new CurlBuilder();
        builder.setMethod("POST");
        builder.setUrl("http://www.google.com");
        String value = builder.build();
        assert value.equals("curl -X POST http://www.google.com");
    }

    @Test
    public void testBody(){
        CurlBuilder builder = new CurlBuilder();
        builder.setMethod("POST");
        builder.setUrl("http://www.google.com");
        builder.setRequestBody(new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("application/json");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("{}");
            }
        });
        String value = builder.build();
        assert value.equals("curl -H 'application/json' -d '{}' http://www.google.com");
    }
}
