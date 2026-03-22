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
import java.time.Instant;
import java.time.format.DateTimeParseException;
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
                return cu.getTitle();
            }
        }
        return null;
    }
    
    public boolean checkExistance(int id){
        for(Curso cur: cursos){
            if( cur.id == id) return true;
        }
        
        return false;
    }
    //Busca el id en el .csv
    public  boolean addCurso(Scanner sc, String fileName, String id){
            //si no se tiene el curso, se puede añadir
            
            if(this.checkExistance(Integer.parseInt(id))== false){
                
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
                       int numRev= Integer.parseInt(temp[4]);
                       int numPub= Integer.parseInt(temp[5]);
                       Instant created = null;
                       created = Instant.parse(temp[6]);
                       String lastUpdDate = temp[7]; //de pronto cambia esto a una date?
                       String duration = temp[8];
                       int instrId = Integer.parseInt(temp[9]);
                       String image = temp[10];
                       int posRev = Integer.parseInt(temp[11]);
                       int negRev = Integer.parseInt(temp[12]);
                       int neutRev = Integer.parseInt(temp[13]);
                       
                       Curso cur = new Curso(idInt, title, url, rating, numRev, numPub, created, lastUpdDate, duration, instrId, image, posRev, negRev, neutRev);
                       
                    }
           
                }
                BufferLectura.close();
                return true; //si se pudo crear, regresa true
                   
                } catch (IOException ex) {
                System.out.println("No se encontro archivo");
           
                }
            } 
            return false; // si no, regresa false
                
        }
    
    //estatico pq solo hay un curso manager
    public static Curso getCurso(int id){
        for(Curso cur: cursos){
            if(cur.getId() == id){
                return cur;
            }
        }
        return null;
    }
}
