/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Arbol;


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
        
    public static int alturaA(Nodo raiz){
        if(raiz == null)
           return 0;
        return 1+Math.max(alturaA(raiz.left), alturaA(raiz.right));
    }
    
    public void porNiveles(Nodo raiz){
        if(raiz != null){
            return;
        }
        ArrayList<Nodo> queue = new ArrayList<>();
        queue.add(raiz);
        porNivelesAux(queue);
    }
    public void porNivelesAux(ArrayList<Nodo> queue){
        if(queue.isEmpty())
            return;
        Nodo p = queue.removeFirst();
        System.out.println(p.title);
        if(p.left != null){
            queue.add(p.left);
        }
        if(p.right != null){
            queue.add(p.right);
        }
        porNivelesAux(queue);
    }
}
