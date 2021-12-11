package co.edu.unab.myapplication.repository;

import android.app.Person;

import java.util.List;

import co.edu.unab.myapplication.models.Persona;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetPersona {
    @GET("personas/{id}")
    Call<Persona> readPersona(@Path("id") Long id);
}
