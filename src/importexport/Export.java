package importexport;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Action;
import model.Condition;
import model.ObjectObservation;
import model.ObjectProperty;
import model.SysObject;
import model.World;

/**
 * Object used to export the world and scenario to an xml file
 * @author Paul Givel et Guillaume Hartenstein
 */
public class Export {
    private World world;
    private Path file;
    private List<String> lines;
    
    /**
     * Creates a new Export object
     * @param world World to export
     * @param file Path to the destination file
     */
    public Export(World world, Path file){
        this.world = world;
        this.file = file;
    }
    
    private void exportWorld(){
        lines.add("<world>");
        
        lines.add("<objects>");
        for(SysObject object : world.getObjects()){
            exportObject(object);
        }
        lines.add("</objects>");
        
        lines.add("<action>");
        for(Action action : world.getPossibleActions()){
            exportAction(action);
        }
        lines.add("</actions>");
        
        lines.add("<observations>");
        for(ObjectObservation observation : world.getObservations()){
            exportObservation(observation);
        }
        lines.add("</observations>");
        
        lines.add("</world>");
    }
    
    /**
     * Starts the exportation process
     */
    public void export(){
        exportWorld();
        write();
    }
        
    private void write(){        
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exportObject(SysObject object) {
        lines.add("<object name=\""+object.toString()+"\">");
        for (ObjectProperty property : object.getProperties()) {
            exportProperty(property);
        }
        lines.add("</object>");
    }

    private void exportProperty(ObjectProperty property) {
        lines.add("<property name=\""+property.getName()+"\">");
        for (String value : property.getPossibleValues()) {
            lines.add("<value name=\""+value+"\">");
        }
        lines.add("</object>");
    }

    private void exportAction(Action action) {
        lines.add("<action name=\""+action.getName()+"\">");
        
        lines.add("<preconditions>");
        for (Condition precondition : action.getPreConditions()) {
            exportCondition(precondition);
        }
        lines.add("</preconditions>");
        
        lines.add("<postconditions>");
        for (Condition postcondition : action.getPostConditions()) {
            exportCondition(postcondition);
        }
        lines.add("</postconditions>");
        
        lines.add("</action>");
    }

    private void exportCondition(Condition condition) {
        String object = condition.getObject().toString();
        String property = condition.getPropertyName();
        String value = condition.getWantedValue();
        lines.add("<condition object=\""+object+"\" property=\""+property+"\" wantedValue=\""+value+"\">");
    }

    private void exportObservation(ObjectObservation observation) {
        lines.add("<observation>");
        //TODO        
        lines.add("</observation>");
    }
    
    
}
