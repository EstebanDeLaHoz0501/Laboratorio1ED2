/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

/**
 *
 * @author Esteban
 */
public class Curso {
    public int id;
    String title;
    double rating;
    int num_reviews;
    int num_published_lectures;
    String created;
    String last_update_date;
    String duration;
    int instructors_id;
    String image;
    int positive_reviews;
    int negative_reviews;
    int neutral_reviews;

    double satisfaction;

    public Curso(int id, String title, double rating, int num_reviews, int num_published_lectures, String created, String last_update_date, String duration, int instructors_id, String image, int positive_reviews, int negative_reviews, int neutral_reviews) {
        this.id = id;
        this.title = title;
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
    
    
}
