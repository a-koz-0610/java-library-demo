package org.wecancodeit.library.rest.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.library.models.Book;
import org.wecancodeit.library.models.HashTag;
import org.wecancodeit.library.storage.BookStorage;
import org.wecancodeit.library.storage.repositories.BookRepository;
import org.wecancodeit.library.storage.repositories.HashTagRepository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
public class BookRestController {

    @Resource
    private BookRepository bookRepo;

    @Resource
    private BookStorage bookStorage;

    @Resource
    private HashTagRepository hashTagRepo;

    @GetMapping("/rest-books")
    public Collection<Book> getBooks() {
        return (Collection<Book>) bookRepo.findAll();
    }

    @GetMapping("/rest-books/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        return bookRepo.findById(id);
    }

    @PostMapping("/rest-books/{id}/add-hashtag")
    public Optional<Book> addHashTagToBook(@RequestBody String body, @PathVariable Long id) throws JSONException {
        JSONObject newHashTag = new JSONObject(body);
        String hashTagName = newHashTag.getString("hashTagName");
        Optional<HashTag> hashTagToAddOpt = hashTagRepo.findByName(hashTagName);
        if (hashTagToAddOpt.isPresent()) {
           // HashTag hashTagToAdd = new HashTag(hashTagName);
            Book bookToAddHashTagTo = bookStorage.findBookById(id);
            bookToAddHashTagTo.addHashTag(hashTagToAddOpt.get());
            bookStorage.store(bookToAddHashTagTo);
        }

//        else {
//            System.out.println("DUPLICATE!!!!!!");
//            HashTag hashTagToAdd = hashTagToAddOpt.get();
//            bookToAddHashTagTo.addHashTag(hashTagToAdd);
//            bookStorage.store(bookToAddHashTagTo);
//        }

        return bookRepo.findById(id);
    }

}