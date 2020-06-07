package org.wecancodeit.library.storage;

import org.junit.jupiter.api.Test;
import org.wecancodeit.library.models.Campus;

import static org.assertj.core.api.Assertions.assertThat;

public class MapCampusStorageTest {
    @Test
    public void shouldStoreCampusInMap() {
        Campus testCampus = new Campus("Test Town");
        CampusStorage underTest = new MapCampusStorage();
        underTest.store(testCampus);
        assertThat(underTest.findAllCampuses()).contains(testCampus);
    }

    @Test
    public void shouldRetrieveSingleCampusByLocation() {
        Campus testCampus1 = new Campus("Test Town");
        Campus testCampus2 = new Campus("Testville");
        CampusStorage underTest = new MapCampusStorage();
        underTest.store(testCampus1);
        underTest.store(testCampus2);
        Campus retrievedCampus1 = underTest.findCampusByLocation("Test Town");
        Campus retrievedCampus2 = underTest.findCampusByLocation("Testville");
        assertThat(retrievedCampus1).isEqualTo(testCampus1);
        assertThat(retrievedCampus2).isEqualTo(testCampus2);
    }
}
