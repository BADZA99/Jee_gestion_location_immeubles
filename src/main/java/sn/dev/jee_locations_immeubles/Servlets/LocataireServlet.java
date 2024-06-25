package sn.dev.jee_locations_immeubles.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.jee_locations_immeubles.Entities.*;
import sn.dev.jee_locations_immeubles.dao.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "LocataireServlet", value = "/LocataireServlet")
public class LocataireServlet extends HttpServlet {

    private LocataireDao LocataireDao;
    private UtilisateurDao UtilisateurDao;
    private UniteLocationDao UniteLocationDao;
    private ContratLocationDao ContratLocationDao;
    private DemandeLocationDao DemandeLocationDao;
    public void init() {
        this.LocataireDao = new LocataireDao();
        this.UtilisateurDao = new UtilisateurDao();
        this.UniteLocationDao = new UniteLocationDao();
        this.ContratLocationDao = new ContratLocationDao();
        this.DemandeLocationDao = new DemandeLocationDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        String idUniteLocParam = request.getParameter("idUniteLoc");
        String idLocataireParam = request.getParameter("idLocataire");

        System.out.println("idUniteLocParam : " + idUniteLocParam);
        System.out.println("idLocataireParam : " + idLocataireParam);
        System.out.println("action : " + action);

        if (action != null && !action.isEmpty() && "demandeLoc".equals(action) && idUniteLocParam != null && idLocataireParam != null && !idUniteLocParam.isEmpty() && !idLocataireParam.isEmpty()) {
            int idUniteLoc = Integer.parseInt(idUniteLocParam);
            int idLocataire = Integer.parseInt(idLocataireParam);
            System.out.println("idUniteLoc : " + idUniteLoc);
            System.out.println("idLocataire : " + idLocataire);
            try {
                Unitelocation uniteLocation = UniteLocationDao.find(idUniteLoc);
                Locataire locataire = LocataireDao.find(idLocataire);

                if (uniteLocation != null && locataire != null) {
                    // Définir les dates directement
                    java.util.Date utilDateDebut = new java.util.Date();  // Date actuelle comme date de début
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(utilDateDebut);
                    cal.add(Calendar.MONTH, 6);  // Par exemple, 6 mois après la date de début
                    java.util.Date utilDateFin = cal.getTime();

                    // Convertir java.util.Date en java.sql.Date
                    java.sql.Date sqlDateDebut = new java.sql.Date(utilDateDebut.getTime());
                    java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());

                    Contratlocation newContrat = new Contratlocation();
                    newContrat.setDateDebut(sqlDateDebut);
                    newContrat.setDateFin(sqlDateFin);
                    newContrat.setUnitelocationByUniteLocationId(uniteLocation);
                    newContrat.setLocataireByLocataireId(locataire);
                    newContrat.setStatut("pending");

                    ContratLocationDao.save(newContrat);

                    if (ContratLocationDao.save(newContrat) != null) {
                        System.out.println(ContratLocationDao.save(newContrat));
                        request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                    } else {
                        System.out.println("erreur lors de la demande");
                        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                    }
                }
            } catch (Exception e) {
                // Si la connexion échoue, définissez l'attribut "failed"
                System.out.println("erreur lors de la demande : " + e.getMessage());
            }
        } else {
            System.out.println("Paramètres idUniteLoc ou idLocataire manquants");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        String nom = request.getParameter("nom");
        String mdp = request.getParameter("mdp");
        String action = request.getParameter("action");

        //sendDemand
        int idLoc = Integer.parseInt(request.getParameter("idLoc"));
        int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
        //System.out.println("action=" + action);
        //System.out.println("idUtilisateur=" + idUtilisateur);
        //System.out.println("idLoc=" + idLoc);
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
        }
        else if ("sendDemand".equals(action)) {
            try{
                int uniteLocationId = Integer.parseInt(request.getParameter("uniteLocationId"));
                Demandelocation newDemandeLocation = new Demandelocation();
                newDemandeLocation.setDateDemande(new java.sql.Date(new Date().getTime()));
                newDemandeLocation.setLocataireId(idLoc);
                newDemandeLocation.setUniteLocationId(uniteLocationId);
                //STATUT ENUM('EN_ATTENTE', 'ACCEPTEE', 'REFUSEE') NOT NULL DEFAULT 'EN_ATTENTE',
                newDemandeLocation.setStatut("EN_ATTENTE");
                Demandelocation saved =DemandeLocationDao.save(newDemandeLocation);

                if (saved != null) {
                    System.out.println("demande envoyeee "+saved);
                    request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                }else{

                    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                }
            }catch (Exception e) {
                // Si la connexion échoue, définissez l'attribut "failed"
                System.out.println("erreur lors de l'envoie de la demande : " + e.getMessage());
            }

        }
        else {
            // Gérer l'erreur : données invalides
            request.setAttribute("status", "Données invalides");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }


    }

}