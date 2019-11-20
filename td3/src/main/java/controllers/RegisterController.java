package controllers;

import modele.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.ModelService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterController extends HttpServlet {
    @Autowired
    private ModelService modelService;

    private Membre userCo;

    @RequestMapping(value = "/registerS")
    public String registerS(HttpServletRequest request) {
        return "register";
    }

    @RequestMapping(value = "/registerP")
    public String registerP(HttpServletRequest request, HttpServletResponse response, @RequestParam String login, @RequestParam String password, @RequestParam String surnom) throws IOException {
        if (login != null && password != null && surnom != null) {
            if (this.modelService.createMembre(login, password, surnom)) {
                return "redirect:/login/loginS";
            } else {
                request.setAttribute("msgError", "Login déja utilisé ou champs mal renseigné");
                return "redirect:/register/registerS";
            }
        } else {
            return "redirect:/error/errorS";
        }
    }
}