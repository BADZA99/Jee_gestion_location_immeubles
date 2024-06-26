package sn.dev.jee_locations_immeubles.Servlets;

import sn.dev.jee_locations_immeubles.dao.UtilisateurDao;
import sn.dev.jee_locations_immeubles.Entities.Utilisateur;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UtilisateurServlet", value = "/UtilisateurServlet")
public class UtilisateurServlet extends HttpServlet {

    private UtilisateurDao utilisateurDao = new UtilisateurDao();

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
                insertUser(req, resp);
                break;
            case "delete":
                deleteUser(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "update":
                updateUser(req, resp);
                break;
            default:
                listUser(req, resp);
                break;
        }
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // List<Utilisateur> listUser = utilisateurDao.getAllUsers();
        // PrintWriter out = resp.getWriter();
        // for (Utilisateur user : listUser) {
        //     out.println("<p>" + user.getName() + "</p>");
        // }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de création
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour insérer un nouvel utilisateur
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour supprimer un utilisateur
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de modification
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour mettre à jour un utilisateur
    }
}