package sn.dev.jee_locations_immeubles.Servlets;

import sn.dev.jee_locations_immeubles.dao.UniteLocationDao;
import sn.dev.jee_locations_immeubles.Entities.Unitelocation;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UniteLocationServlet", value = "/UniteLocationServlet")
public class UniteLocationServlet extends HttpServlet {

    private UniteLocationDao uniteLocationDao = new UniteLocationDao();

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
                insertUnit(req, resp);
                break;
            case "delete":
                deleteUnit(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "update":
                updateUnit(req, resp);
                break;
            default:
                listUnit(req, resp);
                break;
        }
    }

    private void listUnit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // List<Unitelocation> listUnit = uniteLocationDao.findAll();
        // PrintWriter out = resp.getWriter();
        // for (Unitelocation unit : listUnit) {
        //     out.println("<p>" + unit.getName() + "</p>");
        // }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de création
    }

    private void insertUnit(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour insérer une nouvelle unité
    }

    private void deleteUnit(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour supprimer une unité
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de modification
    }

    private void updateUnit(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour mettre à jour une unité
    }
}