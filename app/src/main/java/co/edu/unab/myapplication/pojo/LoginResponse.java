package co.edu.unab.myapplication.pojo;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }
}
