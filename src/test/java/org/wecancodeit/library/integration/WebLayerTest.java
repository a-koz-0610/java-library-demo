package org.wecancodeit.library.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.storage.AuthorStorage;
import org.wecancodeit.library.storage.BookStorage;
import org.wecancodeit.library.storage.CampusStorage;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {

    @MockBean
    CampusStorage mockStorage;
    @MockBean
    BookStorage bookStorage;
    @MockBean
    AuthorStorage authorStorage;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void campusesShouldBeOKAndReturnTheCampusesViewWithCampusesModelAttribute() throws Exception {
        mockMvc.perform(get("/campuses"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(view().name("campusesView"))
               .andExpect(model().attributeExists("campuses"));
    }

    @Test
    public void shouldReceiveOKFromSingleCampusEndpoint() throws Exception {
        Campus testCampus = new Campus("Testerville");
        when(mockStorage.findCampusByLocation("Testerville")).thenReturn(testCampus);
        mockMvc.perform(get("/campuses/Testerville"))
               .andExpect(status().isOk())
               .andExpect(view().name("campusView"))
               .andExpect(model().attributeExists("campus"));
    }

    @Test
    public void shouldBeAbleToCreateNewCampus() throws Exception {
        mockMvc.perform(post("/add-campus")
                .param("location", "Testville"))
               .andExpect(status().is3xxRedirection());
        verify(mockStorage).store(new Campus("Testville"));
    }

}
