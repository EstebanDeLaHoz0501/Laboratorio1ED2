/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.controllers;

import Core.Arbol.AVL;
import Core.Arbol.Graphviz;
import Core.Arbol.OperacionesArbol;
import Core.CursoManager;
import Core.controllers.utils.Response;
import Core.controllers.utils.Status;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 *
 * @author Esteban
 */
public class Controladores {
    private CursoManager CM;
    private Scanner sc;
    private AVL tree;

    public Controladores(CursoManager CM, AVL tree) {
        this.CM = CM;
        this.sc = new Scanner(System.in);
        this.tree = tree;
    }
    
    public Response insertarNodo(String idS) {
        try {            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }
            
            if(CM.addCurso(this.sc,"src/Core/dataset_courses_with_reviews",idS)){
                if(tree.getRaiz()==null){
                    System.out.println("aqui");
                    System.out.println(CM.getCursos()); //revisar
                    tree.setRaiz( OperacionesArbol.insertar1(this.tree, id));
                    Graphviz.exportarDOT(tree.getRaiz());
                }else{
                    this.tree.setRaiz(
                        OperacionesArbol.insertar(this.tree.getRaiz(), id, this.tree)
                    );
                    
                    Graphviz.exportarDOT(tree.getRaiz());
                    
                }
                
            return new Response(true, "Insertado.", Status.CREATED);
            }
            
            return new Response(true, "Ya existe.", Status.CREATED);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "Error inesperado.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public Response borrarNodo(String idS){
        try {     
            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }
            
            //si se pudo borrar el curso:
            if(CM.delCurso(idS)){
                
                //se borra el nodo, se dibuja el arbol
                OperacionesArbol.delete(this.tree.getRaiz(), id, this.tree);
                Graphviz.exportarDOT(tree.getRaiz());
  
            return new Response(true, "Borrado exitosamente.", Status.CREATED);
            }
            
            return new Response(false, "El nodo no existe.", Status.BAD_REQUEST);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "Error inesperado.", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
