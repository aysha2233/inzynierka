package dynamicGrid.mapGenerator.map;

/**
 * Created by ArturK on 2016-09-24.
 */
public class PlaceInMapDTO {

    private int placeInMapId;
    private boolean dropAllowed;
    private boolean alreadyDropped;
    private boolean isItMap;//if not then it is Items container

    public int getPlaceInMapId() {
        return placeInMapId;
    }

    public void setPlaceInMapId(final int placeInMapId) {
        this.placeInMapId = placeInMapId;
    }

    public boolean isDropAllowed() {
        return dropAllowed;
    }

    public void setDropAllowed(final boolean dropAllowed) {
        this.dropAllowed = dropAllowed;
    }

    public boolean isAlreadyDropped() {
        return alreadyDropped;
    }

    public void setAlreadyDropped(final boolean alreadyDropped) {
        this.alreadyDropped = alreadyDropped;
    }

    public boolean isItMap() {
        return isItMap;
    }

    public void setIsItMap(final boolean isItMap) {
        this.isItMap = isItMap;
    }

}