package dynamicGrid;

import java.net.URI;

import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

import Simulets.ActionSimulet;
import Simulets.SimuletsState;
import TriggerSimulets.EventSimulet;
import dynamicGrid.mapGenerator.map.MapDTO;
import dynamicGrid.mapGenerator.map.MapDTOBuilder;
import dynamicGrid.mapGenerator.map.PlaceInMapDTO;

public class DynamicGridUtils {
    private static final String TRIGGER = "TRIGGER";
    private static final String SIMULET = "SIMULET";
    public static final String PAUSE_SIMULET = "PAUSE_SIMULET";
    private static final String NONE = "NONE";

    public static void reorder(LinkedList<PlaceInMapDTO> list, int indexFrom, int indexTwo) {
//        if (list.get(indexTwo).isDropAllowed()) {
        PlaceInMapDTO obj = list.remove(indexFrom);
        list.add(indexTwo, obj);
//        }
    }

    public static void swap(LinkedList<PlaceInMapDTO> list, MapDTO map,
                            ArrayList<ActionSimulet> listOfActionSimulets, ArrayList<EventSimulet> triggers,
                            int firstIndex, int secondIndex) {
        final ArrayList placesTypes = map.getPlacesTypes();
        final String firstType = checkType(list.get(firstIndex), triggers, listOfActionSimulets);
        final String secondType = checkType(list.get(secondIndex), triggers, listOfActionSimulets);
        if ((placesTypes.get(firstIndex).equals(MapDTOBuilder.TRIGGER_PLACE) && placesTypes.get(secondIndex).equals(MapDTOBuilder.TRIGGER_PLACE) && !firstType.equals(SIMULET)&& !secondType.equals(SIMULET)) ||
                (placesTypes.get(firstIndex).equals(MapDTOBuilder.SIMULET_PLACE) && placesTypes.get(secondIndex).equals(MapDTOBuilder.SIMULET_PLACE) && !firstType.equals(TRIGGER)&& !secondType.equals(TRIGGER))) {
            //Trigger podmien z triggerem lub simulet z simuletem
            PlaceInMapDTO secondObject = list.get(secondIndex);
//            list.set(firstIndex, secondObject);
            list.set(secondIndex, list.set(firstIndex, secondObject));
        } else if ((placesTypes.get(firstIndex).equals(MapDTOBuilder.CONTAINER) && placesTypes.get(secondIndex).equals(MapDTOBuilder.TRIGGER_PLACE) && firstType.equals(TRIGGER)) ||
                (placesTypes.get(firstIndex).equals(MapDTOBuilder.CONTAINER) && placesTypes.get(secondIndex).equals(MapDTOBuilder.SIMULET_PLACE) && firstType.equals(SIMULET)) ||
                (placesTypes.get(firstIndex).equals(MapDTOBuilder.PAUSE_SIMULET_PLACE) && placesTypes.get(secondIndex).equals(MapDTOBuilder.SIMULET_PLACE) && firstType.equals(PAUSE_SIMULET))) {

            PlaceInMapDTO secondObject = list.get(secondIndex);
            PlaceInMapDTO firstObject = list.get(firstIndex);
            SimuletsState copy = new SimuletsState(firstObject.getSimuletState().getStateId(),
                    firstObject.getSimuletState().getMiniature(),
                    firstObject.getSimuletState().getHighlightedMiniature(),
                    firstObject.getSimuletState().getSimuletsURI(),
                    firstObject.getSimuletState().getEventType());
            secondObject.setSimuletState(copy);
            list.set(secondIndex, list.set(firstIndex, secondObject));
        }

}

    public static float getViewX(View view) {
        return Math.abs((view.getRight() - view.getLeft()) / 2);
    }

    public static float getViewY(View view) {
        return Math.abs((view.getBottom() - view.getTop()) / 2);
    }

    private static String checkType(final PlaceInMapDTO place, ArrayList<EventSimulet> triggers, ArrayList<ActionSimulet> actionSimulets) {
        if (isSimulet(place, actionSimulets, triggers)) {
            return SIMULET;
        } else if (isTrigger(place, triggers)) {
            return TRIGGER;
        } else if(isPauseSimulet(place)){
          return PAUSE_SIMULET;
        } else {
            return NONE;
        }
    }

    private static boolean isPauseSimulet(PlaceInMapDTO place) {
        if(place.getSimuletState()==null) return false;
        return PAUSE_SIMULET.equals(place.getSimuletState().getStateId());

    }

    private static boolean isTrigger(final PlaceInMapDTO place, ArrayList<EventSimulet> triggers) {
        if(place.getSimuletState()==null) return false;
        final URI triggersUri = place.getSimuletState().getSimuletsURI();
        for (EventSimulet trigger : triggers) {
            if (trigger.getUriOfTrigger().equals(triggersUri)) return true;
        }
        return false;
    }

    private static boolean isSimulet(final PlaceInMapDTO place, ArrayList<ActionSimulet> actionSimulets, ArrayList<EventSimulet> eventSimulets) {
        if(place.getSimuletState()==null) return false;
        final URI simuletsUri = place.getSimuletState().getSimuletsURI();
        for (ActionSimulet actionSimulet : actionSimulets) {
            if (actionSimulet.getUriOfSimulet().equals(simuletsUri)) return true;
        }
        for (EventSimulet eventSimulet : eventSimulets){
            if(eventSimulet.getUriOfTrigger().equals(simuletsUri) && "action".equals(place.getSimuletState().getEventType())) return true;
        }
        return false;
    }
}
