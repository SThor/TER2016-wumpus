/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;
import model.World;
import model.importexport.ScenarioExport;
import model.importexport.ScenarioImport;
import model.observations.Observation;
import model.observations.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
class ScenarioModel {
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
        System.out.println(scenario.get(instant));
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
                ScenarioImport importer = new ScenarioImport(world);
                Observation newObs = null;
                /*try {
                    newObs = importer.importAll(file)
                } catch (...) {

                } catch (InterruptedException e) {
                    TODO: throw InterruptedException in Observation parser
                    return;
                }*/
                
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
}
