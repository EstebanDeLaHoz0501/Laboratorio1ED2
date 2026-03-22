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
    public int id;
    public String title;
    public Nodo left = null;
    public Nodo right = null;
    public double metrica;
    public Curso curso;
    
    //Si no existe el curso, no se puede crear el nodo
    public Nodo(Curso curso) {
        this.curso = curso;
        
        this.id = curso.getId();
        
        this.title = CursoManager.getTitle(id);
        this.metrica = CursoManager.getSatisfaction(id);
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
        if(raiz==this){
            return 0;
        }
        return 1 + AVL.alturaA(this.right)-AVL.alturaA(this.left);
    }
}
