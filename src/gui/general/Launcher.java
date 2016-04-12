/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general;

import java.util.Arrays;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.SysObject;
import model.UniqueList;
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
        
        UniqueList<String> isOpenedValues = new UniqueList<>(Arrays.asList(new String[]{"true", "false"}));
        door.setPossibleValuesOf("isOpened", isOpenedValues);
        
        UniqueList<String> positionValues = new UniqueList<>(Arrays.asList(new String[]{"moving", "up", "atDoor", "sat"}));
        person.setPossibleValuesOf("position", positionValues);
        
        UniqueList<String> isCallingValues = new UniqueList<>(Arrays.asList(new String[]{"true", "false"}));
        person.setPossibleValuesOf("isCalling", isCallingValues);
        
        world.addObject(door);
        world.addObject(person);
        
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
                new GeneralUI(world).setVisible(true);
            }
        });
    }
}
