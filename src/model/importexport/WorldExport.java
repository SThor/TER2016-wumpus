package model.importexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import model.Action;
import model.Condition;
import model.Equality;
import model.PropertyValue;
import model.ObjectProperty;
import model.SysObject;
import model.World;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Object used to export a world to an xml file
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WorldExport {

    private final World world;
    private final Element root = new Element("world");
    private final Document xmlFile = new Document(root);
    private final Path file;

    /**
     * Creates a new Export object
     *
     * @param world World to export
     * @param file Path to the destination file
     */
    public WorldExport(World world, Path file) {
        this.world = world;
        this.file = file;
    }

    /**
     * Starts the exportation process
     *
     * @throws java.io.IOException Exception if there is a problem with the file
     * opening
     */
    public void exportAll() throws IOException {
        root.addContent(exportObjects());
        root.addContent(exportActions());
        save(file);
    }

    private Element exportObjects() {
        Element objects = new Element("objects");

        for (int i = 0; i < world.getObjectCount(); i++) {
            objects.addContent(exportObject(world.getObjectAt(i)));
        }

        return objects;
    }

    private Element exportActions() {
        Element actions = new Element("actions");

        for (Action action : world.getPossibleActions()) {
            actions.addContent(exportAction(action));
        }

        return actions;
    }

    private Element exportObject(SysObject object) {
        Element xmlObject = new Element("object");
        xmlObject.setAttribute("name", object.getName());

        Element xmlProperties = new Element("properties");
        for (int i = 0; i < object.getPropertyCount(); i++) {
            xmlProperties.addContent(exportProperty(object.getPropertyAt(i)));
        }
        xmlObject.addContent(xmlProperties);

        return xmlObject;
    }

    private Element exportProperty(ObjectProperty property) {
        Element xmlProperty = new Element("property");
        xmlProperty.setAttribute("name", property.getName());

        Element xmlValues = new Element("values");
        for (Integer value : property.getPossibleValues()) {
            Element xmlValue = new Element("value");
            xmlValue.setAttribute("name", value.toString());
            xmlValues.addContent(xmlValue);
        }
        xmlProperty.addContent(xmlValues);

        return xmlProperty;
    }

    private Element exportAction(Action action) {
        Element xmlAction = new Element("action");
        xmlAction.setAttribute("name", action.getName());

        Element xmlPre = new Element("preconditions");
        for (Condition preCondition : action.getPreConditions()) {
            xmlPre.addContent(exportCondition(preCondition));
        }
        xmlAction.addContent(xmlPre);

        Element xmlPost = new Element("postconditions");
        for (Condition postCondition : action.getPostConditions()) {
            xmlPost.addContent(exportCondition(postCondition));
        }
        xmlAction.addContent(xmlPost);

        return xmlAction;
    }

    private Element exportCondition(Condition condition) {
        Element xmlCondition;
        if (condition instanceof PropertyValue) {
            PropertyValue pv = (PropertyValue) condition;
            
            xmlCondition = new Element("propertyValue");
            xmlCondition.setAttribute("object", pv.getObject().getName());
            xmlCondition.setAttribute("property", pv.getPropertyName());
            xmlCondition.setAttribute("wanted_value", pv.getWantedValue().toString());
        } else if (condition instanceof Equality) {
            Equality e = (Equality) condition;
            
            xmlCondition = new Element("equality");
            xmlCondition.setAttribute("object", e.getObject().getName());
            xmlCondition.setAttribute("property", e.getPropertyName());
            xmlCondition.setAttribute("second_object", e.getSecondObject().getName());
            xmlCondition.setAttribute("second_property", e.getSecondPropertyName());
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        return xmlCondition;
    }

    private void save(Path file) throws IOException {
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        out.output(xmlFile, new FileOutputStream(file.toFile()));
    }
}
