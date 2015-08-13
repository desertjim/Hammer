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
    StringBuilder builder = new StringBuilder();
    String spaceCharacter = " ";
    String quoteCharacter = "'";

    public CurlBuilder(Interceptor.Chain chain) {
        Request request = chain.request();
        method = request.method();
        url = request.urlString();
        headers = request.headers();
        body = request.body();
    }

    private void reset(){
        builder.delete(0, builder.length());
    }

    private void appendArgs(String... args){
        for(String arg: args){
            builder.append(arg);
            builder.append(" ");
        }
    }

    private void appendSingleArgPair(String arg, String argValue){
        builder.append(arg);
        builder.append(spaceCharacter);
        builder.append(quoteCharacter);
        builder.append(argValue);
        builder.append(quoteCharacter);
        builder.append(spaceCharacter);
    }


    public String build() {
        reset();
        
        appendArgs("curl");
        appendArgs("-X", method);

        if (body != null && body.contentType() != null) {
            appendSingleArgPair("-H", body.contentType().toString());
            appendSingleArgPair("-d", body.toString());
        }

        if (headers != null && headers.size() > 0) {
            // Add headers here
        }

        appendArgs(url);

        return builder.toString();
    }
}
