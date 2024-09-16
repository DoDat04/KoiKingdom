/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import koikd.google.GooglePojo;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author Do Dat
 */
public class GoogleUtils {
    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(ConstantsUtils.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", ConstantsUtils.GOOGLE_CLIENT_ID)
                        .add("client_secret", ConstantsUtils.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", ConstantsUtils.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", ConstantsUtils.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").getAsString();
        return accessToken;
    }

    public static GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = ConstantsUtils.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GooglePojo googlePojo = new Gson().fromJson(response, GooglePojo.class);
        return googlePojo;
    }
}
