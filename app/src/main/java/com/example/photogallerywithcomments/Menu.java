package com.example.photogallerywithcomments;

import java.util.ArrayList;

public class Menu {
    private int idImagen;
    private String titulo;

    public Menu(){
        idImagen=0;
        titulo="";
    }

    public Menu(int idImagen,String titulo){
        this.idImagen=idImagen;
        this.titulo=titulo;
    }

    public int getIdImagen(){
        return idImagen;
    }

    public String getTitulo(){
        return titulo;
    }

    public ArrayList<Menu>listaMenu(){
        Menu menu;
        ArrayList<Menu> lista = new ArrayList<Menu>();
        Integer[]IdImagenes = new Integer[]{
                R.drawable.arbol,
                R.drawable.atardecer
        };
        String[]titulos = new String[]{
                "Arbol",
                "Atardecer"
        };
        for (int i= 0; i<IdImagenes.length;i++){
            menu = new Menu(IdImagenes[i],titulos[i]);
            lista.add(menu);
        }
        return lista;
    }
}
