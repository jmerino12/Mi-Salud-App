package com.jonathan.proyectopit2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectopit2.R;
import com.jonathan.proyectopit2.model.Presion;

import java.util.ArrayList;

public class RegistrosAdapter extends RecyclerView.Adapter <RegistrosAdapter.ViewHolder>{
    private int resource;
    private ArrayList<Presion> presionArrayList;

    public RegistrosAdapter(ArrayList<Presion> presionArrayList, int resource){
        this.presionArrayList = presionArrayList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Presion presion = presionArrayList.get(position);
        holder.textViewMensaje.setText(presion.getAdicionales());
        holder.txtpeso.setText(presion.getPeso());
        holder.txtfecha.setText(presion.getFecha());
        holder.txtdiastolica.setText(presion.getPresionDiastolica());
        holder.txtSistolica.setText(presion.getPresionSistolica());
        holder.txthora.setText(presion.getHora());

    }

    @Override
    public int getItemCount() {
        return presionArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewMensaje,txtfecha,txtpeso,txtSistolica,txtdiastolica,txthora;
        public View view;

        public ViewHolder(View view){
            super(view);
            this.view =view;
            this.txthora = view.findViewById(R.id.hora);
            this.txtfecha = view.findViewById(R.id.fecha);
            this.txtpeso = view.findViewById(R.id.txtPeso);
            this.txtdiastolica = view.findViewById(R.id.txtDiastolica);
            this.txtSistolica = view.findViewById(R.id.txtSistolica);
            this.textViewMensaje = view.findViewById(R.id.txtAdicionalesdatos);
        }
    }
}
