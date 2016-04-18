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
import model.observations.Scenario;
import model.SysObject;
import model.World;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Launcher {
    public static void main(String args[]) {
        
        // World used throughout the application
        final World world = new World();
        
        //--- Demo world ---//
        
        SysObject door = new SysObject("Door", world);
        SysObject person = new SysObject("Person", world);
        
        door.addProperty("isOpened");
        door.addPossibleValue("isOpened", "true");
        door.addPossibleValue("isOpened", "false");
        
        person.addProperty("position");
        person.addPossibleValue("position", "sat");
        person.addPossibleValue("position", "up");
        person.addPossibleValue("position", "atDoor");
        
        person.addProperty("isCalling");
        person.addPossibleValue("isCalling", "true");
        person.addPossibleValue("isCalling", "false");
        
        world.addObject(door);
        world.addObject(person);
        
        Action closeDoor = new Action("Close_Door");
        closeDoor.addPreCondition(new Condition(person, "position", "atDoor"));
        closeDoor.addPreCondition(new Condition(door, "isOpened", "true"));
        closeDoor.addPostCondition(new Condition(person, "position", "atDoor"));
        closeDoor.addPostCondition(new Condition(door, "isOpened", "false"));
        
        Action openDoor = new Action("Open_Door");
        openDoor.addPreCondition(new Condition(person, "position", "atDoor"));
        openDoor.addPreCondition(new Condition(door, "isOpened", "false"));
        openDoor.addPostCondition(new Condition(person, "position", "atDoor"));
        openDoor.addPostCondition(new Condition(door, "isOpened", "true"));
        
        world.addPossibleAction(openDoor);
        world.addPossibleAction(closeDoor);
        
        //------------------//
        
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
                new GeneralUI(world, new Scenario()).setVisible(true);
            }
        });
    }
}
