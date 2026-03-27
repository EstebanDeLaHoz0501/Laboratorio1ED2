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
        
    public static ArrayList<Curso> cursos = new ArrayList<>();
    private static CursoManager instance;

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public static CursoManager getInstance() {
        if (instance == null) {
            instance = new CursoManager();
        }
        return instance;
    }
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
        if(this.cursos != null){
            for(Curso cur: cursos){
                if( cur.id == id) return true;
            }
        }
        return false;
    }
    //Busca el id en el .csv
    public  boolean addCurso(Scanner sc, String fileName, String id){
            //si no se tiene el curso, se puede añadir
            
            if(this.checkExistance(Integer.parseInt(id))){
               return false;
            }
               try{ 
                FileReader outFile = new FileReader(fileName+".csv");
                BufferedReader BufferLectura = new BufferedReader(outFile);
                String line = null;
                
                while((line = BufferLectura.readLine()) != null){

                    // hay varios simbolos que oculta excel del csv, esto los quita
                    line = line.trim();
                    line = line.replaceAll(";+$", "");

                    // quitar comillas que envuelven toda la fila
                    if (line.startsWith("\"") && line.endsWith("\"")) {
                        line = line.substring(1, line.length() - 1);
                    }

                    // convertir "" a "
                    line = line.replace("\"\"", "\"");

 

                    String temp[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    
                    if (temp[0].equals(id)){
                        System.out.println("CSV ID: " + temp[0] + " | Buscando: " + id);
                     

                 /// ESTO DE ACA ES PARA VOLVER A PONER LO QUE SE QUITO

                    for (int i = 0; i < temp.length; i++) {
                        temp[i] = temp[i].trim();

                        // quitar comillas solo de los extremos de cada campo
                        if (temp[i].startsWith("\"") && temp[i].endsWith("\"")) {
                            temp[i] = temp[i].substring(1, temp[i].length() - 1);
                        }
                    }

                    if (temp.length < 14) {
                        System.out.println("Fila inválida, columnas detectadas: " + temp.length);
                        System.out.println("Contenido: " + temp);
                        continue;
                    }

                    

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
                       this.cursos.add(cur);
                       BufferLectura.close();
                       return true;
                    }
           
                }
                BufferLectura.close();
                
                   
                } catch (IOException ex) {
                   System.out.println(ex);
                System.out.println("No se encontro archivo");
           
                }
             
            return false; // si no, regresa false
                
        }
    
    public boolean delCurso(String id, boolean quitar){
        if(this.cursos != null){
            for(Curso cur: cursos){
                if( cur.id == Integer.parseInt(id)){
                    //si se encuentra, se borra
                    int index = cursos.indexOf(cur);
                    if(quitar){
                    cursos.remove(index);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public boolean delCursoMetrica(String metrica, boolean quitar){
        if(this.cursos != null){
            for(Curso cur: cursos){
                if( cur.getSatisfaction() == Double.parseDouble(metrica)){
                    //si se encuentra, se borra
                    int index = cursos.indexOf(cur);
                    if(quitar){
                    cursos.remove(index);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    //estatico pq solo hay un curso manager
    public Curso getCurso(int id){
        for(Curso cur: cursos){
            if(cur.getId() == id){
                return cur;
            }
        }
        return null;
    }
    
}
