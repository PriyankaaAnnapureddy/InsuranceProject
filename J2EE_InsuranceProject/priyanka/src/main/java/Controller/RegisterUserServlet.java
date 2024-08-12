package Controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cellphone = request.getParameter("cellphone");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            // Insert user data into the database
            String sql = "INSERT INTO user (username, password, phone, usermail, fullname, address) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, cellphone);
            stmt.setString(4, email);
            stmt.setString(5, name);
            stmt.setString(6, address);
            int rowsAffected = stmt.executeUpdate();

            // Check if insertion was successful
            if (rowsAffected > 0) {
                request.setAttribute("successMessage", "User registered successfully! You can now <a href='login.jsp'>log in</a>.");
                request.getRequestDispatcher("register-user.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("register-user.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
                // Duplicate entry detected
                request.setAttribute("errorMessage", "Username already exists. Please choose a different username.");
            } else {
                e.printStackTrace();
                request.setAttribute("errorMessage", "An error occurred during registration. Please try again later.");
            }
            request.getRequestDispatcher("register-user.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database driver not found. Please contact the administrator.");
            request.getRequestDispatcher("register-user.jsp").forward(request, response);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
