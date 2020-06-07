package org.wecancodeit.library.storage;

import org.wecancodeit.library.models.Author;

public interface AuthorStorage {
    void store(Author authorToStore);
}
