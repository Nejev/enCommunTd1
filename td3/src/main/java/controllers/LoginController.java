package controllers;

import model.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.ModelService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/login")
public class LoginController extends HttpServlet {
    @Autowired
    private ModelService modelService;

    private Membre userCo;

    @RequestMapping(value = "/loginS")
    public String loginS(HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo == null) {
            return "login";
        } else {
            return "redirect:/home/homeS";
        }
    }

    @RequestMapping(value = "/loginP")
    public String loginP(HttpServletRequest request, HttpServletResponse response, @RequestParam String login, @RequestParam String password) throws IOException {
        HttpSession session = request.getSession();
        if (login != null && password != null) {
            this.userCo = this.modelService.findMembreByLogin(login);
            System.out.println(this.userCo.getLogin() + "=" + login + this.userCo.getLogin() + "=" + this.userCo.getMotdepasse());
            if (this.userCo != null && login.equals(this.userCo.getLogin()) && password.equals(this.userCo.getMotdepasse())) {
                session.setAttribute("user_session", this.userCo.getLogin());
                return "redirect:/home/homeS";
            } else {
                request.setAttribute("msgError", "Identifiants invalides");
                return "redirect:/login/loginS";
            }
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/loginD")
    public String loginD(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user_session", "");
        return "redirect:/home/indexS";
    }
}