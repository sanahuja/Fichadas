package com.fichadas.fichada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fichadas.clasesdb.FichadaOriginal;

/**
 * Created by informatica on 14/01/16.
 */
public class AdaptadorGridSemana  extends BaseAdapter {

    private Context context;

    public AdaptadorGridSemana(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return FichadaOriginal.ITEMS.length;
    }

    @Override
    public FichadaOriginal getItem(int position) {
         return FichadaOriginal.ITEMS[position];

    }

    @Override
    public long getItemId(int position) {
         return getItem(position).getCodigo();

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;


        if (view == null)
        {
            holder= new ViewHolder();
           // view = View.inflate(context , R.layout.grid_item, null);

             LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
            holder.Imagen   = (ImageView) view.findViewById(R.id.tImagen);
            holder.Codigo   = (TextView) view.findViewById(R.id.tCodigo);
            holder.Entrada  = (TextView) view.findViewById(R.id.tEntrada);
            holder.Fechada  = (TextView) view.findViewById(R.id.tFecha);
            holder.Extra    = (TextView) view.findViewById(R.id.tExtra);

            view.setTag(holder);

        } else
        {
            holder = (ViewHolder) view.getTag();
        }

             final FichadaOriginal item = getItem(position);

        holder.Imagen.setImageResource( item.getIdDrawable() );
        holder.Codigo.setText(String.valueOf(item.getCodigo()));
        holder.Entrada.setText(String.valueOf(item.getEntSal()));
        holder.Fechada.setText(item.getFechaHora());
        holder.Extra.setText(String.valueOf(item.getExtra()));


        return view;
    }

    public class ViewHolder
    {
        public ImageView Imagen;
        public TextView  Codigo;
        public TextView  Entrada;
        public TextView  Fechada;
        public TextView  Extra;

    }
}