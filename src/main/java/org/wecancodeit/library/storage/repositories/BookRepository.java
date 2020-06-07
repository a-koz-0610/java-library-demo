package org.wecancodeit.library.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.library.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
