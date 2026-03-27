/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Arbol;


import Core.CursoManager;
import Core.Nodo;
import java.util.ArrayList;
import java.time.Instant;
/**
 *
 * @author Esteban
 */
public class AVL {              //todos los metodos con "Aux" son metodos que no se ejecutan directamente,
    private Nodo raiz;
    private static AVL instance; //sino por su version sin el "Aux"

    public static AVL getInstance() {
        if (instance == null) {
            instance = new AVL();

        }
        return instance;
    }
        
    public static int alturaA(Nodo raiz){
        if(raiz == null)
           return 0;
        return 1+Math.max(alturaA(raiz.getLeft()), alturaA(raiz.getRight()));
    }
    
    public String porNiveles(Nodo raiz){
        if(raiz == null){
            return "El arbol está vacio";
        }
        ArrayList<Nodo> queue = new ArrayList<>();
        
        queue.add(raiz);
 
        return porNivelesAux(queue, "");
        
    }
    
    public String porNivelesAux(ArrayList<Nodo> queue, String rec){
        if(queue.isEmpty())
            return rec;
        Nodo p = queue.remove(0);
        
        rec += p.getId() + ", ";
        
        if(p.getLeft() != null){
            queue.add(p.getLeft());
        }
        if(p.getRight() != null){
            queue.add(p.getRight());
        }
        return porNivelesAux(queue, rec);
    }
    
public Nodo balancear(Nodo raiz){

    if(raiz == null) return null;

    int fb = raiz.factorbalanceo();

    // 🔵 Derecha pesada
    if(fb > 1){
        System.out.println("me");
        if(raiz.getRight() != null && raiz.getRight().factorbalanceo() >= 0){
            return Rotaciones.RSI(raiz); // RR
        }
        if(raiz.getRight() != null && raiz.getRight().factorbalanceo() < 0){
            return Rotaciones.RDDI(raiz); // RL
        }
    }

    // 🔵 Izquierda pesada
    if(fb < -1){
        System.out.println("you");
        if(raiz.getLeft() != null && raiz.getLeft().factorbalanceo() <= 0){
            return Rotaciones.RSD(raiz); // LL
        }
        if(raiz.getLeft() != null && raiz.getLeft().factorbalanceo() > 0){
            return Rotaciones.RDID(raiz); // LR
        }
    }

    return raiz;
}
    
    //getters 

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
}
