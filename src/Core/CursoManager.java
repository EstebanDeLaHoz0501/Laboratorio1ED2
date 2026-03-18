/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class CursoManager {  
        
    public static ArrayList<Curso> cursos;

    public static double getSatisfaction(int id){
        for (Curso cu: cursos){
            if (cu.id == id){
                return cu.satisfaction;
            }
        }
        return -1;
    }
    public static String getTitle(int id){
        for (Curso cu: cursos){
            if (cu.id == id){
                return cu.title;
            }
        }
        return null;
    }
    
}
