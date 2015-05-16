package nl.gingerik.bzv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionHandlingController {
    
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such resource")
    public static class ResourceNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;
    }

}
