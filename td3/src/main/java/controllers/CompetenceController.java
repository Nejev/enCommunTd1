package controllers;

import model.Projet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import model.Membre;
import services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/competence")
public class CompetenceController extends HttpServlet {
    @Autowired
    private ModelService modelService;

    private Membre userCo;

    @RequestMapping(value = "/competenceS")
    public String competenceS(HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            request.setAttribute("listCompetenceUser", userCo.getCompetenceDeclare());
            request.setAttribute("listCompetence", this.modelService.getCompetenceList());
            return "competence";
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/competenceP")
    public String competenceP(HttpServletRequest request, @RequestParam String intitule , @RequestParam String description) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            if (intitule != null) {
                if (description == null) {
                    description = "Compétence " + intitule;
                }
                this.modelService.addCompetence(intitule, description);
                return "redirect:/competence/competenceS";
            } else {
                return "redirect:/error/errorS";
            }
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/competenceMembreP")
    public String competenceMembreP(HttpServletRequest request, @RequestParam String intituleCM ,@RequestParam String descriptionCM,@RequestParam Integer niveau ) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            if (intituleCM != null && niveau != null) {
                if (descriptionCM == null || descriptionCM == "") {
                    descriptionCM = "Compétence " + intituleCM + " de niveau " + niveau;
                }
                this.modelService.setCompetenceToMembre(this.modelService.findCompetenceByIntitule(intituleCM), userCo, niveau, descriptionCM);
                return "redirect:/competence/competenceS";
            } else {
                return "redirect:/error/errorS";
            }
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/competenceMembreD")
    public String competenceMembreD(HttpServletRequest request, @RequestParam String intituleCM) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            if (intituleCM != null) {
                this.modelService.deleteCompetenceMembre(userCo, intituleCM);
                return "redirect:/competence/competenceS";
            } else {
                return "redirect:/error/errorS";
            }
        } else {
            return "redirect:/login/loginS";
        }
    }
}
