package com.example.hotelmolinos.adaptador;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.hotelmolinos.R;

public class GaleriaFotosAdaptador extends BaseAdapter {

    private Context mContext;
    //variable pública para mostrar las imágenes
    public int [] imageArray={

            R.drawable.vistasterraza,
            R.drawable.vistaspalace,
            R.drawable.vistasalhambra,
            R.drawable.recepcion,
            R.drawable.desayuno,
            R.drawable.hotelmolinosportada,
            R.drawable.fachadaprincipal,
            R.drawable.habitaciontwin,
            R.drawable.banotwin,
            R.drawable.doble,
            R.drawable.habitacionmatrimonio,
            R.drawable.habitaciontriple,
            R.drawable.cuadruple,
            R.drawable.banera,
            R.drawable.parking,

    };
    //Constructor para esta clase tomando el contexto

    public GaleriaFotosAdaptador(Context mContext) {
        this.mContext = mContext;
        Log.d("GaleriaFotosAdaptador", "Tamaño del array de imágenes: " + imageArray.length); // Verificar el tamaño del array
    }

    @Override
    public int getCount() {
        //modificamos el getCount para que retorne lo que hay en el array
        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        //modificamos el getItem para que retorne la porsicion
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //este getView es el que mostrará las imágenes
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams( 340, 350 ));
        return imageView;
    }
}

