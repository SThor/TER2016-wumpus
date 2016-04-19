package model.importexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import model.Action;
import model.Condition;
import model.observations.Observation;
import model.World;
import model.exceptions.UnknownObservationException;
import model.exceptions.UnknownOperationException;
import model.observations.And;
import model.observations.Not;
import model.observations.Operation;
import model.observations.Or;
import model.observations.Scenario;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Object used to export a scenario to an xml file
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ScenarioExport {

    private final Element root = new Element("scenario");
    private final Document xmlFile = new Document(root);
    private final Path file;
    private final Scenario scenario;
    private final XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());

    /**
     * Creates a new Export object
     *
     * @param world World related to the scenario to export
     * @param file Path to the destination file
     * @param scenario Scenario to export
     */
    public ScenarioExport(World world, Path file, Scenario scenario) {
        this.file = file;
        this.scenario = scenario;
    }

    /**
     * Starts the exportation process
     *
     * @throws java.io.IOException Exception if there is a problem with the file
     * opening
     */
    public void exportAll() throws IOException {
        for (Observation observation : scenario) {
            root.addContent(exportObservation(observation));
        }
        save(file);
    }

    private void save(Path file) throws IOException {
        out.output(xmlFile, new FileOutputStream(file.toFile()));
    }

    /**
     * Exports a single observation to a String
     *
     * @param instant Instant of the desired observation
     * @return
     */
    public String exportObservation(int instant) {
        Element xmlObservation = exportObservation(scenario.get(instant));
        return out.outputString(xmlObservation);
    }

    private Element exportObservation(Observation observation) {
        if (observation instanceof Operation) {
            return exportOperation((Operation) observation);
        } else if (observation instanceof Condition) {
            return exportCondition((Condition) observation);
        } else if (observation instanceof Action) {
            return exportAction((Action) observation);
        } else {
            throw new UnknownObservationException();
        }
    }

    private Element exportOperation(Operation operation) {
        Element xmlOperation = new Element("operation");
        List<Observation> observations = operation.getObservations();
        
        for (Observation observation : observations) {
            xmlOperation.addContent(exportObservation(observation));
        }

        String[] fullType = operation.getClass().getName().split(".");
        String type = fullType[fullType.length - 1];
        type = type.toLowerCase();
        xmlOperation.setAttribute("type", type);
        
        return xmlOperation;
    }

    private Element exportCondition(Condition condition) {
        Element xmlCondition = new Element("condition");
        xmlCondition.setAttribute("object", condition.getObject().getName());
        xmlCondition.setAttribute("property", condition.getPropertyName());
        xmlCondition.setAttribute("value", condition.getWantedValue());
        return xmlCondition;
    }

    private Element exportAction(Action action) {
        throw new UnsupportedOperationException("Observation of actions is not supported yet.");
    }

}
