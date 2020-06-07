package org.wecancodeit.library.storage;

import org.junit.jupiter.api.Test;
import org.wecancodeit.library.models.Author;
import org.wecancodeit.library.storage.repositories.AuthorRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthorStorageJpaImplTest {
    @Test
    public void shouldStoreAuthor() {
        AuthorRepository authorRepo = mock(AuthorRepository.class);
        AuthorStorage underTest = new AuthorStorageJpaImpl(authorRepo);
        Author testAuthor = new Author("Tester", "MacTeston");
        underTest.store(testAuthor);
        verify(authorRepo).save(testAuthor);
    }
}
