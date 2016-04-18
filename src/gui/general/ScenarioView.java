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
    private Scenario scenario;
    private List<String> xmlSources;
    private int instant;
    private GeneralUI parent;
    private Thread setXMLThread;
    
    public ScenarioView(Scenario scenario, GeneralUI parent) {
        this.scenario = scenario;
        xmlSources = new ArrayList<>(scenario.size());
        for (Observation observation : scenario) {
            // xmlSource.add(observation.getXML());
        }
        instant = 0;
        this.parent = parent;
        /*setXMLThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized(scenario) {
                        this.scenario.set(instant, xmlSources.get(instant).toFormula());
                    }
                } catch (...) {

                }
        
                parent.newFormulaAvailable(instant);
            }
        };*/
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
    
    public void setXML(String xml) {
        xmlSources.set(instant, xml);
    }
}
