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
import model.Action;
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
    
    public ScenarioModel(Scenario scenario, GeneralUI parent) {
        if(scenario.isEmpty()) {
            throw new IllegalArgumentException("Empty scenario");
        }
        this.scenario = scenario;
        xmlSources = new ArrayList<>(scenario.size());
        for (int i=0; i<scenario.size(); i++) {
            // xmlSource.add(observation.getXML());
            xmlSources.add("TODO_"+i);
        }
        instant = 0;
        this.parent = parent;
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
                return false;
            }
        });
        
        xmlSources.add(instant, "Generated_Test");
    }
    
    public void setXML(final String xml) {
        xmlSources.set(instant, xml);
        
        if(setXMLThread != null) {
            setXMLThread.interrupt();
        }
        
        setXMLThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Observation newObs = new Action("Foo_Bar");// Remove this line
                /* TODO
                Observation newObs = null;
                try {
                    newObs = xml.parseObservation();
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
