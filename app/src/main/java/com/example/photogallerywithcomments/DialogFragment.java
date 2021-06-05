package com.example.photogallerywithcomments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DialogFragment extends androidx.fragment.app.DialogFragment {
    View view;
    TextView titulo;
    RecyclerView recyclerViewGaleria;
    ImageView imagen;
    ArrayList<Menu> listaMenu;
    RecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dialog, container, false);
        titulo=(TextView)view.findViewById(R.id.titulo);
        imagen=(ImageView) view.findViewById(R.id.imageView);
        recyclerViewGaleria=(RecyclerView) view.findViewById(R.id.galeria);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        recyclerViewGaleria.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        listaMenu = new Menu().listaMenu();
        adapter = new RecyclerAdapter(listaMenu, new RecyclerAdapter.OnClickRecycler() {
            @Override
            public void OnclickItemRecycler(Menu menu) {
                Glide.with(getContext()).load(menu.getIdImagen()).into(imagen);
                titulo.setText(menu.getTitulo());
            }
        });
        recyclerViewGaleria.setAdapter(adapter);
        return view;
    }
}