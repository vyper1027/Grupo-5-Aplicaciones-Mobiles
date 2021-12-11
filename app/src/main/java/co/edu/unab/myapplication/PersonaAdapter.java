package co.edu.unab.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.unab.myapplication.models.IMCPersona;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaViewHolder>{
    Context context;
    int layout;
    List<IMCPersona> lista;
    LayoutInflater inflater;
    private Spinner sexo;

    public PersonaAdapter(Context context, int layout, List<IMCPersona> lista){
        this.context=context;
        this.layout=layout;
        this.lista=lista;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(layout, parent, false);
        return new PersonaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        IMCPersona persona = lista.get(position);
        holder.getNombre().setText("Nombre: " + persona.getNombre());
        holder.getEdad().setText("Edad: " + persona.getEdad()+"");
        holder.getPeso().setText("Peso: " + Float.toString(persona.getPeso()));
        holder.getEstatura().setText("Estatura: " + Float.toString(persona.getEstatura()));
        holder.getSexo().setText("Sexo: "+ persona.getSexo());
        holder.getImc().setText("IMC: "+Float.toString(persona.getImc()));
        holder.getBasal().setText( "BASAL: "+Float.toString(persona.getBasal())+" cal / dia");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

}
