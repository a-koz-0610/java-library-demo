package org.wecancodeit.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.library.models.Author;
import org.wecancodeit.library.models.Book;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.models.HashTag;
import org.wecancodeit.library.storage.AuthorStorage;
import org.wecancodeit.library.storage.BookStorage;
import org.wecancodeit.library.storage.CampusStorage;
import org.wecancodeit.library.storage.HashTagStorage;

@Component
public class Populator implements CommandLineRunner {

    private final CampusStorage campusStorage;
    private final AuthorStorage authorStorage;
    private final BookStorage bookStorage;
    private final HashTagStorage hashTagStorage;

    public Populator(CampusStorage campusStorage, AuthorStorage authorStorage, BookStorage bookStorage, HashTagStorage hashTagStorage) {
        this.campusStorage = campusStorage;
        this.authorStorage = authorStorage;
        this.bookStorage = bookStorage;
        this.hashTagStorage = hashTagStorage;
    }

    @Override
    public void run(String... args) {

        HashTag java = new HashTag("Java");
        hashTagStorage.store(java);
        HashTag cSharp = new HashTag("C Sharp");
        hashTagStorage.store(cSharp);
        Campus columbus = new Campus("Columbus");
        campusStorage.store(columbus);
        Campus cleveland = new Campus("Cleveland");
        campusStorage.store(cleveland);
        Campus theMoon = new Campus("THE MOON");
        campusStorage.store(theMoon);
        Author kathy = new Author("Kathy", "Sierra");
        authorStorage.store(kathy);
        Author bates = new Author("Burt", "Bates");
        authorStorage.store(bates);
        Author kent = new Author("Kent", "Beck");
        authorStorage.store(kent);
        Author martin = new Author("Martin", "Fowler");
        authorStorage.store(martin);
        Author micah = new Author("Micah", "Martin");
        authorStorage.store(micah);
        Author neil = new Author("Neil", "Armstrong");
        authorStorage.store(neil);
        Book headFirstJava = new Book("Head First Java", "A good book to learn Java with.", columbus, kathy, bates);
        bookStorage.store(headFirstJava);
        Book tddByExample = new Book("Test Driven Development By Example",
                "The first book on TDD and still one of the best.", columbus, kent);
        bookStorage.store(tddByExample);
        Book refactoring = new Book("Refactoring",
                "The first book to catalog the many refactorings available to use to address code smells.", columbus,
                martin);
        bookStorage.store(refactoring);
        Book apppCSharp = new Book("Agile Principles Patterns and Practices in C#",
                "A classic book, 'refactored' for C#", cleveland, micah);
        bookStorage.store(apppCSharp);
        Book onTheMoon = new Book("On the Moon", "A retrospective from those who have been there.", theMoon, neil);
        bookStorage.store(onTheMoon);
    }
}
