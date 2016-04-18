/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general;

import java.util.ArrayList;
import java.util.List;
import model.Observation;
import model.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
class ScenarioView {
    private final Scenario scenario;
    private List<String> xmlSources;
    private int instant;
    private final GeneralUI parent;
    private Thread setXMLThread;
    
    public ScenarioView(Scenario scenario, GeneralUI parent) {
        this.scenario = scenario;
        xmlSources = new ArrayList<>(scenario.size());
        for (Observation observation : scenario) {
            // xmlSource.add(observation.getXML());
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
        scenario.add(instant, null);
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
                Observation newObs = null;
                /* TODO
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
        
        setXMLThread.start();
    }
}
