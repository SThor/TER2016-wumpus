/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.general.GeneralUI;
import ilog.concert.IloException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Action;
import model.observations.And;
import model.PropertyValue;
import model.SysObject;
import model.World;
import model.observations.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Launcher {
    public static void main(String args[]) throws IloException {
        
        // World used throughout the application
        final World world = new World();
        
        //--- Demo world ---//
        
        SysObject door = new SysObject("Door", world);
        SysObject person = new SysObject("Person", world);
        
        door.addProperty("isOpened");
        door.addPossibleValue("isOpened", 1);
        door.addPossibleValue("isOpened", 0);
        
        person.addProperty("position");
        person.addPossibleValue("position", 1);
        person.addPossibleValue("position", 2);
        person.addPossibleValue("position", 3);
        
        person.addProperty("isCalling");
        person.addPossibleValue("isCalling", 1);
        person.addPossibleValue("isCalling", 0);
        
        world.addObject(door);
        world.addObject(person);
        
        Action closeDoor = new Action("Close_Door");
        closeDoor.addPreCondition(new PropertyValue(person, "position", 3));
        closeDoor.addPreCondition(new PropertyValue(door, "isOpened", 1));
        closeDoor.addPostCondition(new PropertyValue(person, "position", 3));
        closeDoor.addPostCondition(new PropertyValue(door, "isOpened", 0));
        
        Action openDoor = new Action("Open_Door");
        openDoor.addPreCondition(new PropertyValue(person, "position", 3));
        openDoor.addPreCondition(new PropertyValue(door, "isOpened", 0));
        openDoor.addPostCondition(new PropertyValue(person, "position", 3));
        openDoor.addPostCondition(new PropertyValue(door, "isOpened", 1));
        
        world.addPossibleAction(openDoor);
        world.addPossibleAction(closeDoor);
        
        //------------------//
        
        //--- Demo Scenrario ---//
        
        final Scenario scenario = new Scenario();
        scenario.add(new And(new PropertyValue(person, "position", 2), new PropertyValue(door, "isOpened", 0)));
        scenario.add(new And(new PropertyValue(person, "position", 3), new PropertyValue(door, "isOpened", 0)));
        scenario.add(new And(new PropertyValue(person, "position", 3), new PropertyValue(door, "isOpened", 1)));
        
        //----------------------//
        
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
                new GeneralUI(world, scenario).setVisible(true);
            }
        });
    }
}
