package servlet;

import model.ConfigUrlMsgService;
import model.MapProtectService;
import model.Membre;
import model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CompetenceController extends HttpServlet {
    @Autowired
    private ModelService modelService;

    @Autowired
    private ConfigUrlMsgService config;

    @Autowired
    private MapProtectService mapProtectService;

    private String action;

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
                case "competenceS":
                    request.setAttribute("listCompetenceUser", userCo.getCompetenceDeclare());
                    request.setAttribute("listCompetence", this.modelService.getCompetenceList());
                    request.getRequestDispatcher(config.getPATH_COMPETENCE()).forward(request, response);
                    break;

                case "competenceP":
                    String intitule = request.getParameter("intitule");
                    String description = request.getParameter("description");
                    if (intitule != null) {
                        if (description == null) {
                            description = "Compétence " + intitule;
                        }
                        this.modelService.addCompetence(intitule, description);
                        response.sendRedirect(config.getURL_GET_COMPETENCE());
                    } else {
                        throw new Exception();
                    }
                    break;

                case "competenceMembreP":
                    String intituleCM = request.getParameter("intituleCM");
                    String descriptionCM = request.getParameter("descriptionCM");
                    String niveauString = request.getParameter("niveau");
                    Integer niveau = -1;
                    try {
                        niveau = Integer.parseInt(niveauString);
                    } catch (Exception ex) {
                        request.setAttribute("msgError", "Veuillez mettre un entier");
                        request.getRequestDispatcher(config.cutBaseUrl(config.getURL_GET_COMPETENCE())).forward(request, response);
                    }
                    if (niveau != -1) {
                        if (intituleCM != null && niveau != null) {
                            if (descriptionCM == null || descriptionCM == "") {
                                descriptionCM = "Compétence " + intituleCM + " de niveau " + niveau;
                            }
                            this.modelService.setCompetenceToMembre(this.modelService.findCompetenceByIntitule(intituleCM), userCo, niveau, descriptionCM);
                            response.sendRedirect(config.getURL_GET_COMPETENCE());
                        } else {
                            throw new Exception();
                        }
                    }
                    break;

                case "competenceMembreD":
                    String intituleCM_D = request.getParameter("intituleCM");
                    if (intituleCM_D != null) {
                        this.modelService.deleteCompetenceMembre(userCo, intituleCM_D);
                        response.sendRedirect(config.getURL_GET_COMPETENCE());
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
