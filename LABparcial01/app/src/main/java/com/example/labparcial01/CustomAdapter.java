package com.example.labparcial01;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre, artista, año;
        private ImageView imagenCancion;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nombre = (TextView) view.findViewById(R.id.textNombre);
            artista = (TextView) view.findViewById(R.id.textArtista);
            año = (TextView) view.findViewById(R.id.textAño);
            imagenCancion = (ImageView) view.findViewById(R.id.imagenCancion);
        }
    }

    public List<Canciones> cancionesList;

    public CustomAdapter() {

    }

    public CustomAdapter(List<Canciones> cancionesList) {
        this.cancionesList = cancionesList;
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
        Canciones item = cancionesList.get(position);
        holder.nombre.setText(cancionesList.get(position).getNombre());
        holder.artista.setText(cancionesList.get(position).getArtista());
        holder.año.setText(cancionesList.get(position).getAño()+"");
        holder.imagenCancion.setImageResource(cancionesList.get(position).getImagenCantante());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), Detalles_canciones.class);
                intent.putExtra("itemDetail",item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cancionesList.size();
    }

}

