package org.wecancodeit.library.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.storage.repositories.CampusRepository;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
public class CampusRestController {

    @Resource
    private CampusRepository campusRepo;

    @GetMapping("/rest-campuses")
    public Collection<Campus> getCampuses() {
        return (Collection<Campus>) campusRepo.findAll();
    }
}
