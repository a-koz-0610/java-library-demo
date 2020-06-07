package org.wecancodeit.library.storage;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.storage.repositories.CampusRepository;

import java.util.Collection;

@Service
public class CampusStorageJpaImpl implements CampusStorage {
    private final CampusRepository campusRepository;

    public CampusStorageJpaImpl(CampusRepository campusRepository) {

        this.campusRepository = campusRepository;
    }

    @Override
    public Collection<Campus> findAllCampuses() {
        return (Collection<Campus>) campusRepository.findAll();
    }

    @Override
    public void store(Campus campus) {
        campusRepository.save(campus);
    }

    @Override
    public Campus findCampusByLocation(String campusLocation) {
        Campus retrievedCampus;
        try {
            retrievedCampus = campusRepository.findByLocation(campusLocation).get();
        } catch (Exception e) {
            throw new CampusNotFoundException(e.getMessage());
        }
        return retrievedCampus;
    }
}
