package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductStatusServlet")
public class ProductStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");
        int productId;

        if (productIdStr == null || productIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "Product ID is required.");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            return;
        }

        try {
            productId = Integer.parseInt(productIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Product ID.");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504")) {
            // Retrieve product details
            try (PreparedStatement stmtProduct = conn.prepareStatement("SELECT * FROM product WHERE productid=?")) {
                stmtProduct.setInt(1, productId);
                try (ResultSet rsProduct = stmtProduct.executeQuery()) {
                    if (rsProduct.next()) {
                        request.setAttribute("product", rsProduct);
                    } else {
                        request.setAttribute("errorMessage", "Product not found.");
                        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                        return;
                    }
                }
            }

            // Retrieve claim details
            try (PreparedStatement stmtClaims = conn.prepareStatement("SELECT * FROM claims WHERE productid=?")) {
                stmtClaims.setInt(1, productId);
                try (ResultSet rsClaims = stmtClaims.executeQuery()) {
                    request.setAttribute("claims", rsClaims);
                }
            }

            request.getRequestDispatcher("product-status.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving the product status.");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }
}
