package model;

import ilog.solver.IlcAnyVar;
import ilog.solver.IlcConstraint;
import ilog.solver.IlcSolver;
import java.util.ArrayList;
import model.observations.Observation;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.exceptions.DuplicateElementException;

/**
 * Represents a possible action in a world.
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Action implements Observation {

    /**
     * Name of the action.
     */
    private final String name;

    /**
     * List of pre-conditions for this action to be realised.
     */
    private final List<Condition> preConditions;

    /**
     * List of post-conditions after this action has been realised.
     */
    private final List<Condition> postConditions;

    /**
     * Constructs an action with no pre- and post-consitions
     *
     * @param name The name of this action.
     * @throws NullPointerException If <tt>name</tt> is null.
     */
    public Action(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        
        this.name = name;
        preConditions = new UniqueList<>();
        postConditions = new UniqueList<>();
    }

    /**
     * Check wether the pre-conditions are verified.
     *
     * @return <tt>true</tt> if all the pre-conditions are verified,
     * <tt>false</tt> otherwise.
     */
    public boolean preConditionsVerified() {
        for (Condition c : preConditions) {
            if (!c.isVerified()) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Check wether the post-conditions are verified.
     *
     * @return <tt>true</tt> if all the post-conditions are verified,
     * <tt>false</tt> otherwise.
     */
    public boolean postConditionsVerified() {
        for (Condition c : postConditions) {
            if (!c.isVerified()) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Add a pre-condition for this action.
     *
     * @param preCondition The pre-condition to add
     * @throws DuplicateElementException If the pre-condition already exists.
     */
    public void addPreCondition(PropertyValue preCondition) {
        preConditions.add(preCondition);
    }

    /**
     * Add a post-condition for this action.
     *
     * @param postCondition The post-condition to add
     * @throws DuplicateElementException If the post-condition already exists
     */
    public void addPostCondition(PropertyValue postCondition) {
        postConditions.add(postCondition);
    }

    /**
     * Remove a pre-condition.
     *
     * @param index The index of the pre-condition to remove
     */
    public void removePreCondition(int index) {
        preConditions.remove(index);
    }

    /**
     * Remove a post-condition.
     *
     * @param index The index of the post-condition to remove
     */
    public void removePostCondition(int index) {
        postConditions.remove(index);
    }

    /**
     * Accessor to the pre-conditions.
     *
     * @return The list of pre-conditions
     */
    public List<Condition> getPreConditions() {
        return preConditions;
    }

    /**
     * Accessor to the post-conditions.
     *
     * @return The list of post-conditions
     */
    public List<Condition> getPostConditions() {
        return postConditions;
    }

    /**
     * @see Action#removeFromList(java.util.List, model.SysObject,
     * java.lang.String, java.lang.String)
     */
    protected void removeAllConditions(SysObject object, String property, String value) {
        removeFromList(preConditions, object, property, value);
        removeFromList(postConditions, object, property, value);
    }

    /**
     * Remove all condition associated to an object that has a certain property
 which is currently at a certain objectMap. Note: if <tt>objectMap</tt> is
     * <tt>null</tt>, the objectMap of the property is discarded. if
 <tt>property</tt> is <tt>null</tt> the object is deleted wether it has
     * this propety or not.
     *
     * @param object The associated object
     * @param property The associated property
     * @param values The list of possible values associated
     */
    private void removeFromList(List<Condition> conditions, SysObject object, String property, String value) {
        for (Iterator<Condition> it = conditions.iterator(); it.hasNext();) {
            Condition c = it.next();
            if (c.largeEquals(object, property, value)) {
                it.remove();
            }
        }
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + name.hashCode();
        return hash;
    }

    /**
     * Two <tt>Actions</tt> are equals if they have the same name, regardless of
     * their pre-conditions.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Action)) {
            return false;
        }
        
        return ((Action) obj).name.equals(name);
    }
    
    @Override
    public boolean isVerified() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isVerifiedIn(WorldState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName() {
        return name;
    }

    public boolean preConditionsVerifiedIn(WorldState before) {
        for (Condition preCond : preConditions) {
            if (!preCond.isVerifiedIn(before)) {
                return false;
            }
        }
        return true;
    }

    public boolean postConditionsVerifiedIn(WorldState after) {
        for (Condition postCond : postConditions) {
            if (!postCond.isVerifiedIn(after)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * @return The list of Objects.properties modified by this action
     */
    protected List<Condition> modifiedObjects() {
        List<Condition> modified = new ArrayList<>();
        List<SysObject> objectsInPreCond = new ArrayList<>();
        for (Condition preCond : preConditions) {
            objectsInPreCond.add(preCond.getObject());
        }
        for (Condition postCond : postConditions) {
            SysObject postCondObject = postCond.getObject();
            if (objectsInPreCond.contains(postCondObject)) {
                for (Condition preCond : preConditions) {
                    if (preCond instanceof PropertyValue) {
                        if (preCond.getObject().equals(postCondObject) && preCond.getPropertyName().equals(postCond.getPropertyName())) {
                            // We have found a condition acting on the same Object.property in both preConditions and postConditions
                            // If the wanted objectMap is changed, the object is modified by this action
                            PropertyValue pre = (PropertyValue) preCond;
                            PropertyValue post = (PropertyValue) postCond;
                            if (!pre.getWantedValue().equals(post.getWantedValue())) {
                                modified.add(preCond);
                            }
                        }
                    }
                }
            }
        }
        return modified;
    }

    @Override
    public IlcConstraint solverConstraint(IlcSolver solver, Map<SysObject, Map<String, IlcAnyVar>> worldMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Return the transitionnal constraints for a backtrack solver
     * @param solver Solver to constraint
     * @param worldMapBefore Map of the world before the action
     * @param worldMapAfter Map of the world after the action
     * @return 
     */
    public IlcConstraint transitionConstraint(IlcSolver solver, 
    Map<SysObject, Map<String, IlcAnyVar>> worldMapBefore, 
    Map<SysObject, Map<String, IlcAnyVar>> worldMapAfter) {
        if (preConditions.isEmpty() || postConditions.isEmpty()) {
            return null;
        }
        
        List<String> concernedProperties = new ArrayList<>();
        int i;
        
        Condition preCond = preConditions.get(0);
        IlcConstraint preCondConstraint = preCond.solverConstraint(solver, worldMapBefore);
        concernedProperties.addAll(preCond.getPropertiesNames());
        for (i = 1; i < preConditions.size(); i++) {
            preCond = preConditions.get(i);
            preCondConstraint = solver.and(preCondConstraint, preCond.solverConstraint(solver, worldMapBefore));
            concernedProperties.addAll(preCond.getPropertiesNames());
        }
        
        Condition postCond = postConditions.get(0);
        IlcConstraint postCondConstraint = postCond.solverConstraint(solver, worldMapAfter);
        concernedProperties.addAll(postCond.getPropertiesNames());
        for (i = 1; i < postConditions.size(); i++) {
            postCond = postConditions.get(i);
            postCondConstraint = solver.and(postCondConstraint, postCond.solverConstraint(solver, worldMapAfter));
            concernedProperties.addAll(postCond.getPropertiesNames());
        }
        
        IlcConstraint transitionConstraint = solver.imply(preCondConstraint, postCondConstraint);
        
        // Now we need to declare that all property not modified by this action must stay
        // equal between the two states
        for (Map.Entry<SysObject, Map<String, IlcAnyVar>> objectEntry : worldMapBefore.entrySet()) {
            SysObject objectKey = objectEntry.getKey();
            Map<String, IlcAnyVar> objectMap = objectEntry.getValue();
            for (Map.Entry<String, IlcAnyVar> propEntry : objectMap.entrySet()) {
                String propKey = propEntry.getKey();
                IlcAnyVar var = propEntry.getValue();
                // If the property is not concerned by the action, constrain to equality
                if (!concernedProperties.contains(propKey)) {
                    transitionConstraint = solver.and(transitionConstraint, solver.eq(var, worldMapAfter.get(objectKey).get(propKey)));
                }
            }
            
        }
        
        return transitionConstraint;
    }
}
