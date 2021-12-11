package co.edu.unab.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import co.edu.unab.myapplication.models.IMCPersona;

public class ImcActivity extends Activity implements View.OnClickListener {
    private EditText nombreInput;
    private EditText edadInput;
    private EditText pesoInput;
    private EditText tallaInput;
    private Button btnCalculate;
    private Button adminButton;
    private TextView resultadoImc;
    private TextView resultadoBasal;
    private Spinner spinner;
    private Button btnRegistry;
    String userId;
    final static String TAG = "Logs de FireBase";
    ArrayList<IMCPersona> personas = new ArrayList<IMCPersona>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    JSONObject regs = new JSONObject();
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc_activity);
        btnCalculate = (Button) findViewById(R.id.calculate_button);
        btnRegistry = (Button) findViewById(R.id.registry_button);
        btnCalculate.setOnClickListener(ImcActivity.this);
        btnRegistry.setOnClickListener(ImcActivity.this);
        spinner = (Spinner) findViewById(R.id.sex_spinner);
        adminButton = (Button) findViewById(R.id.super_button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        receiveData();
    }

    public void receiveData(){
        String isAdmin;
        isAdmin = getIntent().getStringExtra("isAdmin");
        if(isAdmin.equals("true")){
            Log.v("isAdmin", isAdmin);
            adminButton.setVisibility(View.VISIBLE);
        }
        userId = getIntent().getStringExtra("id");
    }
    public ArrayList<IMCPersona> getValues(){
        return personas;
    }
    @Override
    public void onClick(View v) {
        Log.v("id", String.valueOf(v.getId()));
        if(v.getId() == (int)2131296605){
            try {
                Intent k = new Intent(ImcActivity.this, RegActivity.class);
                String json = regs.toString();
                k.putExtra("registros", json);
                startActivity(k);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }else{
            String nombre;
            int edad;
            float peso;
            float estatura;
            float imc;
            float basal;
            String sexo;

            nombreInput = (EditText) findViewById(R.id.name_input);
            edadInput = (EditText) findViewById(R.id.edad_input);
            pesoInput = (EditText) findViewById(R.id.peso_input);
            tallaInput = (EditText) findViewById(R.id.estatura_input);
            resultadoImc = (TextView) findViewById(R.id.resultado);
            resultadoBasal = (TextView) findViewById(R.id.resultadoBasal);

            try {
                nombre = nombreInput.getText().toString();
                edad = Integer.parseInt(String.valueOf(edadInput.getText()));
                peso = Float.parseFloat(String.valueOf(pesoInput.getText()));
                estatura = Float.parseFloat(String.valueOf(tallaInput.getText()));
                sexo = spinner.getSelectedItem().toString();
                imc = peso/(estatura * estatura);
                estatura = estatura*100;
                if(sexo.equals("Masculino")){
                    basal = (float) (10*peso + 6.25*estatura - 5*edad + 5);
                }else{
                    basal = (float) (10*peso + 6.25*estatura - 5*edad - 161);
                }
                JSONObject reader = new JSONObject();

                reader.put("nombre", nombre);
                reader.put("edad", edad);
                reader.put("peso", peso);
                reader.put("imc", imc);
                reader.put("estatura", estatura);
                reader.put("basal", basal);
                reader.put("sexo", sexo);

                if(flag == false){
                    regs.accumulate("regs", "{}");
                    flag = true;
                }

                regs.accumulate("regs", reader);
                resultadoImc.setText( "IMC: "+ Float.toString(imc) );
                resultadoBasal.setText( "Metabolismo basal: " + Float.toString(basal) + " cal / día");

                nombreInput.setText("");
                edadInput.setText("");
                tallaInput.setText("");
                pesoInput.setText("");

                // Create a new user with a first and last name
                Map<String, Object> registro = new HashMap<>();
                registro.put("nombre", nombre);
                registro.put("edad", edad);
                registro.put("peso", peso);
                registro.put("imc", imc);
                registro.put("estatura", estatura);
                registro.put("basal", basal);
                registro.put("sexo", sexo);
                //registro.put("userID"),

                // Add a new document with a generated ID
                db.collection("registros")
                        .add(registro)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

            }catch (Exception e){
                Log.v("errores", String.valueOf(e));
            }
        }

    }
}
