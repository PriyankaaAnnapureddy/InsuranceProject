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
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        List<Product> devices = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product WHERE username = ?")) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product device = new Product();
                    device.setProductId(rs.getInt("productid"));
                    device.setProductName(rs.getString("productname"));
                    device.setSno(rs.getString("sno"));
                    device.setPdate(rs.getDate("pdate"));

                    // Check if the device has any claims
                    try (PreparedStatement claimStmt = conn.prepareStatement("SELECT COUNT(*) FROM claims WHERE productid = ?")) {
                        claimStmt.setInt(1, device.getProductId());
                        try (ResultSet claimRs = claimStmt.executeQuery()) {
                            if (claimRs.next()) {
                                device.setClaimCount(claimRs.getInt(1)); // Fixed the method to set claim count
                            }
                        }
                    }

                    devices.add(device);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while fetching your products. Please try again.");
        }

        request.setAttribute("devices", devices);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
