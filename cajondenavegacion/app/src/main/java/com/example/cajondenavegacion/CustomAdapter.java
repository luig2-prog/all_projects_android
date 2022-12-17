package com.example.cajondenavegacion;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, descripcion;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            titulo = (TextView) view.findViewById(R.id.textTituloLista);
            descripcion = (TextView) view.findViewById(R.id.textDescripcionLista);

        }
    }

    public List<Notas> notasList;

    public CustomAdapter() {

    }

    public CustomAdapter(List<Notas> cancionesList) {
        this.notasList = cancionesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notas item = notasList.get(position);
        holder.titulo.setText(notasList.get(position).getTitulo());
        holder.descripcion.setText(notasList.get(position).getDescripcion());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(holder.itemView.getContext(), Detalles.class);
               intent.putExtra("itemDetalle",item);
               holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notasList.size();
    }

}

