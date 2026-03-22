/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.format.DateTimeParseException;
/**
/**
 *
 * @author Esteban
 */
public class Curso {
    public int id;
    private String title;
    private String url;
    private double rating;
    private int num_reviews;
    private int num_published_lectures;
    private Instant created;
    private String last_update_date;
    private String duration;
    private int instructors_id;
    private String image;
    private int positive_reviews;
    private int negative_reviews;
    private int neutral_reviews;

    double satisfaction;

    //SOLO SE LLAMA DESDE CursoManager, si no existe el curso no se puede ejecutar
    public Curso(int id, String title, String url, double rating, int num_reviews, int num_published_lectures, Instant created, String last_update_date, String duration, int instructors_id, String image, int positive_reviews, int negative_reviews, int neutral_reviews) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.num_published_lectures = num_published_lectures;
        this.created = created;
        this.last_update_date = last_update_date;
        this.duration = duration;
        this.instructors_id = instructors_id;
        this.image = image;
        this.positive_reviews = positive_reviews;
        this.negative_reviews = negative_reviews;
        this.neutral_reviews = neutral_reviews;
        
        this.satisfaction = rating*0.7+((5*positive_reviews+3*neutral_reviews+negative_reviews)/(num_reviews))*0.3;
        
        
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public double getRating() {
        return rating;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public int getNum_published_lectures() {
        return num_published_lectures;
    }

    public Instant getCreated() {
        return created;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public String getDuration() {
        return duration;
    }

    public int getInstructors_id() {
        return instructors_id;
    }

    public String getImage() {
        return image;
    }

    public int getPositive_reviews() {
        return positive_reviews;
    }

    public int getNegative_reviews() {
        return negative_reviews;
    }

    public int getNeutral_reviews() {
        return neutral_reviews;
    }

    public double getSatisfaction() {
        return satisfaction;
    }
    
    
    
}
