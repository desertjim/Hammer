package hammer.jimbaca.net.hammer;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;

public class CurlBuilder {

    String method;

    public CurlBuilder(Interceptor.Chain chain){
        Request request = chain.request();
        method = request.method();
    }

    public String build(){
        StringBuilder builder = new StringBuilder();

        return builder.toString();
    }
}
