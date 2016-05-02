/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WorldState extends ArrayList<PropertyValue> {
    
    @Override
    public int hashCode() {
        return 14*super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof WorldState)) {
            return false;
        }
        
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(((WorldState)o).get(i))) {
                return false;
            }
        }
        
        return true;
    }
}
