package oauth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;


public final class GoogleOAuth {


    private static final String CLIENT_ID = "695410172015-vhklf7dmroggoktcdmmhgmvaanmaj2t2.apps.googleusercontent.com";

    private static final String CLIENT_SECRET = "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";

    private static final String CALLBACK_URI = "http://localhost:8080/TomCatChatter_war/app";

    // start google authentication constants
    private static final Iterable<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    // end google authentication constants

    private String stateToken;

    private final GoogleAuthorizationCodeFlow flow;


    public GoogleOAuth() {
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, (Collection<String>) SCOPE).build();

        generateStateToken();
    }

    public String buildLoginUrl() {

        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();

        return url.setRedirectUri(CALLBACK_URI).setState(stateToken).build();
    }

    private void generateStateToken(){

        SecureRandom sr1 = new SecureRandom();

        stateToken = "google;"+sr1.nextInt();

    }

    public String getStateToken(){
        return stateToken;
    }


    public static String getValues(String json, String key) {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < json.length();i++){
            if (json.charAt(i)==key.charAt(0)){
                if (json.startsWith(key, i)){
                    i += key.length()+1;
                    while(json.charAt(i)!=','){
                        value.append(json.charAt(i));
                        i++;
                    }
                    break;
                }
            }
        }
        System.out.println("from the json editor" + value);
        if (key.equals("id_token")){
            return value.substring(2, value.length() - 1);
        }else{
            return value.substring(3, value.length() - 1);
        }
    }
    String accessToken = null;

    public String getUserInfoJson(final String authCode) throws IOException {


        final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
        System.out.println(response);
        accessToken = getValues(String.valueOf(response), "id_token");
        final Credential credential = flow.createAndStoreCredential(response, null);
        final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
        final GenericUrl url = new GenericUrl(USER_INFO_URL);
        final HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setContentType("application/json");
        System.out.println(request.execute().parseAsString());
        return request.execute().parseAsString();

    }
    public String getAccessToken(final String authCode) throws IOException{
        System.out.println(accessToken);
        return accessToken;
    }



}