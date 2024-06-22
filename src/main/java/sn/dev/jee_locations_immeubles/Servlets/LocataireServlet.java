package sn.dev.jee_locations_immeubles.Servlets;

import jakarta.servlet.ServletException;
import sn.dev.jee_locations_immeubles.Entities.Utilisateur;
import sn.dev.jee_locations_immeubles.dao.LocataireDao;
import sn.dev.jee_locations_immeubles.Entities.Locataire;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sn.dev.jee_locations_immeubles.dao.UniteLocationDao;
import sn.dev.jee_locations_immeubles.dao.UtilisateurDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LocataireServlet", value = "/LocataireServlet")
public class LocataireServlet extends HttpServlet {

    private LocataireDao LocataireDao;
    private UtilisateurDao UtilisateurDao;
    public void init() {
        this.LocataireDao = new LocataireDao();
        this.UtilisateurDao = new UtilisateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        String nom = request.getParameter("nom");
        String mdp = request.getParameter("mdp");
        //idUtilisateur
        int idLoc = Integer.parseInt(request.getParameter("idLoc"));
        int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
        if (nom != null && mdp != null && !nom.isEmpty() && !mdp.isEmpty()) {

            Locataire locataire = LocataireDao.find(idLoc);
            Utilisateur utilisateur = UtilisateurDao.find(idUtilisateur);
            if (locataire != null && utilisateur != null) {
                locataire.setNom(nom);
                locataire.setMotDePasse(mdp);
                utilisateur.setNomUtilisateur(nom);
                utilisateur.setMotDePasse(mdp);
                // Supposons que updateLocataire met à jour l'objet dans la base de données
                LocataireDao.update(locataire);
                UtilisateurDao.update(utilisateur);
                if(LocataireDao.update(locataire) != null && UtilisateurDao.update(utilisateur) != null ){
                    System.out.println("locataire mis a jour ");
                    System.out.println("user correspondant  mis a jour");
                    System.out.println(LocataireDao.update(locataire));
                    System.out.println(UtilisateurDao.update(utilisateur));
                }else {
                    System.out.println("erreur maj loacataire");
                }
                // Redirection ou message de succès
                request.setAttribute("status", "success");
                request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
            } else {
                // Gérer l'erreur : locataire non trouvé
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } else {
            // Gérer l'erreur : données invalides
            request.setAttribute("status", "Données invalides");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

}