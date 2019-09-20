package servlet;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class ControllerMain extends HttpServlet {
    public static final String URL_BASE_APPLI = "/td1_war";
    public static final String URL_BASE_MODULE = URL_BASE_APPLI+"/EnCommun";
    public static final String URL_GET_HOME = URL_BASE_MODULE+"/home";
    public static final String URL_GET_LOGIN = URL_BASE_MODULE+"/login";
    public static final String URL_POST_LOGIN = URL_BASE_MODULE+"/loginP";
    public static final String URL_DELETE_LOGIN = URL_BASE_MODULE+"/loginD";
    public static final String URL_GET_PROJET = URL_BASE_MODULE+"/projet";
    public static final String URL_DELETE_PROJET = URL_BASE_MODULE+"/projetD";
    public static final String URL_UPDATE_QUIT_PROJET = URL_BASE_MODULE+"/projetUQuit";
    public static final String URL_UPDATE_PARTICIPE_PROJET = URL_BASE_MODULE+"/projetUParticipe";
    public static final String URL_GET_COMPETENCE = URL_BASE_MODULE+"/competence";
    public static final String URL_POST_COMPETENCE = URL_BASE_MODULE+"/competenceP";
    public static final String URL_POST_COMPETENCEMEMBRE = URL_BASE_MODULE+"/competenceMembreP";
    public static final String URL_DELETE_COMPETENCEMEMBRE = URL_BASE_MODULE+"/competenceMembreD";
    public static final String URL_GET_ERROR = URL_BASE_MODULE+"/error";
    public static final String PATH_ERROR = "/WEB-INF/view/error.jsp";
    public static final String PATH_HOME = "/WEB-INF/view/home.jsp";
    public static final String PATH_LOGIN = "/WEB-INF/view/login.jsp";
    public static final String PATH_PROJET = "/WEB-INF/view/projet.jsp";
    public static final String PATH_COMPETENCE = "/WEB-INF/view/competence.jsp";

    private Model model;
    private String action;
    private ArrayList<String> needConnection;

    @Override
    public void init() {
        //init des 3 listes
        this.model = new Model();
        //List des actions qui requiert une connexion
        this.needConnection = new ArrayList<>();
        this.needConnection.add("projet");
        this.needConnection.add("home");
        this.needConnection.add("competence");
        this.needConnection.add("competenceP");
        this.needConnection.add("competenceMembreP");
        this.needConnection.add("competenceMembreD");
        this.needConnection.add("projetD");
        this.needConnection.add("projetUQuit");
        this.needConnection.add("projetUParticipe");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //try {
            //Passage des urls
            request.setAttribute("URL_BASE_APPLI", URL_BASE_APPLI);
            request.setAttribute("URL_GET_HOME", URL_GET_HOME);
            request.setAttribute("URL_POST_LOGIN", URL_POST_LOGIN);
            request.setAttribute("URL_DELETE_LOGIN", URL_DELETE_LOGIN);
            request.setAttribute("URL_GET_PROJET", URL_GET_PROJET);
            request.setAttribute("URL_DELETE_PROJET",URL_DELETE_PROJET);
            request.setAttribute("URL_UPDATE_QUIT_PROJET",URL_UPDATE_QUIT_PROJET);
            request.setAttribute("URL_UPDATE_PARTICIPE_PROJET",URL_UPDATE_PARTICIPE_PROJET);
            request.setAttribute("URL_GET_COMPETENCE", URL_GET_COMPETENCE);
            request.setAttribute("URL_POST_COMPETENCE", URL_POST_COMPETENCE);
            request.setAttribute("URL_POST_COMPETENCEMEMBRE", URL_POST_COMPETENCEMEMBRE);
            request.setAttribute("URL_DELETE_COMPETENCEMEMBRE", URL_DELETE_COMPETENCEMEMBRE);
            request.setAttribute("URL_GET_ERROR", URL_GET_ERROR);

            //Construction de l'action
            this.action = request.getPathInfo();
            if (this.action == null) {
                this.action = "";
            } else {
                this.action = this.action.substring(1);
            }

            //Gestion de l'authentification
            HttpSession session = request.getSession();
            Membre userCo = this.model.findMembreByLogin((String) session.getAttribute("user_session"));
            if (this.needConnection.contains(this.action) && userCo==null) {
                this.action = "redirectLogin";
            }

            //Gestion de l'affichage d'un msg
            if (request.getAttribute("msgError") != null) {
                request.setAttribute("msg", request.getAttribute("msgError"));
                request.setAttribute("colorMsg", "red");
            }else if(request.getAttribute("msgValide") != null){
                request.setAttribute("msg", request.getAttribute("msgValide"));
                request.setAttribute("colorMsg", "green");
            }else if(request.getAttribute("msgAutre") != null){
                request.setAttribute("msg", request.getAttribute("msgAutre"));
                request.setAttribute("colorMsg", "black");
            }else{
                request.setAttribute("msg", " ");
                request.setAttribute("colorMsg", "black");
            }

            //Actions
            switch (this.action) {
                case "home":
                    request.setAttribute("login", userCo.getLogin());
                    request.getRequestDispatcher(PATH_HOME).forward(request, response);
                    break;

                case "login":
                    if(userCo==null){
                        request.getRequestDispatcher(PATH_LOGIN).forward(request, response);
                    }else{

                        response.sendRedirect(URL_GET_HOME);
                    }
                    break;

                case "loginP":
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    if (login != null && password != null) {
                        Membre membre = this.model.findMembreByLogin(login);
                        if (membre != null && login.equals(membre.getLogin()) && password.equals(membre.getMotdepasse())) {
                            session.setAttribute("user_session", membre.getLogin());
                            response.sendRedirect(URL_GET_HOME);
                        } else {
                            request.setAttribute("msgError","Identifiants invalides");
                            request.getRequestDispatcher("login").forward(request, response);
                        }
                    } else {
                        //throw new Exception();
                    }
                    break;

                case "loginD":
                    session.setAttribute("user_session", "");
                    response.sendRedirect(URL_BASE_APPLI);
                    break;

                case "redirectLogin":
                    response.sendRedirect(URL_GET_LOGIN);
                    break;

                case "projet":
                    HashMap<String, ArrayList<Projet>> listProjetLogin = this.model.dispatchProject(userCo);
                    request.setAttribute("listDirigeP", listProjetLogin.get("responsable"));
                    request.setAttribute("listParticipeP", listProjetLogin.get("participe"));
                    request.setAttribute("listCompetenceP", listProjetLogin.get("competence"));
                    request.setAttribute("listAutreP", listProjetLogin.get("autre"));
                    request.setAttribute("login", userCo.getLogin());
                    request.setAttribute("URL_DELETE_LOGIN", URL_DELETE_LOGIN);
                    request.getRequestDispatcher(PATH_PROJET).forward(request, response);
                    break;

                case "projetD":
                    String intitulePD = request.getParameter("intituleP");
                    if (intitulePD!=null) {
                        if(userCo.getResponsable().contains(this.model.findProjetByIntitule(intitulePD))){
                            this.model.deleteProjet(intitulePD);
                            response.sendRedirect(URL_GET_PROJET);
                        }else{
                            request.setAttribute("msgError","Vous n'êtes pas responsable de ce projet");
                            request.getRequestDispatcher("projet").forward(request, response);
                        }
                    }else{
                        //throw new Exception();
                    }
                    break;

                case "projetUQuit":
                    String intituleUQ = request.getParameter("intituleP");
                    if (intituleUQ!=null) {
                        this.model.quitProjet(userCo,intituleUQ);
                        response.sendRedirect(URL_GET_PROJET);
                    }else{
                        //throw new Exception();
                    }
                    break;

                case "projetUParticipe":
                    String intituleUP = request.getParameter("intituleP");
                    if (intituleUP!=null) {
                        this.model.participeProjet(userCo,intituleUP);
                        response.sendRedirect(URL_GET_PROJET);
                    }else{
                        //throw new Exception();
                    }
                    break;

                case "competence":
                    request.setAttribute("listCompetenceUser", userCo.getCompetenceDeclare());
                    request.setAttribute("listCompetence", this.model.getCompetenceList());
                    request.getRequestDispatcher(PATH_COMPETENCE).forward(request, response);
                    break;

                case "competenceP":
                    String intitule = request.getParameter("intitule");
                    String description = request.getParameter("description");
                    if (intitule != null) {
                        if(description==null){
                            description="Compétence "+intitule;
                        }
                        this.model.addCompetence(intitule,description);
                        response.sendRedirect(URL_GET_COMPETENCE);
                    } else {
                        //throw new Exception();
                    }
                    break;

                case "competenceMembreP":
                    String intituleCM = request.getParameter("intituleCM");
                    String descriptionCM = request.getParameter("descriptionCM");
                    String niveauString = request.getParameter("niveau");
                    Integer niveau = -1;
                    try {
                        niveau=Integer.parseInt(niveauString);
                    }catch (Exception ex){
                        request.setAttribute("msgError","Veuillez mettre un entier");
                        request.getRequestDispatcher("competence").forward(request, response);
                    }
                    if (niveau!=-1) {
                        if (intituleCM != null || niveau != null) {
                            if (descriptionCM == null || descriptionCM == "") {
                                descriptionCM = "Compétence " + intituleCM + " de niveau " + niveau;
                            }
                            this.model.setCompetenceToMembre(this.model.findCompetenceByIntitule(intituleCM), userCo, niveau, descriptionCM);
                            response.sendRedirect(URL_GET_COMPETENCE);
                        } else {
                            //throw new Exception();
                        }
                    }
                    break;

                case "competenceMembreD":
                    String intituleCM_D = request.getParameter("intituleCM");
                    if (intituleCM_D!=null) {
                        this.model.deleteCompetenceMembre(userCo,intituleCM_D);
                        response.sendRedirect(URL_GET_COMPETENCE);
                    }else{
                        //throw new Exception();
                    }
                    break;

                case "error":
                    request.getRequestDispatcher(PATH_ERROR).forward(request, response);
                    break;

                default:
                    request.setAttribute("msgError", "Ressource inconnue");
                    //throw new Exception();
            }
        /*} catch (Exception e) {
            request.setAttribute("msgError", "Il semblerait que votre action a engendrée une erreur");
            request.getRequestDispatcher("error").forward(request, response);
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}