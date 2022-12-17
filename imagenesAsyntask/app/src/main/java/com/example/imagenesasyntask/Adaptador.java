package com.example.imagenesasyntask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreCel, Propiedades;
        ImageView imgCelular;

        public ViewHolder(View itemView){
            super(itemView);
            nombreCel=(TextView)itemView.findViewById(R.id.tvNombre);
            Propiedades =(TextView)itemView.findViewById(R.id.tvPropiedades);
            imgCelular =(ImageView)itemView.findViewById(R.id.iconImageView);


        }
    }
    public List<CelularesModelo> celularesLista;
    public Adaptador(List<CelularesModelo>celularesLista){

        this.celularesLista=celularesLista;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_celular, parent, false);
        ViewHolder viewHolder= new ViewHolder(view);

        return viewHolder;
    }

    public void onBindViewHolder( ViewHolder holder, int position){
        holder.nombreCel.setText(celularesLista.get(position).getNombreCel());
        holder.Propiedades.setText(celularesLista.get(position).getPropiedades());
        holder.imgCelular.setImageBitmap(celularesLista.get(position).imgCelular);
    }

    public int getItemCount(){
        return celularesLista.size();

    }

}
