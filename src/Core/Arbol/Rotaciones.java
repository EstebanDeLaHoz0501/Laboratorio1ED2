/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Arbol;

import Core.Nodo;

/**
 *
 * @author Esteban
 */
public class Rotaciones {
    public static Nodo RSI(Nodo node){
        Nodo aux = node.getRight();
        node.setRight(aux.getLeft());
        aux.setLeft(node);

        return aux;
    }
    public static Nodo RSD(Nodo node){
        Nodo aux = node.getLeft();
        node.setLeft(aux.getRight());
        aux.setRight(node);
        
        return aux;
    }
    public static Nodo RDDI(Nodo node){
        node.setRight(RSD(node.getRight()));
        //node.right = RSD(node.getRight());
        return RSI(node);
    }
    public static Nodo RDID(Nodo node){
        node.setLeft(RSI(node.getLeft()));
        //node.left = RSI(node.getLeft());
        return RSD(node);
    }
}
