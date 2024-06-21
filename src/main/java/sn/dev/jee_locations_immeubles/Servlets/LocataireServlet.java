package sn.dev.jee_locations_immeubles.Servlets;

import sn.dev.jee_locations_immeubles.dao.LocataireDao;
import sn.dev.jee_locations_immeubles.Entities.Locataire;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LocataireServlet", value = "/LocataireServlet")
public class LocataireServlet extends HttpServlet {

    private LocataireDao locataireDao = new LocataireDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "create":
                showNewForm(req, resp);
                break;
            case "insert":
                insertLocataire(req, resp);
                break;
            case "delete":
                deleteLocataire(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "update":
                updateLocataire(req, resp);
                break;
            default:
                listLocataire(req, resp);
                break;
        }
    }

    private void listLocataire(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Locataire> listLocataire = locataireDao.findAll();
        PrintWriter out = resp.getWriter();
        for (Locataire locataire : listLocataire) {
            out.println("<p>" + locataire.getId() + "</p>");
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de création
    }

    private void insertLocataire(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour insérer un nouveau locataire
    }

    private void deleteLocataire(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour supprimer un locataire
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de modification
    }

    private void updateLocataire(HttpServletRequest req, HttpServletResponse resp) {

    }
}