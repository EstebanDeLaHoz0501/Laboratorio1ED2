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
        Nodo aux = node.right;
        node.right = aux.left;
        aux.left=node;
        return aux;
    }
    public static Nodo RSD(Nodo node){
        Nodo aux = node.left;
        node.left = aux.right;
        aux.right=node;
        return aux;
    }
    public static Nodo RDDI(Nodo node){
        node.right = RSD(node.right);
        return RSI(node);
    }
    public static Nodo RDID(Nodo node){
        node.left = RSI(node.left);
        return RSD(node);
    }
}
