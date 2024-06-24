package sn.dev.jee_locations_immeubles.Servlets;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import jakarta.servlet.ServletException;
import sn.dev.jee_locations_immeubles.Entities.Utilisateur;
import sn.dev.jee_locations_immeubles.Entities.Immeuble;
import sn.dev.jee_locations_immeubles.Entities.Locataire;
import sn.dev.jee_locations_immeubles.Entities.Unitelocation;
import sn.dev.jee_locations_immeubles.dao.UtilisateurDao;
import sn.dev.jee_locations_immeubles.dao.LocataireDao;
import sn.dev.jee_locations_immeubles.dao.UniteLocationDao;
import sn.dev.jee_locations_immeubles.dao.ImmeubleDao;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
@WebServlet(name = "AdminServlet", value = "/Admin-servlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class Admin extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "images";
    private String message;
    private List<Immeuble> ALLimmeubles;

    private List<Utilisateur> AllUtilisateurs;
    private UtilisateurDao userDao;
    private LocataireDao LocataireDao;
    private ImmeubleDao ImmeubleDao;
    private List<Unitelocation> AllUniteLocations;
    private UniteLocationDao UniteLocationDao;

    public void init() {
        message = "error action null";
        this.userDao = new UtilisateurDao();
        this.LocataireDao = new LocataireDao();
        this.UniteLocationDao = new UniteLocationDao();
        this.ImmeubleDao = new ImmeubleDao();
        sn.dev.jee_locations_immeubles.dao.ImmeubleDao immeubleDao = new ImmeubleDao();
        AllUniteLocations=  UniteLocationDao.findAll();
        AllUtilisateurs = userDao.findAll();
        ALLimmeubles = immeubleDao.AllImmeubles();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        String idUserParam = request.getParameter("idUser");
        String immIdParam = request.getParameter("immId");
        String UIdParam = request.getParameter("UId");
        if ("updateImm".equals(action)) {
            int idUser = Integer.parseInt(immIdParam);
            Immeuble immeuble= ImmeubleDao.find(idUser);
            try {
                if (immeuble != null) {
                    request.setAttribute("immeuble", immeuble);
                    request.getRequestDispatcher("/WEB-INF/jsp/Immeubles/Update.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                    System.out.println("immeuble introuvable");
                }
            }catch (Exception e) {
                // Si la connexion échoue, définissez l'attribut "failed"
                System.out.println("erreur lors de la maj immeuble : " + e.getMessage());
            }
            //redirection vers la page modif
        } else if ("deleteImm".equals(action)) {
            int idImm = Integer.parseInt(immIdParam);
            try{
                boolean deletedUnitByimm=ImmeubleDao.deleteUnitLocationsByImmeubleId(idImm);
                boolean deleted = ImmeubleDao.delete(idImm);
                if (deleted == true && deletedUnitByimm ==true ){
                    request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                }else{
                    System.out.println("erreur  suppression");
                }

            }catch (Exception e) {
                // Si la connexion échoue, définissez l'attribut "failed"
                System.out.println("erreur lors de la suppression : " + e.getMessage());
            }
        }else if ("updateU".equals(action)) {
            int idUnit = Integer.parseInt(UIdParam);
            Unitelocation unit= UniteLocationDao.find(idUnit);
            try {
                if (unit != null) {
                    request.setAttribute("unit", unit);
                    request.getRequestDispatcher("/WEB-INF/jsp/UpdateU.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                    System.out.println("unit introuvable");
                }
            }catch (Exception e) {
                // Si la connexion échoue, définissez l'attribut "failed"
                System.out.println("erreur lors de la maj unit : " + e.getMessage());
            }
            //redirection vers la page modif
        }

        else if ("deleteUser".equals(action) && !idUserParam.isEmpty() ) {
            int idUser = Integer.parseInt(idUserParam);
            try{

                    boolean deleted = userDao.delete(idUser);

                    if (deleted == true){
                        request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                    }else{
                        System.out.println("erreur  suppression");
                    }

            }catch (Exception e) {
                // Si la connexion échoue, définissez l'attribut "failed"
                System.out.println("erreur lors de la suppression : " + e.getMessage());
            }
            //redirection vers la page updateU
        }
        else if ("deleteU".equals(action) && !UIdParam.isEmpty() ) {
            int UId = Integer.parseInt(UIdParam);
            try{

                boolean deleted = UniteLocationDao.delete(UId);

                if (deleted == true){
                    request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                }else{
                    System.out.println("erreur  suppression");
                }

            }catch (Exception e) {
                // Si la connexion échoue, définissez l'attribut "failed"
                System.out.println("erreur lors de la suppression de l'unite: " + e.getMessage());
            }
            //redirection vers la page updateU
        }

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String action = request.getParameter("action");
        System.out.println(action);
        if("addImm".equals(action)){
            String nom = request.getParameter("nom");
            String Adresse = request.getParameter("Adresse");
            String Description = request.getParameter("Description");
            String equipements = request.getParameter("equipements");
            // File image = new File(request.getParameter("image"));
            Part filePart = request.getPart("image");
            if (nom != null && !nom.isEmpty() && Adresse != null && !Adresse.isEmpty() && Description != null && !Description.isEmpty() && equipements != null && !equipements.isEmpty() && filePart != null   ){
                String fileName = getFileName(filePart);
                String uploadPath = getServletContext().getRealPath("/images/immeubles");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                System.out.println(filePath);
                try{
                  Immeuble newImm = new Immeuble();
                  newImm.setNom(nom);
                  newImm.setAdresse(Adresse);
                  newImm.setDescription(Description);
                  newImm.setEquipements(equipements);
                  newImm.setImage(fileName);
                  Immeuble added=ImmeubleDao.save(newImm);
                  if (added != null) {
                      System.out.println("immeuble added  : " + added);
                      request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                  }
              }catch(Exception e) {
                  // Si la connexion échoue, définissez l'attribut "failed"
                  System.out.println("erreur lors de l'ajout immeuble : " + e.getMessage());
              }
            }
            //redirection vers la page ajout
        } else if ("updateImm2".equals(action)) {
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String image = request.getParameter("image");
            String equipement = request.getParameter("equipement");
            String description = request.getParameter("description");
            String idImmParam = request.getParameter("idImm");
            if (image != null && nom != null && adresse != null  && description != null && equipement != null && idImmParam !=null) {
                Immeuble imm= ImmeubleDao.find(Integer.parseInt(idImmParam));

                if(imm != null){
                    imm.setNom(nom);
                    imm.setAdresse(adresse);
                    imm.setImage(image);
                    imm.setEquipements(equipement);
                    imm.setDescription(description);
                    Immeuble updated=ImmeubleDao.update(imm);
                    if (updated != null){
                        System.out.println("Immeuble updated "+updated);
                        request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                    }
                }else{
                    System.out.println("immeuble introuvable");
                    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                }
            }
        }else if ("updateU2".equals(action)) {
            String nbpieces = request.getParameter("nbpieces");
            String loyer = request.getParameter("loyer");
            String image = request.getParameter("image");
            String superficie = request.getParameter("superficie");
            String numUnite = request.getParameter("numUnite");
            String idUParam = request.getParameter("idIU");

            if (image != null && nbpieces != null && loyer != null && superficie != null && numUnite != null && idUParam != null) {
                Unitelocation unite = UniteLocationDao.find(Integer.parseInt(idUParam));

                if (unite != null) {
                    try {
                        // Valider les entrées
                        int nbPiecesInt = Integer.parseInt(nbpieces);
                        BigDecimal loyerDecimal = new BigDecimal(loyer);
                        BigDecimal superficieDecimal = new BigDecimal(superficie);

                        // Mettre à jour l'objet Unitelocation
                        unite.setNombrePieces(nbPiecesInt);
                        unite.setLoyer(loyerDecimal);
                        unite.setImage(image);
                        unite.setSuperficie(superficieDecimal);
                        unite.setNumeroUnite(numUnite);

                        Unitelocation updated = UniteLocationDao.update(unite);
                        if (updated != null) {
                            System.out.println("Unite location updated: " + updated);
                            request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur de conversion: " + e.getMessage());
                        request.setAttribute("errorMessage", "Format de nombre invalide pour loyer ou superficie.");
                        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                    }
                } else {
                    System.out.println("Unite location introuvable");
                    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                }
            }
        }

        else if ("updateAdmin".equals(action)) {
            String nom = request.getParameter("nom");
            String mdp = request.getParameter("mdp");
            String idAdmin = request.getParameter("idAdmin");
            try{
                Utilisateur Utilisateur = userDao.find(Integer.parseInt(idAdmin));
                Utilisateur.setNomUtilisateur(nom);
                Utilisateur.setMotDePasse(mdp);
                Utilisateur.setRole("ADMIN");
                Utilisateur Updated = userDao.update(Utilisateur);

                if (Utilisateur != null && Updated !=  null ){
                    System.out.println("admin updated  : " + Updated);
                    request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                }else{
                    System.out.println("erreur  suppression");
                }

            }catch (Exception e) {
                // Si la connexion échoue, définissez l'attribut "failed"
                System.out.println("erreur lors de la suppression : " + e.getMessage());
            }
            //redirection vers la page immeubleId
        } else if ("AjoutU".equals(action)) {
            String nbpieces = request.getParameter("nbpieces");
            String loyer = request.getParameter("loyer");
            String image = request.getParameter("image");
            String superficie = request.getParameter("superficie");
            String numUnite = request.getParameter("numUnite");
            String immeubleIdParam = request.getParameter("immeubleId");

            if  (image != null && nbpieces != null && loyer != null && superficie != null && numUnite != null) {
                try{
                    BigDecimal loyerDecimal = new BigDecimal(loyer);
                    BigDecimal superficieDecimal = new BigDecimal(superficie);
                    int nbPiecesInt = Integer.parseInt(nbpieces);
                    int immeubleId = Integer.parseInt(immeubleIdParam);
                    Immeuble imm= ImmeubleDao.find(immeubleId);
                    Unitelocation unite = new Unitelocation();
                    unite.setNombrePieces(nbPiecesInt);
                    unite.setLoyer(loyerDecimal);
                    unite.setImage(image);
                    unite.setSuperficie(superficieDecimal);
                    unite.setNumeroUnite(numUnite);
                    unite.setImmeubleByImmeubleId(imm);
                    Unitelocation added = UniteLocationDao.save(unite);
                    if (added != null) {
                        System.out.println("Unite location added: " + added);
                        request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                    }

                }catch (Exception e) {
                    System.out.println("Erreur lors de l'ajout unite : " + e.getMessage());
                    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                }

            }

        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }


    public void destroy() {
    }
}