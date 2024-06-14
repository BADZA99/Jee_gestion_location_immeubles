package sn.dev.jee_locations_immeubles.Servlets;

import sn.dev.jee_locations_immeubles.dao.ContratLocationDao;
import sn.dev.jee_locations_immeubles.Entities.Contratlocation;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ContratLocationServlet", value = "/ContratLocationServlet")
public class ContratLocationServlet extends HttpServlet {

    private ContratLocationDao contratLocationDao = new ContratLocationDao();

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
                insertContrat(req, resp);
                break;
            case "delete":
                deleteContrat(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "update":
                updateContrat(req, resp);
                break;
            default:
                listContrat(req, resp);
                break;
        }
    }

    private void listContrat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Contratlocation> listContrat = contratLocationDao.findAll();
        PrintWriter out = resp.getWriter();
        for (Contratlocation contrat : listContrat) {
            out.println("<p>" + contrat.getId() + "</p>");
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de création
    }

    private void insertContrat(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour insérer un nouveau contrat
    }

    private void deleteContrat(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour supprimer un contrat
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de modification
    }

    private void updateContrat(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour mettre à jour un contrat
    }
}