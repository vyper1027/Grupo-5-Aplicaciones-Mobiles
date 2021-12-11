package co.edu.unab.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import co.edu.unab.myapplication.models.Persona;
import co.edu.unab.myapplication.models.Usuario;
import co.edu.unab.myapplication.network.BackendLogin;
import co.edu.unab.myapplication.pojo.LoginResponse;
import co.edu.unab.myapplication.repository.GetPersona;
import co.edu.unab.myapplication.repository.GetUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText userInput;
    private EditText passwordInput;
    private TextView loginError;
    String isAdmin = "";
    String userId;
    String userName;
    Long idlogin;
    Persona data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = (EditText) findViewById(R.id.name_input);
        passwordInput = (EditText) findViewById(R.id.textPassword);
        btnLogin = (Button) findViewById(R.id.login_button);
        loginError = (TextView) findViewById(R.id.login_error);
        GetPersona interfaceLoginUser = BackendLogin.getClient().create(GetPersona.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginError.setVisibility(View.INVISIBLE);
                idlogin = Long.valueOf(userInput.getText().toString());
                Call<Persona> llamado = interfaceLoginUser.readPersona(idlogin);

                llamado.enqueue(new Callback<Persona>() {
                    @Override
                    public void onResponse(Call<Persona> call, Response<Persona> response) {
                        try {
                            data = response.body();
                            if(data == null) {
                                loginError.setVisibility(View.VISIBLE);
                            }else{
                                Intent k = new Intent(MainActivity.this, ImcActivity.class);
                                isAdmin = data.getEsAdmin().toString();
                                userName = data.getNombre() + " " + data.getApellido();
                                userId = data.getId().toString();
                                k.putExtra("isAdmin", isAdmin);
                                k.putExtra("nombre", userName);
                                k.putExtra("id", userId);
                                MainActivity.this.startActivity(k);
                            }
                            Log.d("onResponse----logs", String.valueOf(data));
                        }catch (Exception e){
                            loginError.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onFailure(Call<Persona> call, Throwable t) {
                        Log.d("onFailure----logs", String.valueOf(t));
                        loginError.setVisibility(View.VISIBLE);
                    }
                });


            }
        });
    }

}