/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.saas.facebook;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FacebookSocialNetworkingService Service
 *
 * @author berto
 */

public class FacebookSocialNetworkingService {

    /** Creates a new instance of FacebookSocialNetworkingService */
    public FacebookSocialNetworkingService() {
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch(Throwable th) {}
    }

    /**
     *
     * @param request
     * @param response
     * @param eventInfo
     * @param format
     * @param callback
     * @return an instance of RestResponse
     */
    public static RestResponse eventsCreate(HttpServletRequest request, HttpServletResponse response, String eventInfo, String format, String callback) throws IOException {
        String v = "1.0";
        String method = "facebook.events.create";
        FacebookSocialNetworkingServiceAuthenticator.login(request, response);
        String callId = String.valueOf(System.currentTimeMillis());
        String apiKey = FacebookSocialNetworkingServiceAuthenticator.getApiKey(request, response);
        String sessionKey = FacebookSocialNetworkingServiceAuthenticator.getSessionKey(request, response);
        String sig = FacebookSocialNetworkingServiceAuthenticator.sign(new String[][]{{"api_key", apiKey}, {"session_key", sessionKey}, {"call_id", callId}, {"v", v}, {"event_info", eventInfo}, {"format", format}, {"callback", callback}, {"method", method}});
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"api_key", "" + apiKey + ""}, {"session_key", sessionKey}, {"call_id", callId}, {"sig", sig}, {"v", v}, {"event_info", eventInfo}, {"format", format}, {"callback", callback}, {"method", method}};
        RestConnection conn = new RestConnection("http://api.facebook.com/restserver.php", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
