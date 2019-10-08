package controllers;

import model.Membre;
import model.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.ModelService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/projet")
public class ProjetController extends HttpServlet {
    @Autowired
    private ModelService modelService;

    private Membre userCo;

    @RequestMapping(value = "/projetS")
    public String projetS(HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            HashMap<String, ArrayList<Projet>> listProjetLogin = this.modelService.dispatchProject(userCo);
            request.setAttribute("listDirigeP", listProjetLogin.get("responsable"));
            request.setAttribute("listParticipeP", listProjetLogin.get("participe"));
            request.setAttribute("listCompetenceP", listProjetLogin.get("competence"));
            request.setAttribute("listAutreP", listProjetLogin.get("autre"));
            request.setAttribute("login", userCo.getLogin());
            return "projet";
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/projetD")
    public String projetD(HttpServletRequest request, @RequestParam String intituleP) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            if (intituleP != null) {
                if (userCo.getResponsable().contains(this.modelService.findProjetByIntitule(intituleP))) {
                    this.modelService.deleteProjet(intituleP);
                } else {
                    request.setAttribute("msgError", "Vous n'êtes pas responsable de ce projet");
                }
                return "redirect:/projet/projetS";
            } else {
                return "redirect:/error/errorS";
            }
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/projetPQuit")
    public String projetPQuit(HttpServletRequest request, @RequestParam String intituleP) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            if (intituleP != null) {
                this.modelService.quitProjet(userCo, intituleP);
                return "redirect:/projet/projetS";
            } else {
                return "redirect:/error/errorS";
            }
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/projetPParticipe")
    public String projetPParticipe(HttpServletRequest request, @RequestParam String intituleP) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            if (intituleP != null) {
                this.modelService.participeProjet(userCo, intituleP);
                return "redirect:/projet/projetS";
            } else {
                return "redirect:/error/errorS";
            }
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/projetP")
    public String projetP(HttpServletRequest request, @RequestParam String intitule ,@RequestParam String description) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            if (intitule != null) {
                if (this.modelService.createProjet(userCo, intitule, description)) {
                    return "redirect:/projet/projetS";
                } else {
                    request.setAttribute("msgError", "Vous n'êtes pas responsable de ce projet");
                    return "redirect:/projet/projetS";
                }
            } else {
                return "redirect:/error/errorS";
            }
        } else {
            return "redirect:/login/loginS";
        }
    }
}








    /*

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Passage des urls
            config.setUrlInRequest(request);
            config.setMsgInJsp(request);
            //Construction de l'action
            this.action = request.getPathInfo();
            if (this.action == null) {
                this.action = "";
            } else {
                this.action = this.action.substring(1);
            }
            //Gestion de l'authentification
            HttpSession session = request.getSession();
            Membre userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
            if (this.mapProtectService.isProtect(this.action) && userCo == null) {
                response.sendRedirect(config.getURL_GET_LOGIN());
                return;
            }
            //Actions
            switch (this.action) {

                case "projetS":
                    HashMap<String, ArrayList<Projet>> listProjetLogin = this.modelService.dispatchProject(userCo);
                    request.setAttribute("listDirigeP", listProjetLogin.get("responsable"));
                    request.setAttribute("listParticipeP", listProjetLogin.get("participe"));
                    request.setAttribute("listCompetenceP", listProjetLogin.get("competence"));
                    request.setAttribute("listAutreP", listProjetLogin.get("autre"));
                    request.setAttribute("login", userCo.getLogin());
                    request.getRequestDispatcher(config.getPATH_PROJET()).forward(request, response);
                    break;

                case "projetD":
                    String intitulePD = request.getParameter("intituleP");
                    if (intitulePD != null) {
                        if (userCo.getResponsable().contains(this.modelService.findProjetByIntitule(intitulePD))) {
                            this.modelService.deleteProjet(intitulePD);
                            response.sendRedirect(config.getURL_GET_PROJET());
                        } else {
                            request.setAttribute("msgError", "Vous n'êtes pas responsable de ce projet");
                            request.getRequestDispatcher(config.cutBaseUrl(config.getURL_GET_PROJET())).forward(request, response);
                        }
                    } else {
                        throw new Exception();
                    }
                    break;

                case "projetPQuit":
                    String intituleUQ = request.getParameter("intituleP");
                    if (intituleUQ != null) {
                        this.modelService.quitProjet(userCo, intituleUQ);
                        response.sendRedirect(config.getURL_GET_PROJET());
                    } else {
                        throw new Exception();
                    }
                    break;

                case "projetPParticipe":
                    String intituleUP = request.getParameter("intituleP");
                    if (intituleUP != null) {
                        this.modelService.participeProjet(userCo, intituleUP);
                        response.sendRedirect(config.getURL_GET_PROJET());
                    } else {
                        throw new Exception();
                    }
                    break;

                case "projetP":
                    String intitulePC = request.getParameter("intitule");
                    String descriPC = request.getParameter("description");
                    if (intitulePC != null) {
                        if (this.modelService.createProjet(userCo, intitulePC, descriPC)) {
                            response.sendRedirect(config.getURL_GET_PROJET());
                        } else {
                            request.setAttribute("msgError", "Vous n'êtes pas responsable de ce projet");
                            request.getRequestDispatcher(config.cutBaseUrl(config.getURL_GET_PROJET())).forward(request, response);
                        }
                    } else {
                        throw new Exception();
                    }
                    break;

                default:
                    request.setAttribute("msgError", "Ressource inconnue");
                    throw new Exception();
            }
        } catch (Exception e) {
            request.setAttribute("msgError", "Il semblerait que votre action a engendrée une erreur");
            request.getRequestDispatcher(config.cutBaseUrl(config.getURL_GET_ERROR())).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
*/