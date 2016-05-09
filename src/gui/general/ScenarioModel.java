/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;
import model.World;
import model.WorldState;
import model.exceptions.NoSuchObjectException;
import model.exceptions.NoSuchPropertyException;
import model.exceptions.NoSuchValueException;
import model.importexport.ScenarioExport;
import model.importexport.ScenarioImport;
import model.observations.Observation;
import model.observations.Scenario;
import org.jdom2.JDOMException;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ScenarioModel {
    private final Scenario scenario;
    private List<String> xmlSources;
    private int instant;
    private final GeneralUI parent;
    private Thread setXMLThread;
    private World world;
    
    public ScenarioModel(Scenario scenario, GeneralUI parent, World world) {
        if(scenario.isEmpty()) {
            throw new IllegalArgumentException("Empty scenario");
        }
        this.scenario = scenario;
        xmlSources = new ArrayList<>(scenario.size());
        ScenarioExport exporter = new ScenarioExport(null, scenario);
        for (int i=0; i<scenario.size(); i++) {
            xmlSources.add(exporter.exportObservation(i));
        }
        instant = 0;
        this.parent = parent;
        this.world = world;
    }
    
    public void setInstant(int instant) {
        if(instant < 0 && instant > scenario.size()-1) {
            throw new IndexOutOfBoundsException(instant+"");
        }
        
        this.instant = instant;
    }
    
    public String getFormula() {
        return scenario.get(instant).toString();
    }
    
    public String getXML() {
        return xmlSources.get(instant);
    }
    
    public int getInstant() {
        return instant;
    }
    
    public void addInstantAfterCurrent() {
        instant++;
        addInstantBeforeCurrent();
    }
    
    public void addInstantBeforeCurrent() {
        addInstant();
    }
    
    private void addInstant() {
        scenario.add(instant, new Observation() {
            @Override
            public boolean isVerified() {
                return true;
            }
            
            @Override
            public boolean isVerifiedIn(WorldState state) {
                return true;
            }

            @Override
            public String toString() {
                return "";
            }
        });
        
        xmlSources.add(instant, "");
    }
    
    public void setXML(final String xml) {
        xmlSources.set(instant, xml);
        
        if(setXMLThread != null) {
            setXMLThread.interrupt();
        }
        
        setXMLThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Observation newObs;
                try {
                    //Thread.sleep(1000); // Wait 1 second before starting to process XML
                    ScenarioImport importer = new ScenarioImport(world);
                    newObs = importer.importOne(xml);
                } catch (InterruptedException e) {
                    return;
                } catch (JDOMException e) {
                    parent.xmlSyntaxError();
                    return;
                } catch (IOException e) {
                    parent.promptError("Internal error in XML parsing.\nUnexpected behavior may happen.", "Error");
                    return;
                } catch (NoSuchValueException e) {
                    parent.xmlValueError(messageStartsWithNull(e) ?
                            "'value' attribute is missing for condition over '"+e.getObject()+"."+e.getProperty()+"'" :
                            e.getMessage());
                    return;
                } catch (NoSuchPropertyException e) {
                    parent.xmlValueError(messageStartsWithNull(e) ?
                            "'property' attribute is missing for condition over' "+e.getObject()+"'" :
                            e.getMessage());
                    return;
                } catch (NoSuchObjectException e) {
                    parent.xmlValueError(messageStartsWithNull(e) ?
                            "'object' attribute missing in condition" :
                            e.getMessage());
                    return;
                }
                
                if(!Thread.interrupted()) {
                    synchronized(scenario) {
                        scenario.set(instant, newObs);
                    }
                    parent.newFormulaAvailable(instant);
                }
            }
        });
        
        SwingUtilities.invokeLater(setXMLThread);
    }
    
    public int getMaxInstant() {
        return scenario.size()-1;
    }

    public void swapInstantWithNext() {
        Collections.swap(scenario, instant, instant+1);
        Collections.swap(xmlSources, instant, instant+1);
        instant++;
    }

    public void swapInstantWithPrevious() {
        Collections.swap(scenario, instant-1, instant);
        Collections.swap(xmlSources, instant-1, instant);
        instant--;
    }

    public void removeInstant() {
        scenario.remove(instant);
        xmlSources.remove(instant);
    }
    
    private boolean messageStartsWithNull(Exception e) {
        return e.getMessage().startsWith("'null'");
    }
    
    public Scenario getScenario() {
        return scenario;
    }
}
