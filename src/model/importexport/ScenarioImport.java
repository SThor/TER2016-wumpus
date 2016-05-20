package model.importexport;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import model.Action;
import model.PropertyValue;
import model.SysObject;
import model.World;
import model.exceptions.NoSuchObjectException;
import model.exceptions.UnknownObservationException;
import model.observations.And;
import model.observations.EmptyObservation;
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
    private final World world;

    /**
     * Creates a new import object
     * @param world World in which the scenario import takes place
     */
    public ScenarioImport(World world) {
        this.world = world;
    }

    /**
     * Starts the importation of the scenario to the specified file
     * @param file Path to the xml origin file
     * @return A scenario built from the file
     * @throws IOException Exception thrown if there is a problem reading the file
     * @throws JDOMException Exception thrown if there is a problem with the xml structure of the file
     * @throws java.lang.InterruptedException If the current thread has been interrupted
     */
    public Scenario importAll(Path file) throws IOException, JDOMException, InterruptedException {
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
    
    /**
     * Starts the importation of a single observation
     * @param observationString The xml observation to import
     * @return The observation built from the String
     * @throws JDOMException Exception thrown if there is a problem with the xml structure of the string
     * @throws IOException Exception thrown if there is a problem reading the string
     * @throws java.lang.InterruptedException If the current thread has been interrupted
     */
    public synchronized Observation importOne(String observationString) 
    throws JDOMException, IOException, InterruptedException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        SAXBuilder sxb = new SAXBuilder();
        xmlFile = sxb.build(new StringReader(observationString));
        root = xmlFile.getRootElement();
        
        Observation observation = importObservation(root);

        return observation;
    }

    private Observation importObservation(Element xmlObservation) throws InterruptedException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        switch (xmlObservation.getName()) {
            case "operation":
                return importOperation(xmlObservation);
            case "condition":
                return importCondition(xmlObservation);
            case "action":
                return importAction(xmlObservation);
            case "noObservation":
                return new EmptyObservation();
            default:
                throw new UnknownObservationException();
        }
    }

    private Operation importOperation(Element xmlOperation) throws InterruptedException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        Operation operation;
        List<Observation> observations = new ArrayList<>();
        for (Element xmlObservation : xmlOperation.getChildren()) {
            observations.add(importObservation(xmlObservation));
        }
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        Observation[] observationsArray = new Observation[observations.size()];
        for (int i = 0; i < observationsArray.length; i++) {
            observationsArray[i] = observations.get(i);
        }
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

    private PropertyValue importCondition(Element xmlCondition) throws InterruptedException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
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
        Integer intValue = Integer.parseInt(value);
        return new PropertyValue(object, propertyName, intValue);
    }

    private Action importAction(Element xmlAction) throws InterruptedException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
