package com.example.actividadlabcorte2.ui.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadlabcorte2.R;

import java.util.List;

public class CustomAdapterPosts extends RecyclerView.Adapter<CustomAdapterPosts.ViewHolder> {

    List<Posts> List;
    public seleccion seleccion;
    Context context;
    public CustomAdapterPosts(List<Posts> list, Context context, seleccion seleccion){
        List = list;
        this.context= context;
        this.seleccion=seleccion;
    }


    @NonNull
    @Override
    public CustomAdapterPosts.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterPosts.ViewHolder holder, int position) {
        Posts Objvacunacion = List.get(position);
        holder.nombre.setText(List.get(position).getMuni_nombre());
        holder.sedenombre.setText(List.get(position).getSede_nombre());
        holder.direccion.setText(List.get(position).getDireccion());
        holder.telefono.setText(List.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public interface seleccion{
        void seleccion(Posts objvacunacion);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre;
        public TextView  sedenombre;
        public TextView  direccion;
        public TextView  telefono;
        public Button guardar;

        public ViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.textSede_nombre);
            sedenombre = (TextView) view.findViewById(R.id.textSede_nombre);
            direccion = (TextView) view.findViewById(R.id.textDireccion);
            telefono = (TextView) view.findViewById(R.id.textTelefono);
            guardar  = (Button) view.findViewById(R.id.guardar);
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seleccion.seleccion(List.get(getAdapterPosition()));
                }
            });
        }
    }
}