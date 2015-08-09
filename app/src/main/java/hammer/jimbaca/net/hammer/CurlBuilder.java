package hammer.jimbaca.net.hammer;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;

public class CurlBuilder {

    String method;
    String url;
    Headers headers;

    public CurlBuilder(Interceptor.Chain chain) {
        Request request = chain.request();
        method = request.method();
        url = request.urlString();
        headers = request.headers();
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append("curl ");

        if(headers != null && headers.size() > 0){
            // Add headers here
        }

        builder.append("-X " + method + " ");
        builder.append(url);

        return builder.toString();
    }
}
