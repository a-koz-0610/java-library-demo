package org.wecancodeit.library.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.storage.AuthorStorage;
import org.wecancodeit.library.storage.BookStorage;
import org.wecancodeit.library.storage.CampusStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CampusControllerTest {

    private MockMvc mockMvc;
    private CampusController underTest;
    private CampusStorage campusStorage;
    private Model mockModel;
    private BookStorage bookStorage;
    private AuthorStorage authorStorage;

    @BeforeEach
    public void setUp() {
        mockModel = mock(Model.class);
        campusStorage = mock(CampusStorage.class);
        authorStorage = mock(AuthorStorage.class);
        bookStorage = mock(BookStorage.class);
        underTest = new CampusController(campusStorage, authorStorage, bookStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewWithOneCampus() {
        Campus testCampus = new Campus("TESTER TOWN");
        when(campusStorage.findCampusByLocation("LOS TESTA")).thenReturn(testCampus);

        underTest.displaySingleCampus("LOS TESTA", mockModel);

        verify(campusStorage).findCampusByLocation("LOS TESTA");
        verify(mockModel).addAttribute("campus", testCampus);
    }

    @Test
    public void shouldReturnViewNamedCampusViewWhenDisplaySingleCampusIsCalled() {
        String viewName = underTest.displaySingleCampus("LosTesta", mockModel);
        assertThat(viewName).isEqualTo("campusView");
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception {
        Campus testCampus = new Campus("TESTER TOWN");
        when(campusStorage.findCampusByLocation("LOS TESTA")).thenReturn(testCampus);

        mockMvc.perform(get("/campuses/LOS TESTA"))
               .andExpect(status().isOk())
               .andExpect(view().name("campusView"))
               .andExpect(model().attributeExists("campus"))
               .andExpect(model().attribute("campus", testCampus));
    }

    @Test
    public void campusesEndPointDisplaysAllCampuses() throws Exception {
        Campus testCampus = new Campus("Columbus");

        List<Campus> campusCollection = Collections.singletonList(testCampus);
        when(campusStorage.findAllCampuses()).thenReturn(campusCollection);
        mockMvc.perform(get("/campuses"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(view().name("campusesView"))
               .andExpect(model().attributeExists("campuses"))
               .andExpect(model().attribute("campuses", campusCollection));
    }

    @Test
    public void addCampusShouldRedirectToCampusesEndPoint() {
        String result = underTest.addCampus("Testville");
        assertThat(result).isEqualTo("redirect:campuses");
    }

    @Test
    public void addCampusShouldStoreANewCampus() {
        underTest.addCampus("Testville");
        verify(campusStorage).store(new Campus("Testville"));
    }

    @Test
    public void addCampusEndpointShouldAddNewCampus() throws Exception {
        mockMvc.perform(post("/add-campus")
                .param("location", "Testing Town"))
               .andDo(print())
               .andExpect(status().is3xxRedirection());
        verify(campusStorage).store(new Campus("Testing Town"));
    }
}
