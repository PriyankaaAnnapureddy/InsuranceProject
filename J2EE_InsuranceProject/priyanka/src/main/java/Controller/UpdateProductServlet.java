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

import model.Product;
@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET request to display the current product details
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        System.out.println("Received productId in GET: " + productId);

        if (productId == null || productId.trim().isEmpty()) {
            System.out.println("productId is missing or empty.");
            response.sendRedirect("view-products.jsp");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            String sql = "SELECT * FROM product WHERE productId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setSno(rs.getString("sno"));
                product.setPdate(rs.getDate("pdate"));
                product.setUsername(rs.getString("username"));

                request.setAttribute("product", product);
                request.getRequestDispatcher("update-products.jsp").forward(request, response);
            } else {
                response.sendRedirect("view-products.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving the product.");
            request.getRequestDispatcher("view-products.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Handle POST request to update the product details
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        System.out.println("Received productId in POST: " + productId);

        if (productId == null || productId.trim().isEmpty()) {
            System.out.println("productId is missing or empty in POST.");
            request.setAttribute("errorMessage", "Product ID is missing.");
            request.getRequestDispatcher("update-products.jsp").forward(request, response);
            return;
        }

        String productName = request.getParameter("productName");
        String serialNumber = request.getParameter("serialNumber");
        String purchaseDate = request.getParameter("purchaseDate");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            String sql = "UPDATE product SET productName = ?, sno = ?, pdate = ? WHERE productId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productName);
            stmt.setString(2, serialNumber);
            stmt.setString(3, purchaseDate);
            stmt.setString(4, productId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("view-products.jsp");
            } else {
                request.setAttribute("errorMessage", "Product update failed.");
                request.getRequestDispatcher("update-products.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while updating the product.");
            request.getRequestDispatcher("update-products.jsp").forward(request, response);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
