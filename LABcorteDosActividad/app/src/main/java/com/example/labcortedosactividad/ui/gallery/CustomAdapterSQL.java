package com.example.labcortedosactividad.ui.gallery;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labcortedosactividad.R;
import com.example.labcortedosactividad.SQLlite.DatosSQL;

import java.util.List;

public class CustomAdapterSQL extends RecyclerView.Adapter<CustomAdapterSQL.ViewHolder> {

    List<DatosSQL> list;

    public CustomAdapterSQL(List<DatosSQL> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public com.example.labcortedosactividad.ui.gallery.CustomAdapterSQL.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sql,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.labcortedosactividad.ui.gallery.CustomAdapterSQL.ViewHolder holder, int position) {
        DatosSQL datos= list.get(position);
        holder.depa_nombre.setText(list.get(position).getDepa_nombre());
        holder.muni_nombre.setText(list.get(position).getDepa_nombre());
        holder.sede_nombre.setText(list.get(position).getSede_nombre());
        holder.direccion.setText(list.get(position).getDireccion());
        holder.telefono.setText(list.get(position).getTelefono());
        holder.email.setText(list.get(position).getEmail());
        holder.naju_nombre.setText(list.get(position).getNaju_nombre());
        holder.fecha_corte_reps.setText(list.get(position).getFecha_corte_reps());
    }

    @Override
    public int getItemCount() { return (list.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView  depa_nombre;
        public TextView  muni_nombre;
        public TextView  sede_nombre;
        public TextView  direccion;
        public TextView  telefono;
        public TextView  email;
        public TextView  naju_nombre;
        public TextView  fecha_corte_reps;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            depa_nombre = (TextView) itemView.findViewById(R.id.textViewDepartamento);
            muni_nombre = (TextView) itemView.findViewById(R.id.textViewMuniNombre);
            sede_nombre = (TextView) itemView.findViewById(R.id.textViewSedeNombre);
            direccion = (TextView) itemView.findViewById(R.id.textViewDireccion);
            telefono = (TextView) itemView.findViewById(R.id.textViewTelefono);
            email = (TextView) itemView.findViewById(R.id.textViewEmail);
            naju_nombre = (TextView) itemView.findViewById(R.id.textViewNajuNombre);
            fecha_corte_reps = (TextView) itemView.findViewById(R.id.textViewFechaCorte);
        }
    }
}
