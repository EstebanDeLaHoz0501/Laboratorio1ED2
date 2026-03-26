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

        writer.write(nodo.getCurso().id + ";\n");

        // Hijo izquierdo
        if (nodo.getLeft() != null) {
            writer.write(nodo.getCurso().id + " -> " + nodo.getLeft().getCurso().id + ";\n");
            generarDOT(nodo.getLeft(), writer);
        } else {
            // nodo invisible para forzar posición
            String nullLeft = "nullL" + nodo.getCurso().id;
            writer.write(nullLeft + " [shape=point];\n");
            writer.write(nodo.getCurso().id + " -> " + nullLeft + ";\n");
        }

        // Hijo derecho
        if (nodo.getRight() != null) {
            writer.write(nodo.getCurso().id + " -> " + nodo.getRight().getCurso().id + ";\n");
            generarDOT(nodo.getRight(), writer);
        } else {
            String nullRight = "nullR" + nodo.getCurso().id;
            writer.write(nullRight + " [shape=point];\n");
            writer.write(nodo.getCurso().id + " -> " + nullRight + ";\n");
        }
    }
   public static void exportarDOT(Nodo raiz) {

        try {
            FileWriter writer = new FileWriter("arbol.dot");

            writer.write("digraph AVL {\n");
            writer.write("node [shape=circle];\n");
            writer.write("rankdir=TB;\n");

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
