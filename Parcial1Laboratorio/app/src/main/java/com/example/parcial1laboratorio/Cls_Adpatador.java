package com.example.parcial1laboratorio;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class Cls_Adpatador extends RecyclerView.Adapter<Cls_Adpatador.ViewHolder> {

    public List<Cls_Producto> productoListado;

    public Cls_Adpatador(List<Cls_Producto> datos_productos) {
        this.productoListado = datos_productos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listado_producto, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cls_Producto item = productoListado.get(position);

        holder.nombre.setText(productoListado.get(position).getNombre());
        holder.descripcion.setText(productoListado.get(position).getDescripcion());
        holder.foto.setImageResource(productoListado.get(position).getFoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalleProducto.class);
                intent.putExtra("detallesp", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productoListado.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre, descripcion;
        private ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.idNombre);
            descripcion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
