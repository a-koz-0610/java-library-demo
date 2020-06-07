package org.wecancodeit.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.library.models.Book;
import org.wecancodeit.library.models.HashTag;
import org.wecancodeit.library.storage.BookStorage;
import org.wecancodeit.library.storage.repositories.HashTagRepository;

import java.util.Optional;

@Controller
public class BookController {
    private final BookStorage bookStorage;
    private HashTagRepository hashTagRepo;

    public BookController(BookStorage bookStorage, HashTagRepository hashTagRepo) {
        this.bookStorage = bookStorage;
        this.hashTagRepo = hashTagRepo;
    }

    @RequestMapping("/books/{id}")
    public String displayBook(@PathVariable Long id, Model model) {
        Book retrievedBook = bookStorage.findBookById(id);
        model.addAttribute("book", retrievedBook);
        return "book-view";
    }

    @PostMapping("/book/{id}/add-hashtag")
    public String addHashTagToBook(@RequestParam String hashTagName, @PathVariable Long id) {
        HashTag hashTagToAddToBook;
        Optional<HashTag> hashTagToAddOpt = hashTagRepo.findByName(hashTagName);
        if (hashTagToAddOpt.isEmpty()) {
            hashTagToAddToBook = new HashTag(hashTagName);
            hashTagRepo.save(hashTagToAddToBook);
        } else {
            hashTagToAddToBook = hashTagToAddOpt.get();
        }
        Book bookToAddHashTagTo = bookStorage.findBookById(id);
        bookToAddHashTagTo.addHashTag(hashTagToAddToBook);
        bookStorage.store(bookToAddHashTagTo);
        return "redirect:/books/" + id;
    }
}
