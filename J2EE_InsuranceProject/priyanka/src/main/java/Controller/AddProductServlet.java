package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddProductServlet")

public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String serialNumber = request.getParameter("serialNumber");
        String purchaseDate = request.getParameter("purchaseDate");
        String username = (String) request.getSession().getAttribute("username");

        // Database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "yourpassword")) {
            // Check if username exists in the user table
            String userCheckQuery = "SELECT COUNT(*) FROM user WHERE username = ?";
            try (PreparedStatement userCheckStmt = connection.prepareStatement(userCheckQuery)) {
                userCheckStmt.setString(1, username);
                ResultSet rs = userCheckStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    request.setAttribute("errorMessage", "Username does not exist.");
                    request.getRequestDispatcher("add-products.jsp").forward(request, response);
                    return;
                }
            }

            // Insert or update product
            String query;
            if (productId != null && !productId.isEmpty()) {
                // Update existing product
                query = "UPDATE product SET productName = ?, serialNumber = ?, purchaseDate = ?, username = ? WHERE productId = ?";
            } else {
                // Insert new product
                query = "INSERT INTO product (productName, serialNumber, purchaseDate, username) VALUES (?, ?, ?, ?)";
            }

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, productName);
                stmt.setString(2, serialNumber);
                stmt.setString(3, purchaseDate);
                stmt.setString(4, username);

                if (productId != null && !productId.isEmpty()) {
                    stmt.setString(5, productId);
                }

                stmt.executeUpdate();
                response.sendRedirect("view-products.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("add-products.jsp").forward(request, response);
        }
    }
}
