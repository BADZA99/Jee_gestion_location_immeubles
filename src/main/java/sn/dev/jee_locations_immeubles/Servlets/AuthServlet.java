package sn.dev.jee_locations_immeubles.Servlets;

import java.io.*;
import java.util.*;


import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import sn.dev.jee_locations_immeubles.Entities.Utilisateur;
import sn.dev.jee_locations_immeubles.dao.UtilisateurDao;


@WebServlet(name = "AuthServletServlet", value = "/AuthServlet-servlet")
public class AuthServlet extends HttpServlet {
    private String message;

   private UtilisateurDao userDao;
    public void init() {
        message = "error action null";
        this.userDao = new UtilisateurDao();
        //  immeubles = immeubleDao.AllImmeubles();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
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
            System.out.println("avant save "+ user);
            userDao.save(user);

            if(userDao.save(user)!= null){
                System.out.println(userDao.save(user));
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

   
    } else if ("login".equals(action)) {
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

            if (user != null) {
                // L'utilisateur existe, la connexion est réussie
                HttpSession session = request.getSession();
                request.setAttribute("user", user);
                //System.out.println(user);
                // Si la connexion est réussie, définissez l'attribut "success"
                request.setAttribute("status", "success");
                if(user.getRole().equals("LOCATAIRE")){
                    request.getRequestDispatcher("/WEB-INF/jsp/Locataire.jsp").forward(request, response);
                }else if (user.getRole().equals("PROPRIETAIRE")) {
                    request.getRequestDispatcher("/WEB-INF/jsp/Proprietaire.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("/WEB-INF/jsp/Admin.jsp").forward(request, response);
                }

            } else {
                // L'utilisateur n'existe pas, la connexion a échoué
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Si la connexion échoue, définissez l'attribut "failed"
            System.out.println("Connexion échouée : " + e.getMessage());
        }
    }
}


    public void destroy() {
    }
}