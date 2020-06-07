package org.wecancodeit.library.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wecancodeit.library.models.Author;
import org.wecancodeit.library.models.Book;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.storage.BookStorage;
import org.wecancodeit.library.storage.repositories.HashTagRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    private BookController underTest;
    private Model model;
    private BookStorage mockStorage;
    private Book testBook;
    private HashTagRepository hashTagRepo;

    @BeforeEach
    void setUp() {
        mockStorage = mock(BookStorage.class);
        hashTagRepo = mock(HashTagRepository.class);
        underTest = new BookController(mockStorage, hashTagRepo);
        model = mock(Model.class);
        Campus testCampus = new Campus("New Test City");
        Author testAuthor = new Author("Testa", "Testarosa");
        testBook = new Book("Testing the Night Away", "Test Description", testCampus, testAuthor);
        when(mockStorage.findBookById(1L)).thenReturn(testBook);

    }

    @Test
    public void displayBookReturnsBookTemplate() {
        String result = underTest.displayBook(1L, model);
        assertThat(result).isEqualTo("book-view");
    }

    @Test
    public void displayBookInteractsWithDependenciesCorrectly() {

        underTest.displayBook(1L, model);
        verify(mockStorage).findBookById(1L);
        verify(model).addAttribute("book", testBook);
    }

    @Test
    public void displayBookMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
               .andExpect(status().isOk())
               .andExpect(view().name("book-view"))
               .andExpect(model().attributeExists("book"))
               .andExpect(model().attribute("book", testBook));
    }
}