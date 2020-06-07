package org.wecancodeit.library.storage;

import org.junit.jupiter.api.Test;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.storage.repositories.CampusRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CampusStorageJpaImplTest {

    @Test
    public void shouldFindAllCampuses() {
        CampusRepository mockCampusRepo = mock(CampusRepository.class);
        Campus testCampus = new Campus("Test Town");
        CampusStorage underTest = new CampusStorageJpaImpl(mockCampusRepo);
        when(mockCampusRepo.findAll()).thenReturn(Collections.singletonList(testCampus));
        underTest.store(testCampus);
        verify(mockCampusRepo).save(testCampus);
        assertThat(underTest.findAllCampuses()).contains(testCampus);
    }

    @Test
    public void shouldRetrieveSingleCampusByLocation() {
        CampusRepository mockCampusRepo = mock(CampusRepository.class);
        Campus testCampus1 = new Campus("Test Town");
        Campus testCampus2 = new Campus("Testville");
        CampusStorage underTest = new CampusStorageJpaImpl(mockCampusRepo);
        underTest.store(testCampus1);
        underTest.store(testCampus2);
        Optional<Campus> testCampus1Optional = Optional.of(testCampus1);
        when(mockCampusRepo.findByLocation("Test Town")).thenReturn(testCampus1Optional);

        Optional<Campus> testCampus2Optional = Optional.of(testCampus2);
        when(mockCampusRepo.findByLocation("Testville")).thenReturn(testCampus2Optional);

        Campus retrievedCampus1 = underTest.findCampusByLocation("Test Town");
        Campus retrievedCampus2 = underTest.findCampusByLocation("Testville");
        assertThat(retrievedCampus1).isEqualTo(testCampus1);
        assertThat(retrievedCampus2).isEqualTo(testCampus2);
    }
}
