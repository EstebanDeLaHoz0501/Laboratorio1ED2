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
import java.time.Instant;
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
                Graphviz.exportarDOT(tree.getRaiz());
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }
            if(CM.checkExistance(id)){
                Graphviz.exportarDOT(tree.getRaiz());
                return new Response(true, "Ya existe.", Status.CREATED);
            } 
            if(CM.addCurso(this.sc,"src/Core/dataset_courses_with_reviews",idS)){
                if(tree.getRaiz()==null){
                    
                    System.out.println(CM.getCursos()); //revisar
                    this.tree.setRaiz( OperacionesArbol.insertar1(this.tree, id));
                    Graphviz.exportarDOT(tree.getRaiz());
                }else{
                    this.tree.setRaiz(
                        OperacionesArbol.insertar(this.tree.getRaiz(), id, this.tree)
                    );
                    
                    Graphviz.exportarDOT(tree.getRaiz());
                    
                }
                
            return new Response(true, "Insertado.", Status.CREATED);
            }
            
            return new Response(true, "No encontrado.", Status.CREATED);
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
            System.out.println(CM.getCursos());
            
            if(CM.delCurso(idS, false)){
                System.out.println(CM.getCursos());
                //se borra el nodo, se dibuja el arbol

                
                this.tree.setRaiz(OperacionesArbol.delete(this.tree.getRaiz(), id, this.tree));
                CM.delCurso(idS, true);

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
    public Response borrarNodoMetrica(String metricaS){
        try {     
            
            double metrica = Double.parseDouble(metricaS);
            // verifica que no hayan negativos
            if(metrica< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }
            
            //si se pudo borrar el curso:
            System.out.println(CM.getCursos());
            if(CM.delCursoMetrica(metricaS, false)){
                System.out.println(CM.getCursos());
                //se borra el nodo, se dibuja el arbol

                System.out.println(this.tree.getRaiz()+"esta es la raiz");
                this.tree.setRaiz(OperacionesArbol.delete(this.tree.getRaiz(), metrica, this.tree));
                CM.delCursoMetrica(metricaS, true);

                Graphviz.exportarDOT(tree.getRaiz());
  
            return new Response(true, "Borrado exitosamente.", Status.CREATED);
            }
            
            return new Response(false, "El nodo no existe.", Status.BAD_REQUEST);
        } catch (NumberFormatException e) {
            
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {

             return new Response(false, "Error inesperado.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public Response clasesEnRango(String minStr, String maxStr){
        try{
            int min = Integer.parseInt(minStr);
            int max = Integer.parseInt(maxStr);

            if(min < 0 || max < 0){
                return new Response(false, "Inserte valores positivos", Status.BAD_REQUEST);
            }
        

            return new Response(true, "Nodos encontrados exitosamente.", Status.CREATED);
        
        } catch (NumberFormatException e){
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        }catch (Exception e) {
            System.out.println(e);
             return new Response(false, "Error inesperado.", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    public Response isAfter(String date){
        try{
            
            Instant i = null;
            i = Instant.parse(date);
            
            return new Response(true, "Fecha comparada exitosamente", Status.CREATED);
            
        }catch (Exception e) {
             return new Response(false, "Error inesperado.", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
    public Response InfoCompleta(String idS){
        try {     
            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }

            return new Response(true, "Exito.", Status.OK, CM.getCurso(id).informacion());
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response NivelNodo(String idS){
        try {     
            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }

            return new Response(true, "Exito.", Status.OK, 
            "Nivel del Nodo: "+ OperacionesArbol.search(this.tree.getRaiz(), id).get(0).nivelNodo(this.tree.getRaiz()));
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response FactBalance(String idS){
        try {     
            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }

            return new Response(true, "Exito.", Status.OK, 
            "Factor de balanceo: "+ OperacionesArbol.search(this.tree.getRaiz(), id).get(0).factorbalanceo());
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response Padre(String idS){
        try {     
            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }
            if(OperacionesArbol.search(this.tree.getRaiz(), id).get(1)!= null){
                return new Response(true, "Exito.", Status.OK, 
            "Padre: "+ OperacionesArbol.search(this.tree.getRaiz(), id).get(1).infobasica());
            }else{
                return new Response(true, "No tiene padre", Status.NOT_FOUND, null);
            }
            
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
     public Response Abuelo(String idS){
        try {     
            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }
            if(OperacionesArbol.search(this.tree.getRaiz(), id).get(2)!= null){
                return new Response(true, "Exito.", Status.OK, 
            "Abuelo: "+ OperacionesArbol.search(this.tree.getRaiz(), id).get(2).infobasica());
            }else{
                return new Response(true, "No tiene abuelo", Status.NOT_FOUND, null);
            }
            
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response Tio(String idS){
        try {     
            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }
            if(OperacionesArbol.search(this.tree.getRaiz(), id).get(3)!= null){
                return new Response(true, "Exito.", Status.OK, 
            "Tio: "+ OperacionesArbol.search(this.tree.getRaiz(), id).get(3).infobasica());
            }else{
                return new Response(true, "No tiene tio", Status.NOT_FOUND, null);
            }
            
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response InfoCompletaMetrica(String meS){
        try {     
            
            Double me = Double.parseDouble(meS);
            // verifica que no hayan negativos
            if(Double.parseDouble(meS)< 0){
                return new Response(false, "Inserte una metrica positiva", Status.BAD_REQUEST);
            }

            return new Response(true, "Exito.", Status.OK,
                    OperacionesArbol.search(this.tree.getRaiz(), me).get(0).getCurso().informacion());
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response NivelMetrica(String meS){
        try {     
            
            Double me = Double.parseDouble(meS);
            // verifica que no hayan negativos
            if(Double.parseDouble(meS)< 0){
                return new Response(false, "Inserte una metrica positiva", Status.BAD_REQUEST);
            }

            return new Response(true, "Exito.", Status.OK,
                    OperacionesArbol.search(this.tree.getRaiz(), me).get(0).nivelNodo(this.tree.getRaiz()));
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response FBalanceoMetrica(String meS){
        try {     
            
            Double me = Double.parseDouble(meS);
            // verifica que no hayan negativos
            if(Double.parseDouble(meS)< 0){
                return new Response(false, "Una metrica positiva", Status.BAD_REQUEST);
            }

            return new Response(true, "Exito.", Status.OK,
                    OperacionesArbol.search(this.tree.getRaiz(), me).get(0).factorbalanceo());
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response PadreMetrica(String meS){
        try {     
            
            Double me = Double.parseDouble(meS);
            // verifica que no hayan negativos
            if(Double.parseDouble(meS)< 0){
                return new Response(false, "Una metrica positiva", Status.BAD_REQUEST);
            }
            if(OperacionesArbol.search(this.tree.getRaiz(), me).get(1)!= null){
                return new Response(true, "Exito.", Status.OK, 
            "Padre: "+ OperacionesArbol.search(this.tree.getRaiz(), me).get(1).infobasica());
            }else{
                return new Response(true, "No tiene padre", Status.NOT_FOUND, null);
            }
            
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response AbueloMetrica(String meS){
        try {     
            
            Double me = Double.parseDouble(meS);
            // verifica que no hayan negativos
            if(Double.parseDouble(meS)< 0){
                return new Response(false, "Una metrica positiva", Status.BAD_REQUEST);
            }
            if(OperacionesArbol.search(this.tree.getRaiz(), me).get(2)!= null){
                return new Response(true, "Exito.", Status.OK, 
            "Abuelo: "+ OperacionesArbol.search(this.tree.getRaiz(), me).get(2).infobasica());
            }else{
                return new Response(true, "No tiene abuelo", Status.NOT_FOUND, null);
            }
            
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response TioMetrica(String meS){
        try {     
            
            Double me = Double.parseDouble(meS);
            // verifica que no hayan negativos
            if(Double.parseDouble(meS)< 0){
                return new Response(false, "Una metrica positiva", Status.BAD_REQUEST);
            }
            if(OperacionesArbol.search(this.tree.getRaiz(), me).get(3)!= null){
                return new Response(true, "Exito.", Status.OK, 
            "Tio: "+ OperacionesArbol.search(this.tree.getRaiz(), me).get(3).infobasica());
            }else{
                return new Response(true, "No tiene tio", Status.NOT_FOUND, null);
            }
            
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response BuscarID(String idS){
        try {     
            
            int id = Integer.parseInt(idS);
            // verifica que no hayan negativos
            if(Integer.parseInt(idS)< 0){
                return new Response(false, "Inserte un ID positivo", Status.BAD_REQUEST);
            }
            if(OperacionesArbol.search(this.tree.getRaiz(), id).get(0)!= null){
                return new Response(true, "Exito.", Status.OK, 
            "Nodo: "+ OperacionesArbol.search(this.tree.getRaiz(), id).get(0).infobasica());
            }else{
                return new Response(true, "No existe", Status.NOT_FOUND, null);
            }
            
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public Response BuscarMetrica(String meS){
        try {     
            
            Double me = Double.parseDouble(meS);
            // verifica que no hayan negativos
            if(Double.parseDouble(meS)< 0){
                return new Response(false, "Una metrica positiva", Status.BAD_REQUEST);
            }
            if(OperacionesArbol.search(this.tree.getRaiz(), me).get(0)!= null){
                return new Response(true, "Exito.", Status.OK, 
            "Nodo: "+ OperacionesArbol.search(this.tree.getRaiz(), me).get(0).infobasica());
            }else{
                return new Response(true, "No existe", Status.NOT_FOUND, null);
            }
            
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
             return new Response(false, "No se encontró este curso.", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
