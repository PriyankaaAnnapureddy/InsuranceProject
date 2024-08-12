package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Hardcoded admin credentials
    private static final String[][] ADMIN_CREDENTIALS = {
        {"Priyanka", "priyanka"},
        {"Gurpreet", "gurpreet"}
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticateAdmin(username, password)) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect("admin-dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid credentials. Please try again.");
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        }
    }

    private boolean authenticateAdmin(String username, String password) {
        for (String[] credentials : ADMIN_CREDENTIALS) {
            if (credentials[0].equals(username) && credentials[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
}
