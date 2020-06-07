package org.wecancodeit.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.storage.AuthorStorage;
import org.wecancodeit.library.storage.BookStorage;
import org.wecancodeit.library.storage.CampusStorage;

@Controller
public class CampusController {

    private final CampusStorage campusStorage;

    public CampusController(CampusStorage campusStorage, AuthorStorage authorStorage, BookStorage bookStorage) {
        this.campusStorage = campusStorage;
    }


    @RequestMapping({"/campuses", "/", ""})
    public String displayCampuses(Model model) {
        model.addAttribute("campuses", campusStorage.findAllCampuses());
        return "campusesView";
    }

    @GetMapping("/campuses/{campusLocation}")
    public String displaySingleCampus(@PathVariable String campusLocation, Model model) {
        Campus retrievedCampus = campusStorage.findCampusByLocation(campusLocation);
        model.addAttribute("campus", retrievedCampus);

        return "campusView";
    }

    @PostMapping("/add-campus")
    public String addCampus(@RequestParam String location) {
        campusStorage.store(new Campus(location));
        return "redirect:campuses";
    }
}
