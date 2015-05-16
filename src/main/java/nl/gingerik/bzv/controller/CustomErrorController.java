package nl.gingerik.bzv.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value="/error")
    public void error(HttpServletRequest req, HttpServletResponse resp) {
    }
    
	@Override
	public String getErrorPath() {
		return "/error";
	}


}
