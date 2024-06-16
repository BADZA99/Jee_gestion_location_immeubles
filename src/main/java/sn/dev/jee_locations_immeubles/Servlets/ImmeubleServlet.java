package sn.dev.jee_locations_immeubles.Servlets;

import sn.dev.jee_locations_immeubles.dao.ImmeubleDao;
import sn.dev.jee_locations_immeubles.Entities.Immeuble;

import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ImmeubleServlet", value = "/ImmeubleServlet")
public class ImmeubleServlet extends HttpServlet {

    private ImmeubleDao immeubleDao = new ImmeubleDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "create":
                try {
                    showNewForm(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "insert":
                insertImmeuble(req, resp);
                break;
            case "delete":
                deleteImmeuble(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "update":
                updateImmeuble(req, resp);
                break;
            default:
                listImmeuble(req, resp);
                break;
        }
    }

    private void listImmeuble(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Immeuble> listImmeuble = immeubleDao.AllImmeubles();
       
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Immeubles/listeImmeubles.jsp");
    dispatcher.forward(req, resp);
}

    private void insertImmeuble(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour insérer un nouvel immeuble
    }

    private void deleteImmeuble(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour supprimer un immeuble
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour afficher le formulaire de modification
    }

    private void updateImmeuble(HttpServletRequest req, HttpServletResponse resp) {
        // implémenter la logique pour mettre à jour un immeuble
    }
}