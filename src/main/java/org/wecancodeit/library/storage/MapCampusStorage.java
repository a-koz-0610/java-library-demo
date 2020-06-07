package org.wecancodeit.library.storage;

import org.wecancodeit.library.models.Campus;

import java.util.Collection;
import java.util.HashMap;


public class MapCampusStorage implements CampusStorage {
    private final HashMap<String, Campus> campuses;

    public MapCampusStorage() {
        campuses = new HashMap<>();
    }

    @Override
    public Collection<Campus> findAllCampuses() {
        return campuses.values();
    }

    @Override
    public void store(Campus campus) {
        campuses.put(campus.getLocation(), campus);
    }

    @Override
    public Campus findCampusByLocation(String campusLocation) {
        return campuses.get(campusLocation);
    }
}
