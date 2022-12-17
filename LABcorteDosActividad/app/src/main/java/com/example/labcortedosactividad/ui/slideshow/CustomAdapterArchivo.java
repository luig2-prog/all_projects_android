package com.example.labcortedosactividad.ui.slideshow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labcortedosactividad.R;

import java.util.List;

public class CustomAdapterArchivo extends RecyclerView.Adapter<CustomAdapterArchivo.ViewHolder> {


        public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView horaFechaIngreso;

        public ViewHolder(View view){
            super(view);

            horaFechaIngreso = (TextView) view.findViewById(R.id.textHoras);
        }
    }


    public List<String> infoFechas;


    public CustomAdapterArchivo(){

    }

    public CustomAdapterArchivo(List<String> infroFechas) {
        this.infoFechas = infroFechas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingresos,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String infos = infoFechas.get(position);
        holder.horaFechaIngreso.setText(infoFechas.get(position));

    }

    @Override
    public int getItemCount() {
        return infoFechas.size();
    }


}
