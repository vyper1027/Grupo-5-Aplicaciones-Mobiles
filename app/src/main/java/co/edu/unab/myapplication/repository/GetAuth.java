package co.edu.unab.myapplication.repository;

import co.edu.unab.myapplication.models.IMCPersona;
import co.edu.unab.myapplication.pojo.Autorizacion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetAuth {
    @Headers("Cache-Control: no-cache")
    @POST("auth")
    Call<Autorizacion> getToken(@Body IMCPersona persona);
}
