package co.edu.unab.myapplication;

import static java.lang.Float.parseFloat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import co.edu.unab.myapplication.models.IMCPersona;


public class RegActivity extends AppCompatActivity {
        ArrayList<IMCPersona> personas = new ArrayList<IMCPersona>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.regs_layout);
                receiveData();
        }
        public void receiveData(){
                JSONObject mJsonObject = null;
                try {
                mJsonObject = new JSONObject(getIntent().getStringExtra("registros"));
                JSONArray regs = mJsonObject.getJSONArray("regs");

                for(int x = 1; x < regs.length(); x++){
                JSONObject elemento = regs.getJSONObject(x);
                personas.add(
                    new IMCPersona(
                        elemento.getString("nombre"),
                        Integer.parseInt(elemento.getString("edad")),
                        parseFloat(elemento.getString("peso")),
                        parseFloat(elemento.getString("estatura")),
                        parseFloat(elemento.getString("imc")),
                        parseFloat(elemento.getString("basal")),
                        elemento.getString("sexo")
                    )
                );
                }

                } catch (JSONException e) {
                e.printStackTrace();
                }
                PersonaAdapter adaptador = new PersonaAdapter(this, R.layout.persona_custom_adapter, personas);
                RecyclerView registros = (RecyclerView) findViewById(R.id.lista_registros);
                registros.setAdapter(adaptador);
                registros.setLayoutManager(new LinearLayoutManager(this));
                registros.setHasFixedSize(true);
        }
}