package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController extends HttpServlet {
    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/errorS")
    public String errorS(HttpServletRequest request, @RequestParam String error) {
        request.setAttribute("msgError", error);
        return "error";
    }
}