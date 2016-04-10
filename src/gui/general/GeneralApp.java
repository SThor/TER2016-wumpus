/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Action;
import model.Condition;
import model.State;
import model.SysObject;
import model.World;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class GeneralApp {
    public static void main(String args[]) {
        
        // World used throughout the application
        final World world = new World();
        
        // Demo world
        SysObject door = new SysObject("Door", world);
        SysObject person = new SysObject("Person", world);
        
        door.addPossibleState(new State("Opened"));
        door.addPossibleState(new State("Closed"));
        
        person.addPossibleState(new State("Sat"));
        person.addPossibleState(new State("Up"));
        
        world.addObject(door);
        world.addObject(person);
        
        Action closeDoor = new Action("Close door");
        closeDoor.addPreCondition(new Condition(door, new State("Opened")));
        closeDoor.addPreCondition(new Condition(person, new State("Up")));
        closeDoor.addPostCondition(new Condition(door, new State("Closed")));
        closeDoor.addPostCondition(new Condition(person, new State("Up")));
        
        world.addPossibleAction(closeDoor);
        
        
        // Try to set look and feel to platform.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Cannot get platform look and feel. Will use cross platform look and feel.");
        }

        // Launch the interface
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WorldBuilderUI(world).setVisible(true);
            }
        });
    }
}
