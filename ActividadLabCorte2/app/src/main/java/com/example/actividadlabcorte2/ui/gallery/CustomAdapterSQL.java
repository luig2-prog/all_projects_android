package com.example.actividadlabcorte2.ui.gallery;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadlabcorte2.R;
import com.example.actividadlabcorte2.SQLlite.DatosSQL;

import java.util.List;

public class CustomAdapterSQL extends RecyclerView.Adapter<CustomAdapterSQL.ViewHolder> {
    List<DatosSQL> list;

    public CustomAdapterSQL(List<DatosSQL> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CustomAdapterSQL.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sql,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterSQL.ViewHolder holder, int position) {
        DatosSQL datos= list.get(position);
        holder.direccion.setText(list.get(position).direccion);
        holder.depart.setText(list.get(position).depa_nombre);
        holder.sedenombre.setText(list.get(position).sede_nombre);
    }

    @Override
    public int getItemCount() { return (list.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sedenombre;
        public TextView  direccion;
        public TextView depart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sedenombre = (TextView) itemView.findViewById(R.id.municipio);
            direccion = (TextView) itemView.findViewById(R.id.direccion);
            depart = (TextView) itemView.findViewById(R.id.tvidguardado);
        }
    }
}
