package com.hen.backend.models;
 
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 *
 * @author Henrry Gómez
 */
public class Dna {
    
    public String[] Secuence; 
    
    public Dna(@JsonProperty("dna")String[] Secuence) {
        this.Secuence = Secuence;
    }

    public String[] getSecuence() {
        return Secuence;
    }

    public void setSecuence(String[] Secuence) {
        this.Secuence = Secuence;
    }
    
}
