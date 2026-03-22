/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Arbol;


import Core.Nodo;
import java.util.ArrayList;
import java.time.Instant;
/**
 *
 * @author Esteban
 */
public class AVL {              //todos los metodos con "Aux" son metodos que no se ejecutan directamente,
    Nodo raiz;                  //sino por su version sin el "Aux"

    public AVL(Nodo raiz) {
        this.raiz = raiz;
    }
        
    public static int alturaA(Nodo raiz){
        if(raiz == null)
           return 0;
        return 1+Math.max(alturaA(raiz.getLeft()), alturaA(raiz.getRight()));
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
        //cambien eso, que aparezca en la interfaz
        //System.out.println(p.title);
        if(p.getLeft() != null){
            queue.add(p.getLeft());
        }
        if(p.getRight() != null){
            queue.add(p.getRight());
        }
        porNivelesAux(queue);
    }
    
    public static Nodo balancear(Nodo raiz){
        if(raiz == null){
            return null;
        }
        raiz.setLeft(balancear(raiz.getLeft()));
        raiz.setRight(balancear(raiz.getRight()));

        
        if(raiz.factorbalanceo()>1 && raiz.getRight().factorbalanceo()>=0)
            return Rotaciones.RSI(raiz);
        
        if(raiz.factorbalanceo()<-1 && raiz.getLeft().factorbalanceo()<=0)
            return Rotaciones.RSD(raiz);      
        
        if(raiz.factorbalanceo()>1 && raiz.getRight().factorbalanceo()<0)
            return Rotaciones.RDDI(raiz);
        
        if(raiz.factorbalanceo()<-1 && raiz.getLeft().factorbalanceo()>0)
            return Rotaciones.RDID(raiz);       
        

        return raiz;
    }
    
}
