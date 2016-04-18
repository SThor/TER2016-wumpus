package model.importexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import model.Action;
import model.Condition;
import model.Observation;
import model.Operation;
import model.Scenario;
import model.World;
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

    private final World world;
    private final Element root = new Element("scenario");
    private final Document xmlFile = new Document(root);
    private final Path file;
    private final Scenario scenario;

    /**
     * Creates a new Export object
     *
     * @param world World related to the scenario to export
     * @param file Path to the destination file
     * @param scenario Scenario to export
     */
    public ScenarioExport(World world, Path file, Scenario scenario) {
        this.world = world;
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
        root.setAttribute("world", "" + world.hashCode());
        for (Observation observation : scenario) {
            root.addContent(exportObservation(observation));
        }
        save(file);
    }

    private void save(Path file) throws IOException {
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        out.output(xmlFile, new FileOutputStream(file.toFile()));
    }

    public Element exportObservation(int index) {
        return exportObservation(scenario.get(index));
    }

    private Element exportObservation(Observation observation) {
        if (observation instanceof Operation) {

        } else if (observation instanceof Condition) {

        } else if (observation instanceof Action) {

        } else {
            throw new UnknownObservationException();
        }
    }
