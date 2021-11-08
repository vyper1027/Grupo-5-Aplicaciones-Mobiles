package co.edu.unab.imc_unab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText nombreInput;
    private EditText edadInput;
    private EditText pesoInput;
    private EditText tallaInput;
    private Button btnCalculate;
    private TextView resultadoImc;
    private TextView resultadoBasal;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = (Button) findViewById(R.id.calculate_button);
        btnCalculate.setOnClickListener(MainActivity.this);
        spinner = (Spinner) findViewById(R.id.sex_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
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
        //IMCDeclaration
        try {
            nombre = nombreInput.getText().toString();
            edad = Integer.parseInt(String.valueOf(edadInput.getText()));
            peso = Float.parseFloat(String.valueOf(pesoInput.getText()));
            estatura = Float.parseFloat(String.valueOf(tallaInput.getText()));
            sexo = spinner.getSelectedItem().toString();
            imc = peso/(estatura * estatura);
            estatura = estatura*100;
            //Log.v("peso", String.valueOf(peso));
            //Log.v("estatura", String.valueOf(estatura));
            //Log.v("edad", String.valueOf(edad));
            if(sexo.equals("Masculino")){
                basal = (float) (10*peso + 6.25*estatura - 5*edad + 5);
                //Log.v("G", "hombre");
            }else{
                basal = (float) (10*peso + 6.25*estatura - 5*edad - 161);
                //Log.v("G", "mujer");
            }
            //Log.v("sexo", String.valueOf(sexo));
            //Log.v("basal", String.valueOf(basal));
            resultadoImc.setText( "IMC: "+ Float.toString(imc) );
            resultadoBasal.setText( "Metabolismo basal: " + Float.toString(basal) + " cal / día");

        }catch (Exception e){
            Log.v("error", String.valueOf(e));
        }
    }
}