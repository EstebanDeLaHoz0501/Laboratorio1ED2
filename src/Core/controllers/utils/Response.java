/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.controllers.utils;

/**
 *
 * @author Esteban
 */
public class Response {
    private boolean success;
    private String message;
    private int status;
    private Object object;
      
    public Response(boolean success, String message, int status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }
    
    public Response(boolean success, String message, int status, Object object){
        this.success = success;
        this.message = message;
        this.status = status;
        this.object = object;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }
    
    
    
}
