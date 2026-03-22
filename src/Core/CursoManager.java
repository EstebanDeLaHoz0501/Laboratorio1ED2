/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
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
    
    //Busca el id en el .csv
    public static void addCurso(Scanner sc, String fileName, String id){
        try{
                FileReader outFile = new FileReader(fileName+".csv");
                BufferedReader BufferLectura = new BufferedReader(outFile);
                String line = null;
                
                while((line = BufferLectura.readLine()) != null){
                    String temp[] = line.split(",");
                    if (temp[0].equals(id)){
                        
                       // Si se encuentra el id, se crea un curso con esas caracteristicas
                       int idInt = Integer.parseInt(temp[0]);
                       String title = temp[1];
                       String url = temp[2];
                       double rating = Double.parseDouble(temp[3]);
                       int num_reviews = Integer.parseInt(temp[4]);
                       int num_published_lectures = Integer.parseInt(temp[5]);
                       created = created;
                       last_update_date = last_update_date;
                       String duration = temp[8];
                       int instructors_id = Integer.parseInt(temp[9]);
                       String image = temp[10];
                       int positive_reviews = Integer.parseInt(temp[11]);
                       int negative_reviews = Integer.parseInt(temp[12]);
                       int neutral_reviews = Integer.parseInt(temp[13]);
                       
                       Curso curso = new Curso()
                       
                    }
           
                }
                BufferLectura.close();
                
                   
    } catch (IOException ex) {
                System.out.println("No se encontro archivo");
           
            }
    }
    
}
