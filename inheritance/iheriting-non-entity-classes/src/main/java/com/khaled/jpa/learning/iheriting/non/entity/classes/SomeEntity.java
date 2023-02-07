package com.khaled.jpa.learning.iheriting.non.entity.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
/**
 *
 * @author khaled
 */
@Entity
public class SomeEntity extends NoEntityClasses{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private Long id;
    
    private int somePersistedSate;

    public SomeEntity() {
    }

    public SomeEntity(int somePersistedSate) {
        this.somePersistedSate = somePersistedSate;
    }
    public int SomeUsefulMethod(){
        return nonePersistedState*somePersistedSate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSomePersistedSate() {
        return somePersistedSate;
    }

    public void setSomePersistedSate(int somePersistedSate) {
        this.somePersistedSate = somePersistedSate;
    }
    
}
