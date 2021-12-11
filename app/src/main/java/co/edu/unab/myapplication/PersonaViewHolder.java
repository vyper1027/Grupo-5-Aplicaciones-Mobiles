package co.edu.unab.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonaViewHolder extends RecyclerView.ViewHolder {
    private TextView nombre;
    private TextView edad;
    private TextView peso;
    private TextView estatura;
    private TextView imc;
    private TextView basal;
    private TextView sexo;

    public PersonaViewHolder(@NonNull View itemView) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.name_input_adapter);
        edad = (TextView) itemView.findViewById(R.id.edad_input_adapter);
        peso = (TextView) itemView.findViewById(R.id.peso_input_adapter);
        estatura = (TextView) itemView.findViewById(R.id.estatura_input_adapter);
        imc = (TextView) itemView.findViewById(R.id.resultado_adapter);
        basal = (TextView) itemView.findViewById(R.id.resultadoBasal_adapter);
        sexo = (TextView) itemView.findViewById(R.id.sex_spinner_adapter);
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getEdad() {
        return edad;
    }

    public TextView getPeso() {
        return peso;
    }

    public TextView getEstatura() {
        return estatura;
    }

    public TextView getImc() {
        return imc;
    }

    public TextView getBasal() {
        return basal;
    }

    public TextView getSexo() { return sexo; }
}





