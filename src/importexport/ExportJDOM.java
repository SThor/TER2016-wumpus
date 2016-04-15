package importexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import model.Action;
import model.Condition;
import model.ObjectProperty;
import model.SysObject;
import model.World;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Object used to export a world and scenario to a file
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ExportJDOM {

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
    public ExportJDOM(World world, Path file) {
        this.world = world;
        this.file = file;
    }

    /**
     * Starts the exportation process
     * @throws java.io.IOException Exception if there is a problem with the file opening
     */
    public void exportAll() throws IOException {
        root.addContent(exportObjects());
        root.addContent(exportActions());
        save(file);
    }

    private Element exportObjects() {
        Element objects = new Element("objects");

        for (SysObject object : world.getObjects()) {
            objects.addContent(exportObject(object));
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

        for (ObjectProperty property : object.getProperties()) {
            xmlObject.addContent(exportProperty(property));
        }

        return xmlObject;
    }

    private Element exportProperty(ObjectProperty property) {
        Element xmlProperty = new Element("property");

        Element xmlValues = new Element("values");
        for (String value : property.getPossibleValues()) {
            Element xmlValue = new Element("value");
            xmlValue.setAttribute("name", value);
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
        Element xmlCondition = new Element("condition");
        xmlCondition.setAttribute("object", condition.getObject().getName());
        xmlCondition.setAttribute("property", condition.getPropertyName());
        xmlCondition.setAttribute("wanted_value", condition.getWantedValue());
        return xmlCondition;
    }

    private void save(Path file) throws IOException {
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        out.output(xmlFile, new FileOutputStream(file.toFile()));
    }
}
