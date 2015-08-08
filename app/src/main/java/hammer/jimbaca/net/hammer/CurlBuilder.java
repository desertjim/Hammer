package hammer.jimbaca.net.hammer;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;

public class CurlBuilder {
    public CurlBuilder(Interceptor.Chain chain){
        Request request = chain.request();
        request.method();
    }

    public String build(){
        return null;
    }
}
