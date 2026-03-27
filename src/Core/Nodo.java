/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import Core.Arbol.AVL;
import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class Nodo {
    //metrica == satisfaccion
    private int id;
    private String title;
    private Nodo left = null;
    private Nodo right = null;
    private double metrica;
    private Curso curso;
    
    //Si no existe el curso, no se puede crear el nodo
    public Nodo(Curso curso) {
        this.curso = curso;
        
        this.id = curso.getId();
        
        this.title = CursoManager.getTitle(id);
        this.metrica = curso.getSatisfaction();
    }
    
    /*public ArrayList<Object> Informacion(){    //Para obtener los datos de la lista resultante, se deben castear
        ArrayList<Object> datos = new ArrayList<>();      
        for(Curso curso:CursoManager.cursos){
            if(curso.id == this.id){
                datos.add(curso.getId());
                datos.add(curso.title);
                datos.add(curso.url);
                datos.add(curso.rating);
                datos.add(curso.num_reviews);
                datos.add(curso.num_published_lectures);
                datos.add(curso.created);
                datos.add(curso.last_update_date);
                datos.add(curso.duration);
                datos.add(curso.instructors_id);
                datos.add(curso.positive_reviews);
                datos.add(curso.negative_reviews);
                datos.add(curso.neutral_reviews);
                datos.add(curso.satisfaction); 
            }
        }
        return datos;
    }*/
    
    public int factorbalanceo(){
        return AVL.alturaA(this.right)-AVL.alturaA(this.left); 
    }
    
    public int nivelNodo(Nodo raiz){
    return nivelAux(raiz, this, 0);
}

    private int nivelAux(Nodo actual, Nodo objetivo, int nivel){
        if(actual == null) return -1;

        if(actual == objetivo) return nivel;

        int izq = nivelAux(actual.getLeft(), objetivo, nivel + 1);
        if(izq != -1) 
           return izq;

        return nivelAux(actual.getRight(), objetivo, nivel + 1);
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Nodo getLeft() {
        return left;
    }

    public Nodo getRight() {
        return right;
    }

    public double getMetrica() {
        return metrica;
    }

    public Curso getCurso() {
        return curso;
    }
    
    //SETTERS

    public void setCurso(Curso curso) {
        this.curso = curso;
        this.id = curso.getId();
        
        this.title = CursoManager.getTitle(id);
        this.metrica = curso.getSatisfaction();
    }

    public void setLeft(Nodo left) {
        this.left = left;
    }

    public void setRight(Nodo right) {
        this.right = right;
    }

    public void setMetrica(double metrica) {
        this.metrica = metrica;
    }
    public String infobasica(){
        String info  = "===== CURSO =====\n" +
                "ID: " + id + "\n" +
                "Título: " + title + "\n" +
                "Satisfacción: " + metrica + "\n" +
                "=================\n";
        return info;
    }
}
