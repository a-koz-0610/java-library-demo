package org.wecancodeit.library.storage;

import org.wecancodeit.library.models.Campus;

import java.util.Collection;

public interface CampusStorage {
    Collection<Campus> findAllCampuses();

    void store(Campus campus);

    Campus findCampusByLocation(String campusLocation);
}
