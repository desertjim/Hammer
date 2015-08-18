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
    String command = "curl";
    String methodOption = "-X";
    String headerOption = "-H";
    String dataOption = "-d";

    public CurlBuilder(Interceptor.Chain chain) {
        Request request = chain.request();
        method = request.method();
        url = request.urlString();
        headers = request.headers();
        body = request.body();
    }

    public CurlBuilder(){

    }

    public void setMethod(String method){
        this.method = method;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setRequestBody(RequestBody body){
        this.body = body;
    }

    public void setQuoteCharacter(String character){
        this.quoteCharacter = character;
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

        appendArgs(command);
        appendArgs(methodOption, method);

        if (body != null && body.contentType() != null) {
            appendSingleArgPair(headerOption, body.contentType().toString());
            appendSingleArgPair(dataOption, body.toString());
        }

        if (headers != null && headers.size() > 0) {
            // Add headers here
        }

        appendArgs(url);

        return builder.toString();
    }
}
