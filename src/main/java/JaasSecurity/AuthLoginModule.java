package JaasSecurity;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthLoginModule implements LoginModule{

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    private List<String> userGroups;
    public Map<String, ?> sharedState;
    public Map<String, ?> options;
    boolean loginCompleted = false;

    @Override
    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {

        this.subject = subject;
        this.handler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
        System.out.println(subject + " from initialize");
    }

    @Override
    public boolean login() throws LoginException {

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        boolean isVerified;

        try {
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String token = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());

            URL url = new URL("https://oauth2.googleapis.com/tokeninfo?id_token=" + token);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                isVerified = String.valueOf(Boolean.valueOf(GoogleOAuth.getValues(String.valueOf(response), "email_verified"))).equals("true");
            }
            if (name != null && isVerified) {

                System.out.println("Authenticated actually");

                login = name;
                userGroups = new ArrayList<>();
                userGroups.add("user");
                loginCompleted = true;
                return true;
            }

            // If credentials are NOT OK we throw a LoginException
            throw new LoginException("Authentication failed");

        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }

    }

    @Override
    public boolean commit() throws LoginException {
        if (!loginCompleted){
            System.out.println("at the Commit after being failed to login");
            return false;
        }

        userPrincipal = new UserPrincipal(login);
        subject.getPrincipals().add(userPrincipal);

        if (userGroups != null && userGroups.size() > 0) {
            for (String groupName : userGroups) {
                rolePrincipal = new RolePrincipal(groupName);
                subject.getPrincipals().add(rolePrincipal);
            }
        }
        System.out.println(subject + " from commit");

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }
}