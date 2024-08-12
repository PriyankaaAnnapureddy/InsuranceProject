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

@WebServlet("/SubmitClaimServlet")
public class SubmitClaimServlet extends HttpServlet {
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
                    devices.add(device);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while fetching your products. Please try again.");
        }

        request.setAttribute("devices", devices);
        request.getRequestDispatcher("submit-claim.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deviceIdStr = request.getParameter("productid");
        String dateOfClaimStr = request.getParameter("date_of_claim");
        String description = request.getParameter("description");
        String username = (String) request.getSession().getAttribute("username");

        if (deviceIdStr == null || deviceIdStr.trim().isEmpty() ||
            dateOfClaimStr == null || dateOfClaimStr.trim().isEmpty() ||
            description == null || description.trim().isEmpty()) {

            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("submit-claim.jsp").forward(request, response);
            return;
        }

        try {
            int deviceId = Integer.parseInt(deviceIdStr);
            java.sql.Date dateOfClaim = java.sql.Date.valueOf(dateOfClaimStr);

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO claims (productid, date_of_claim, description, username) VALUES (?, ?, ?, ?)")) {

                stmt.setInt(1, deviceId);
                stmt.setDate(2, dateOfClaim);
                stmt.setString(3, description);
                stmt.setString(4, username);

                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    response.sendRedirect("DashboardServlet?success=true");
                } else {
                    request.setAttribute("errorMessage", "An error occurred while submitting your claim. Please try again.");
                    request.getRequestDispatcher("submit-claim.jsp").forward(request, response);
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid device ID format.");
            request.getRequestDispatcher("submit-claim.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Invalid date format.");
            request.getRequestDispatcher("submit-claim.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while submitting your claim. Please try again.");
            request.getRequestDispatcher("submit-claim.jsp").forward(request, response);
        }
    }
}
