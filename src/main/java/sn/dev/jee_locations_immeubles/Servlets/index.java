package sn.dev.jee_locations_immeubles.Servlets;

import java.io.*;

import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import sn.dev.jee_locations_immeubles.Entities.Immeuble;
import sn.dev.jee_locations_immeubles.Entities.Unitelocation;
import sn.dev.jee_locations_immeubles.dao.ImmeubleDao;
import jakarta.servlet.annotation.*;

@WebServlet(name = "indexServlet", value = "/index-servlet")
public class index extends HttpServlet {
    private String message;
    List<Immeuble> immeubles;
    List<Unitelocation> uniteLocbyId;
    private Immeuble currentImmeuble;
 private ImmeubleDao immeubleDao;
    public void init() {
        message = "error action null";
        this.immeubleDao = new ImmeubleDao();
         immeubles = immeubleDao.AllImmeubles();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                uniteLocbyId = immeubleDao.getUnitLocationsByImmeubleId(id);
                currentImmeuble = immeubleDao.find(id);
                request.setAttribute("uniteLocbyId", uniteLocbyId);
                request.setAttribute("currentImmeuble", currentImmeuble);
                request.getRequestDispatcher("/WEB-INF/jsp/Immeubles/detailsImmeuble.jsp").forward(request, response);
                for (Unitelocation loc : uniteLocbyId) {
                    System.out.println(loc);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid id parameter");
            }
        }

        if (action == null) {
            // Hello
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("<a href='?action=login'>Login</a>");
            out.println("<a href='?action=register'>Register</a>");
            out.println("</body></html>");
        } else if (action.equals("login")) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        } else if (action.equals("register")) {
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
        }else if (action.equals("listeImmeubles")) {
        // affiche dans la console les immeuble
        request.setAttribute("immeubles", immeubles); 
        request.getRequestDispatcher("/WEB-INF/jsp/Immeubles/listeImmeuble.jsp").forward(request, response);
        }else if (action.equals("detailsImmeuble")) {
            request.setAttribute("idParam", idParam);
            request.getRequestDispatcher("/WEB-INF/jsp/Immeubles/detailsImmeuble.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}