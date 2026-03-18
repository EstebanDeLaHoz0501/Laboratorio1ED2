/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Arbol;

import Core.CursoManager;
import Core.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class MetodosPorId {
    
    public static Nodo insertar(Nodo raiz, int id){
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
    
    public static ArrayList<Nodo> search(Nodo root, int id){
        double metrica = CursoManager.getSatisfaction(id);
        return searchAux(metrica, root, null, null);
    }
    public static ArrayList<Nodo> searchAux(double metrica, Nodo p, Nodo pad, Nodo abue){
        ArrayList<Nodo> lista = new ArrayList<>();
        if(p==null){
            lista.add(null);               
            lista.add(pad);
            lista.add(abue);
            lista.add(null);
            return lista;
        }
        if(p.metrica==metrica){
            lista.add(p);
            lista.add(pad);
            lista.add(abue);
            Nodo tio = null;
            if(abue != null){
                if(abue.left==pad){
                    tio = abue.right;
                }else{
                    tio = abue.left;
                }
            }
            lista.add(tio);
            return lista;
        }
        if(p.metrica>metrica){
            return searchAux(metrica, p.left, p, pad);
        }else{
            return searchAux(metrica, p.right, p, pad);
        }
    }
    public static boolean delete(Nodo root, int id){
        Nodo p = (Nodo) search(root, id).get(0);
        Nodo pad = (Nodo) search(root, id).get(1);
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
            return true;
        }
        return false;
    }
    public static ArrayList sus(Nodo node){
        ArrayList<Nodo> lista = new ArrayList<>();
        Nodo p = node.right;
        Nodo pad = node;
        while(p.left!=null){
            p = p.left;
            pad = p;
        }
        lista.add(pad);
        lista.add(p);
        lista.add(p.right);
        return lista;
    }
}
