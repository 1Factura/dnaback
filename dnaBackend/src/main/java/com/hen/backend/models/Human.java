package com.hen.backend.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author Henrry Gómez
 */
@Entity
@Table(name = "Human")
public class Human {

    private long id;
    private String Name;
    private String Dna;
    private boolean HasMutation;
    private LocalDateTime CreatedAt;

	
    public Human() {
		
    }

    public Human(long id, String Name, String Dna, boolean HasMutation, LocalDateTime CreatedAt) {
        this.id = id;
        this.Name = Name;
        this.Dna = Dna;
        this.HasMutation = HasMutation;
        this.CreatedAt = CreatedAt;
    }
	
    
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }  
        
    @Column(name = "Name", nullable = false)
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Column(name = "Dna", nullable = false)
    public String getDna() {
        return Dna;
    }

    public void setDna(String Dna) {
        this.Dna = Dna;
    }

    @Column(name = "HasMutation", nullable = false)
    public boolean isHasMutation() {
        return HasMutation;
    }

    public void setHasMutation(boolean HasMutation) {
        this.HasMutation = HasMutation;
    }

    @Column(name = "CreatedAt", nullable = false)
    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime CreatedAt) {
        this.CreatedAt = CreatedAt;
    }
    
    @Override
    public String toString() {
	return "Human [id=" + id + ", Name =" + Name + ", Dna =" + Dna + ", HasMutation =" + HasMutation +  ", CreatedAt =" + CreatedAt + "]";
    }
	
}
