package hammer.jimbaca.net.hammer;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

public class CurlBuilder {

    String method;
    String url;
    Headers headers;
    RequestBody body;

    public CurlBuilder(Interceptor.Chain chain) {
        Request request = chain.request();
        method = request.method();
        url = request.urlString();
        headers = request.headers();
        body = request.body();
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append("curl ");
        builder.append("-X " + method + " ");

        if (body != null && body.contentType() != null) {
            builder.append("-H " + body.contentType().toString() + " ");
            builder.append("-d" + body.contentType());
        }

        if (headers != null && headers.size() > 0) {
            // Add headers here
        }



        builder.append(url);

        return builder.toString();
    }
}
