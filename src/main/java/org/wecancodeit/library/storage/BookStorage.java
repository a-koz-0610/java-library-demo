package org.wecancodeit.library.storage;

import org.wecancodeit.library.models.Book;

public interface BookStorage {
    Book findBookById(long id);

    void store(Book bookToStore);
}
