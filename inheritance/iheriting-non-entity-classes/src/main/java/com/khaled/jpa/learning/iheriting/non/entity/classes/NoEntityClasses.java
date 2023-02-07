
package com.khaled.jpa.learning.iheriting.non.entity.classes;

/**
 *
 * @author khaled
 */
public class NoEntityClasses {
    protected int nonePersistedState;

    public int getNonePersistedState() {
        return nonePersistedState;
    }

    public void setNonePersistedState(int nonePersistedState) {
        this.nonePersistedState = nonePersistedState;
    }

    public int someUseful(){
        return nonePersistedState*31;
    }
    
}
