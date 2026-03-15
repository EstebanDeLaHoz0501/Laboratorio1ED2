/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class Nodo {
    public int id;
    public Nodo left;
    public Nodo right;
    public double metrica;
    public int balance;

    public Nodo(int id) {
        this.id = id;
        this.metrica = CursoManager.getSatisfaction(id);
    }
    
    public ArrayList<Object> Información(){
        ArrayList<Object> datos = new ArrayList<>();
        for(Curso curso:CursoManager.cursos){
            if(curso.id == this.id){
                datos.add(curso.id);
                datos.add(curso.title);
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
    }
    public int factorbalanceo(){
        return AlturaA(this.right)-AlturaA(this.left); 
    }
    
    public static int AlturaA(Nodo raiz){
        if(raiz == null)
           return 0;
        return 1+Math.max(AlturaA(raiz.left), AlturaA(raiz.right));
    }
}
