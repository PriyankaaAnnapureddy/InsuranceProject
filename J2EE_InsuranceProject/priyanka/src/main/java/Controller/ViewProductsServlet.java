package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

@WebServlet("/view-products")
public class ViewProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int PAGE_SIZE = 10;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("product_name");
        int currentPage = 1;

        try {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // Default to page 1 if no valid page number is provided
        }

        List<Product> products = new ArrayList<>();
        int totalProducts = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            String countSql;
            if (searchQuery == null || searchQuery.trim().isEmpty()) {
                countSql = "SELECT COUNT(*) FROM product";
            } else {
                countSql = "SELECT COUNT(*) FROM product WHERE productname LIKE ? OR username LIKE ?";
            }
            stmt = conn.prepareStatement(countSql);
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
            }
            rs = stmt.executeQuery();
            if (rs.next()) {
                totalProducts = rs.getInt(1);
            }

            String sql;
            if (searchQuery == null || searchQuery.trim().isEmpty()) {
                sql = "SELECT * FROM product LIMIT ? OFFSET ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, PAGE_SIZE);
                stmt.setInt(2, (currentPage - 1) * PAGE_SIZE);
            } else {
                sql = "SELECT * FROM product WHERE productname LIKE ? OR username LIKE ? LIMIT ? OFFSET ?";
                stmt = conn.prepareStatement(sql);
                String searchPattern = "%" + searchQuery + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
                stmt.setInt(3, PAGE_SIZE);
                stmt.setInt(4, (currentPage - 1) * PAGE_SIZE);
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productid"));
                product.setUsername(rs.getString("username"));
                product.setProductName(rs.getString("productname"));
                product.setSno(rs.getString("sno"));
                product.setPdate(rs.getDate("pdate"));

                String claimSql = "SELECT COUNT(*) FROM claims WHERE productid = ?";
                try (PreparedStatement claimStmt = conn.prepareStatement(claimSql)) {
                    claimStmt.setInt(1, product.getProductId());
                    ResultSet claimRs = claimStmt.executeQuery();
                    if (claimRs.next()) {
                        int claimCount = claimRs.getInt(1);
                        product.setClaimCount(claimCount);
                    }
                }

                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while fetching products.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
        request.setAttribute("products", products);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);
        request.getRequestDispatcher("view-products.jsp").forward(request, response);
    }
}
