package model.importexport;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import model.Action;
import model.PropertyValue;
import model.ObjectProperty;
import model.SysObject;
import model.World;
import model.exceptions.NoSuchObjectException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Object used to import a world from an xml file
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WorldImport {

    private Document xmlFile;
    private Element root;
    private World world;

    public World importAll(Path file) throws IOException, JDOMException {
        world = new World();
        SAXBuilder sxb = new SAXBuilder();
        xmlFile = sxb.build(file.toFile());
        root = xmlFile.getRootElement();

        importObjects(root.getChildren("object"));
        importActions(root.getChildren("action"));

        return world;
    }

    private void importObjects(List<Element> xmlObjects) {
        for (Element xmlObject : xmlObjects) {
            String name = xmlObject.getAttributeValue("name");
            SysObject object = new SysObject(name, world);
            importProperties(object, xmlObject.getChildren("property"));

            world.addObject(object);
        }
    }

    private void importProperties(SysObject object, List<Element> xmlPoperties) {
        for (Element xmlProperty : xmlPoperties) {
            String name = xmlProperty.getAttributeValue("name");
            ObjectProperty property = new ObjectProperty(name);

            importPropertyValues(property, xmlProperty.getChildren("value"));

            object.addProperty(property);
        }
    }

    private void importPropertyValues(ObjectProperty property, List<Element> xmlValues) {
        for (Element xmlValue : xmlValues) {
            String value = xmlValue.getAttributeValue("name");
            Integer intValue = Integer.parseInt(value);
            property.addPossibleValue(intValue);
        }
    }

    private void importActions(List<Element> xmlActions) {
        for (Element xmlAction : xmlActions) {
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
            action.addPostCondition(importCondition(xmlCondition));
        }
    }

    private PropertyValue importCondition(Element xmlCondition) {
        String objectName = xmlCondition.getAttributeValue("object");
        SysObject object = null;
        for (int i = 0; i < world.getObjectCount(); i++) {
            SysObject objectTemp = world.getObjectAt(i);
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
        Integer intWantedValue = Integer.parseInt(wantedValue);
        return new PropertyValue(object, propertyName, intWantedValue);
    }
}
