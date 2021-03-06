package ApplicationData;

import org.eclipse.californium.core.CoapClient;

import java.util.ArrayList;

import Simulets.Simulet;
import TriggerSimulets.TriggerSimulet;
import dynamicGrid.mapGenerator.map.MapDTO;

/**
 * Created by ArturK on 2016-09-24.
 */
public class ApplicationData {

    private ArrayList<MapDTO> allMaps;
    private ArrayList<Simulet> simulets;
    private ArrayList<TriggerSimulet> triggers;


    public ApplicationData() {
        allMaps = new ArrayList<>();
        simulets = new ArrayList<>();
        triggers = new ArrayList<>();
    }

    public void addMap(final MapDTO newMap) {
        if (newMap != null) {
            allMaps.add(newMap);
        }
    }

    public ArrayList<MapDTO> getAllMaps() {
        return allMaps;
    }

    public ArrayList<Simulet> getSimulets() {
        return simulets;
    }

    public void addSimulet(final Simulet simulet) { //TODO dodawanie simuletów powinno być tą metodą, zmiana na pozniej
        this.simulets.add(simulet);
    }

    public void removeSimulet(final String simuletId) {
        //TODO gdy utrace łączność usuwam simulet
    }

    public void removeAllSimulets() {
        simulets = new ArrayList<>();
    }

    public ArrayList<TriggerSimulet> getTriggers() {
        return triggers;
    }
}
