package org.wecancodeit.library.storage;

import org.springframework.stereotype.Service;
import org.wecancodeit.library.models.Book;
import org.wecancodeit.library.storage.repositories.BookRepository;

@Service
public class BookStorageJpaImpl implements BookStorage {
    private final BookRepository bookRepository;

    public BookStorageJpaImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findBookById(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void store(Book bookToStore) {
        bookRepository.save(bookToStore);
    }
}
