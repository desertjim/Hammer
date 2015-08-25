import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import hammer.jimbaca.net.hammer.CurlBuilder;

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
}
