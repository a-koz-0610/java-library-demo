package org.wecancodeit.library.storage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CampusNotFoundException extends RuntimeException {
    public CampusNotFoundException(String message) {
    }
}
