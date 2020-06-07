package org.wecancodeit.library.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.library.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
