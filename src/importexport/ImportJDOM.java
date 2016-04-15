package importexport;

import java.io.IOException;
import java.nio.file.Path;
import model.Action;
import model.Condition;
import model.ObjectProperty;
import model.SysObject;
import model.World;
import model.exceptions.NoSuchObjectException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Object used to import a world and scenario from a file
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ImportJDOM {

    private Document xmlFile;
    private Element root;
    private Path file;
    private World world;

    public World importAll() throws IOException, JDOMException {
        world = new World();
        SAXBuilder sxb = new SAXBuilder();
        xmlFile = sxb.build(file.toFile());
        root = xmlFile.getRootElement();

        importObjects(root.getChild("objects"));
        importActions(root.getChild("actions"));

        return world;
    }

    private void importObjects(Element xmlObjects) {
        for (Element xmlObject : xmlObjects.getChildren()) {
            String name = xmlObject.getAttributeValue("name");
            SysObject object = new SysObject(name, world);
            importProperties(object, xmlObject.getChild("properties"));

            world.addObject(object);
        }
    }

    private void importProperties(SysObject object, Element xmlPoperties) {
        for (Element xmlProperty : xmlPoperties.getChildren()) {
            String name = xmlProperty.getAttributeValue("name");
            ObjectProperty property = new ObjectProperty(name);

            importPropertyValues(property, xmlProperty.getChild("values"));

            object.addProperty(property);
        }
    }

    private void importPropertyValues(ObjectProperty property, Element xmlValues) {
        for (Element xmlValue : xmlValues.getChildren()) {
            String value = xmlValue.getAttributeValue("name");
            property.addPossibleValue(value);
        }
    }

    private void importActions(Element xmlActions) {
        for (Element xmlAction : xmlActions.getChildren()) {
            String name = xmlAction.getAttributeValue("name");
            Action action = new Action(name);

            importPreconditions(action, xmlAction.getChild("preconditions"));
            importPostconditions(action, xmlAction.getChild("postconditions"));

            world.addPossibleAction(action);
        }
    }

    private void importPreconditions(Action action, Element xmlPreconditions) {
        for (Element xmlCondition : xmlPreconditions.getChildren()) {
            action.addPreCondition(importCondition(xmlCondition));
        }
    }

    private void importPostconditions(Action action, Element xmlPostconditions) {
        for (Element xmlCondition : xmlPostconditions.getChildren()) {
            action.addPreCondition(importCondition(xmlCondition));
        }
    }

    private Condition importCondition(Element xmlCondition) {
        String objectName = xmlCondition.getAttributeValue("object");
        SysObject object = null;
        for (SysObject objectTemp : world.getObjects()) {
            String tempName = objectTemp.getName();
            if (tempName.equals(objectName)) {
                object = objectTemp;
                break;
            }
        }
        
        if(object==null){
            throw new NoSuchObjectException(objectName);
        }

        String propertyName = xmlCondition.getAttributeValue("property");
        String wantedValue = xmlCondition.getAttributeValue("wanted_value");
        return new Condition(object, propertyName, wantedValue);
    }
}
