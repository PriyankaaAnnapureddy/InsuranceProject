package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Claim;
import model.Product;

@WebServlet("/ViewClaimsServlet")
public class ViewClaimsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Priya@504";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session
        if (session == null || session.getAttribute("username") == null) {
            // No session or no user is logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        String productIdStr = request.getParameter("productId");
        int productId = productIdStr != null ? Integer.parseInt(productIdStr) : 0;
        
        
        //Product device = new Product();
        //int productId = device.getProductId();


        List<Claim> claims = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String sql = "SELECT * FROM claims WHERE username = ? AND productid = ? ORDER BY date_of_claim DESC";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    stmt.setInt(2, productId);
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            Claim claim = new Claim();
                            claim.setClaimId(rs.getInt("claim_id"));
                            claim.setProductId(rs.getInt("productid"));
                            claim.setStatus(rs.getString("status"));
                            claim.setDateOfClaim(rs.getString("date_of_claim"));
                            claims.add(claim);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error retrieving claims", e);
        }

        request.setAttribute("claims", claims);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view-user-claims.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request if necessary, but likely not needed for just viewing claims
    }
}
