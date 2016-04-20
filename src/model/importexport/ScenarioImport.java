package model.importexport;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import model.Action;
import model.Condition;
import model.SysObject;
import model.World;
import model.exceptions.NoSuchObjectException;
import model.exceptions.UnknownObservationException;
import model.observations.And;
import model.observations.Not;
import model.observations.Observation;
import model.observations.Operation;
import model.observations.Or;
import model.observations.Scenario;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Object used to import a scenario from an xml file
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ScenarioImport {

    private Document xmlFile;
    private Element root;
    private Scenario scenario;
    private World world;

    public ScenarioImport(World world) {
        this.world = world;
    }

    public Scenario importAll(Path file) throws IOException, JDOMException {
        scenario = new Scenario();
        SAXBuilder sxb = new SAXBuilder();
        xmlFile = sxb.build(file.toFile());
        root = xmlFile.getRootElement();

        List<Element> xmlScenario = root.getChildren();
        for (Element observation : xmlScenario) {
            scenario.add(importObservation(observation));
        }

        return scenario;
    }
    
    public Scenario importAll(String file) throws JDOMException, IOException{
        scenario = new Scenario();
        SAXBuilder sxb = new SAXBuilder();
        xmlFile = sxb.build(new StringReader(file));
        root = xmlFile.getRootElement();

        List<Element> xmlScenario = root.getChildren();
        for (Element observation : xmlScenario) {
            scenario.add(importObservation(observation));
        }

        return scenario;
    }

    private Observation importObservation(Element xmlObservation) {
        switch (xmlObservation.getName()) {
            case "operation":
                return importOperation(xmlObservation);
            case "condition":
                return importCondition(xmlObservation);
            case "action":
                return importAction(xmlObservation);
            default:
                throw new UnknownObservationException();
        }
    }

    private Operation importOperation(Element xmlOperation) {
        Operation operation;
        List<Observation> observations = new ArrayList<>();
        for (Element xmlObservation : xmlOperation.getChildren()) {
            observations.add(importObservation(xmlObservation));
        }
        Observation[] observationsArray = (Observation[]) observations.toArray();
        switch (xmlOperation.getAttributeValue("type")) {
            case "and":
                operation = new And(observationsArray);
                break;
            case "or":
                operation = new Or(observationsArray);
                break;
            case "not":
                operation = new Not(observations.get(0));
                break;
            default:
                operation = null;
                break;
        }
        return operation;
    }

    private Condition importCondition(Element xmlCondition) {
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

        if (object == null) {
            throw new NoSuchObjectException(objectName);
        }

        String propertyName = xmlCondition.getAttributeValue("property");
        String value = xmlCondition.getAttributeValue("value");
        return new Condition(object, propertyName, value);
    }

    private Action importAction(Element xmlAction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
