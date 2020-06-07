package org.wecancodeit.library.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.library.models.Campus;

import java.util.Optional;

public interface CampusRepository extends CrudRepository<Campus, Long> {

    Optional<Campus> findByLocation(String campusLocation);
}
