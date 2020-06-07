package org.wecancodeit.library.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.library.models.HashTag;

import java.util.Optional;

public interface HashTagRepository extends CrudRepository<HashTag, Long> {
    Optional<HashTag> findByName(String hashTagName);
}
