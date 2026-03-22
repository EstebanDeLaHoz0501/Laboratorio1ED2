/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Arbol;

import static Core.Arbol.MetodosPorId.search;
import static Core.Arbol.MetodosPorId.searchAux;
import static Core.Arbol.MetodosPorId.sus;
import Core.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Esteban
 */

//nota: Metrica == Satisfaccion 

public class MetodosPorMetrica {
    public static ArrayList<Nodo> search(Nodo root, double metrica){
        return searchAux(metrica, root, null, null);
    }
    
    public static boolean delete(Nodo root, double metrica){
        Nodo p = (Nodo) search(root, metrica).get(0);
        Nodo pad = (Nodo) search(root, metrica).get(1);
        if(p!=null){
            if(p.left == null & p.right == null){
                if(pad.left==p){
                    pad.left=null;
                }else{
                    pad.right=null;
                }
            }else if(p.left== null & p.right != null){
                if(pad.left==p){
                    pad.left=p.right;
                }else{
                    pad.right=p.right;
                }
            }else if(p.left!= null & p.right == null){
                if(pad.left==p){
                    pad.left=p.left;
                }else{
                    pad.right=p.left;
                }
            }else{
                Nodo psus = (Nodo) sus(p).get(0);
                Nodo padsus = (Nodo) sus(p).get(1);
                Nodo phijo = (Nodo) sus(p).get(2);
                if(padsus == p){
                    p.metrica = psus.metrica;
                    padsus.right = psus.right;
                }else{
                    if(phijo == null){
                    p.metrica = psus.metrica;
                    padsus.left = null;
                    }else{
                        p.metrica = psus.metrica;
                        padsus.left = phijo;
                    }
                }
            }
            AVL.balancear(root);
            return true;
        }
        return false;
    }
}
