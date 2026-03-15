/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Arbol;

import Core.Curso;
import Core.CursoManager;
import Core.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class AVL {
    Nodo raiz;

    public AVL(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public Nodo insertar(Nodo raiz, int id){
        double metrica = CursoManager.getSatisfaction(id);
        if(raiz== null){
            return new Nodo(id);
        }
        if(raiz.metrica < metrica){
            raiz.right = insertar(raiz.right, id);
        }
        if(raiz.metrica < metrica){
            raiz.left = insertar(raiz.left, id);
        }
        return raiz;
    }

    
    
}
