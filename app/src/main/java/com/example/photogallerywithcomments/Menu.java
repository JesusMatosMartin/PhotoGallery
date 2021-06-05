package com.example.photogallerywithcomments;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Menu extends Activity {
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
        ArrayList<String> photos = new ArrayList<String>();
        ArrayList<Integer> photoID = new ArrayList<Integer>();
        ArrayList<String> comments = new ArrayList<String>();

        // Lector de XML
        try {
            InputStream is = getAssets().open("photos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element=doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("photo");
            for (int i=0; i<nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    //URL
                    photos.add(getValue("url", element2));
                    // Separacion .jpg
                    String photoSeparation = photos.get(i).replaceAll(".jpg","");
                    photoID.add(getResources().getIdentifier(photoSeparation,"drawable",getPackageName()));
                    Log.v("photo size",""+photoID.size());
                    // Comments
                    Log.v("comment",""+getValue("comment",element2));
                    comments.add(getValue("comment",element2));
                }
            }
        } catch (Exception e) {e.printStackTrace();}

        //Base
        photoID.add(R.drawable.arbol);
        photoID.add(R.drawable.mar);
        comments.add("Arbol en un amanecer a las orillas del mar");
        comments.add("Olas del mar al atardecer");

        for (int i= 0; i<photoID.size();i++){
            menu = new Menu(photoID.get(i),comments.get(i));
            lista.add(menu);
        }
        return lista;
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
