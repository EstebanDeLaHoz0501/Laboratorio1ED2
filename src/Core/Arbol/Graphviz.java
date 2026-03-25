/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Arbol;

import Core.Nodo;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Esteban
 */
public class Graphviz {
    public static void generarDOT(Nodo nodo, FileWriter writer) throws IOException {

        if (nodo == null) return;

        if (nodo.getLeft() != null) {
            writer.write(nodo.getCurso().id + " -> " + nodo.getLeft().getCurso().id + ";\n");
            generarDOT(nodo.getLeft(), writer);
        }

        if (nodo.getRight() != null) {
            writer.write(nodo.getCurso().id + " -> " + nodo.getRight().getCurso().id + ";\n");
            generarDOT(nodo.getRight(), writer);
        }
   }
   public static void exportarDOT(Nodo raiz) {

        try {
            FileWriter writer = new FileWriter("arbol.dot");

            writer.write("digraph AVL {\n");
            writer.write("node [shape=circle];\n");

            generarDOT(raiz, writer);

            writer.write("}\n");
            
            writer.close();

            Process p = Runtime.getRuntime().exec(
                "\"C:\\Program Files\\Graphviz\\bin\\dot.exe\" -Tpng arbol.dot -o arbol.png"
            );
            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
