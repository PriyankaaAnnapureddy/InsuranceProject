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

import model.Claim;

@WebServlet("/manage-claims")
public class ManageClaims extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Claim> claims = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            String sql = "SELECT * FROM claims ORDER BY date_of_claim DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setProductId(rs.getInt("productid"));
                claim.setUsername(rs.getString("username"));
                claim.setStatus(rs.getString("status"));
                claim.setDateOfClaim(rs.getString("date_of_claim"));
                claims.add(claim);

                // Debug statement
                System.out.println("Claim ID: " + claim.getClaimId() + ", Username: " + claim.getUsername());
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while fetching claims.");
        }

        request.setAttribute("claims", claims);
        request.getRequestDispatcher("manage-claims.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        int claimId = Integer.parseInt(request.getParameter("claimId"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            String sql = "UPDATE claims SET status = ? WHERE claim_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, claimId);
            stmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while updating the claim status.");
        }

        response.sendRedirect("manage-claims");
    }
}
