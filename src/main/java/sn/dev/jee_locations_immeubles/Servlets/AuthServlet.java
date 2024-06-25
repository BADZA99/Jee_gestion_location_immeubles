package sn.dev.jee_locations_immeubles.Servlets;

import java.io.*;
import java.util.*;


import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import sn.dev.jee_locations_immeubles.Entities.Immeuble;
import sn.dev.jee_locations_immeubles.Entities.Utilisateur;
import sn.dev.jee_locations_immeubles.Entities.Locataire;
import sn.dev.jee_locations_immeubles.Entities.Unitelocation;
import sn.dev.jee_locations_immeubles.Entities.Contratlocation;
import sn.dev.jee_locations_immeubles.dao.ContratLocationDao;
import sn.dev.jee_locations_immeubles.Entities.Demandelocation;
import sn.dev.jee_locations_immeubles.dao.DemandeLocationDao;
import sn.dev.jee_locations_immeubles.dao.ImmeubleDao;
import sn.dev.jee_locations_immeubles.dao.UtilisateurDao;
import sn.dev.jee_locations_immeubles.dao.LocataireDao;
import sn.dev.jee_locations_immeubles.dao.UniteLocationDao;

@WebServlet(name = "AuthServletServlet", value = "/AuthServlet-servlet")
public class AuthServlet extends HttpServlet {
    private String message;
    private List<Immeuble> ALLimmeubles;
   private UtilisateurDao userDao;
   private LocataireDao LocataireDao;
    private List<Unitelocation> AllUniteLocations;
    private List<Demandelocation> AllDemandelocation;
    private List<Contratlocation> AllContratlocation;
    private UniteLocationDao UniteLocationDao;

    private DemandeLocationDao DemandeLocationDao;
    private ContratLocationDao contratLocationDao;
    private List<Utilisateur> AllUtilisateurs;


    public void init() {
        message = "error action null";
        this.userDao = new UtilisateurDao();
        this.LocataireDao = new LocataireDao();
        this.UniteLocationDao = new UniteLocationDao();
        this.contratLocationDao = new ContratLocationDao();
        this.DemandeLocationDao = new DemandeLocationDao();
        AllUniteLocations=  UniteLocationDao.findAll();
        sn.dev.jee_locations_immeubles.dao.ImmeubleDao immeubleDao = new ImmeubleDao();
        AllUtilisateurs = userDao.findAll();
        ALLimmeubles = immeubleDao.AllImmeubles();
        AllDemandelocation=DemandeLocationDao.findAll();
        AllContratlocation=contratLocationDao.findAll();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        String action = request.getParameter("action");
         if ("logout".equals(action)) {
            // Invalider la session pour déconnecter l'utilisateur
            request.getSession().invalidate();

            // Rediriger l'utilisateur vers la page de connexion ou la page d'accueil
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }else if ("addUser".equals(action)) {
             request.getRequestDispatcher("/WEB-INF/jsp/AjoutAdmin.jsp").forward(request, response);
         }
    }

    // dopost 
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if ("register".equals(action)) {
        // L'utilisateur essaie de s'inscrire
        String name = request.getParameter("nom");
        String password = request.getParameter("mdp");
        String role = request.getParameter("role");
        if (name == null || name.isEmpty() || password == null || password.isEmpty() || role == null) {
            request.setAttribute("status", "vides");

            return;
        }
        try {
            // Créez un nouvel utilisateur et enregistrez-le dans votre base de données
            Utilisateur user = new Utilisateur();
            user.setNomUtilisateur(name);
            user.setMotDePasse(password);
            user.setRole(role);
            //System.out.println("avant save "+ user.getRole());
            Utilisateur Newuser= userDao.save(user);
            System.out.println("apres save "+ Newuser);
            Locataire locataire = new Locataire();
            if(Newuser.getRole().equals("LOCATAIRE")){
                //Locataire locataire = new Locataire();
                locataire.setNom(Newuser.getNomUtilisateur());
                locataire.setMotDePasse(Newuser.getMotDePasse());
                locataire.setIdUtilisateur(Newuser.getId());
                locataire.setEmail(Newuser.getId()+"@locataire.com");
                LocataireDao.save(locataire);
                System.out.println("New loc "+ LocataireDao.save(locataire));
            }

            if(Newuser!= null && locataire!=null ){
                System.out.println(Newuser);
                //System.out.println("insertion reussi");
                // Si l'inscription est réussie, définissez l'attribut "success"
                request.setAttribute("status", "success");
                request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
            }else {
                request.setAttribute("status", "error");
                System.out.println("erreur lors de l'insertion");
            }


        } catch (Exception e) {
            // Si l'inscription échoue, définissez l'attribut "failed"
            System.out.println("Inscription échouée : " + e.getMessage());
            request.setAttribute("status", "error");
        }

   
    } else if ("registerUser".equals(action)) {
        // L'utilisateur essaie de s'inscrire
        String name = request.getParameter("nom");
        String password = request.getParameter("mdp");
        String role = request.getParameter("role");
        if (name == null || name.isEmpty() || password == null || password.isEmpty() || role == null) {
            request.setAttribute("status", "vides");

            return;
        }
        try {
            // Créez un nouvel utilisateur et enregistrez-le dans votre base de données
            Utilisateur user = new Utilisateur();
            user.setNomUtilisateur(name);
            user.setMotDePasse(password);
            user.setRole(role);
            //System.out.println("avant save "+ user.getRole());
            Utilisateur Newuser= userDao.save(user);
            System.out.println("apres save "+ Newuser);
            Locataire locataire = new Locataire();
            if(Newuser.getRole().equals("LOCATAIRE")){
                //Locataire locataire = new Locataire();
                locataire.setNom(Newuser.getNomUtilisateur());
                locataire.setMotDePasse(Newuser.getMotDePasse());
                locataire.setIdUtilisateur(Newuser.getId());
                locataire.setEmail(Newuser.getId()+"@locataire.com");
                LocataireDao.save(locataire);
                System.out.println("New loc "+ LocataireDao.save(locataire));
            }

            if(Newuser!= null && locataire!=null ){
                System.out.println(Newuser);
                //System.out.println("insertion reussi");
                // Si l'inscription est réussie, définissez l'attribut "success"
                request.setAttribute("status", "success");
                request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
            }else {
                request.setAttribute("status", "error");
                System.out.println("erreur lors de l'insertion");
            }


        } catch (Exception e) {
            // Si l'inscription échoue, définissez l'attribut "failed"
            System.out.println("Inscription échouée 2 : " + e.getMessage());
            request.setAttribute("status", "error");
        }

       }
    else if ("update".equals(action)) {
        String nomloc = request.getParameter("nom");
        String mdp = request.getParameter("mdp");
        int idLoc = Integer.parseInt(request.getParameter("idLoc"));
        if (nomloc != null && mdp != null && !nomloc.isEmpty() && !mdp.isEmpty()) {
            // Supposons que vous avez une méthode getLocataireByNom pour obtenir l'objet Locataire
            Locataire locataire = LocataireDao.find(idLoc);

            if (locataire != null) {
                locataire.setNom(nomloc);
                locataire.setMotDePasse(mdp);
                // Supposons que updateLocataire met à jour l'objet dans la base de données
                LocataireDao.update(locataire);
                if(LocataireDao.update(locataire) != null){
                    System.out.println("locataire mis a jour ");
                }else {
                    System.out.println("erreur maj loacataire");
                }
                // Redirection ou message de succès
                request.setAttribute("status", "success");
                request.getRequestDispatcher("/WEB-INF/jsp/Locataire.jsp").forward(request, response);
            } else {
                // Gérer l'erreur : locataire non trouvé
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/WEB-INF/jsp/Locataire.jsp").forward(request, response);
            }
        } else {
            // Gérer l'erreur : données invalides
            request.setAttribute("status", "Données invalides");
            request.getRequestDispatcher("/WEB-INF/jsp/Locataire.jsp").forward(request, response);
        }
    }
    else if ("login".equals(action)) {
        // L'utilisateur essaie de se connecter
        String name = request.getParameter("name");
        String password = request.getParameter("mdp");

        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("status", "Les champs de nom et de mot de passe ne peuvent pas être vides");

            return;
        }

        try {
            // Vérifiez si un utilisateur avec le nom et le mot de passe donnés existe dans la base de données
            Utilisateur user = userDao.findUserByNameAndPassword(name, password);
            if (user == null) {
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            }
            if (user.getRole().equals("LOCATAIRE")){
                Locataire loc=LocataireDao.findLocataireByUtilisateurId(user.getId());
                if (user != null && loc != null ) {
                    // L'utilisateur existe, la connexion est réussie
                    HttpSession session = request.getSession();
                    session.setAttribute("user", loc);
                    request.setAttribute("AllUniteLocations", AllUniteLocations);

                    //System.out.println(user);
                    // Si la connexion est réussie, définissez l'attribut "success"
                    request.setAttribute("status", "success");

                        request.getRequestDispatcher("/WEB-INF/jsp/Locataire.jsp").forward(request, response);
                }
            }else if (user.getRole().equals("PROPRIETAIRE")) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.setAttribute("AllUniteLocations", AllUniteLocations);
                request.setAttribute("ALLimmeubles", ALLimmeubles);
                request.setAttribute("AllUtilisateurs", AllUtilisateurs);
                request.getRequestDispatcher("/WEB-INF/jsp/Proprietaire.jsp").forward(request, response);
            }else if (user.getRole().equals("ADMIN")){
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.setAttribute("AllUniteLocations", AllUniteLocations);
                request.setAttribute("ALLimmeubles", ALLimmeubles);
                request.setAttribute("AllUtilisateurs", AllUtilisateurs);
                request.setAttribute("AllContratlocation", AllContratlocation);
                request.setAttribute("AllDemandelocation", AllDemandelocation);
                request.getRequestDispatcher("/WEB-INF/jsp/Admin.jsp").forward(request, response);
            } else {
                // L'utilisateur n'existe pas, la connexion a échoué
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("status", "error");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            // Si la connexion échoue, définissez l'attribut "failed"
            System.out.println("Connexion échouée : " + e.getMessage());
        }
    }
   }


    public void destroy() {
    }
}