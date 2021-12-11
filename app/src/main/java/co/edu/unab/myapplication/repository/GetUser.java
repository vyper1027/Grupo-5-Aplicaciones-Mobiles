package co.edu.unab.myapplication.repository;

import co.edu.unab.myapplication.models.Usuario;
import co.edu.unab.myapplication.pojo.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetUser {
    @POST("/api/auth")
    Call<LoginResponse> getLoginResponse(@Body Usuario usuario);
}
