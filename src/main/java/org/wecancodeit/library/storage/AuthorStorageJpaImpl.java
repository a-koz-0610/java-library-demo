package org.wecancodeit.library.storage;

import org.springframework.stereotype.Service;
import org.wecancodeit.library.models.Author;
import org.wecancodeit.library.storage.repositories.AuthorRepository;

@Service
public class AuthorStorageJpaImpl implements AuthorStorage {
    private final AuthorRepository authorRepository;

    public AuthorStorageJpaImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void store(Author authorToStore) {
        authorRepository.save(authorToStore);
    }
}
