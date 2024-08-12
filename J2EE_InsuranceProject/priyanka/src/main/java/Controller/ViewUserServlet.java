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

import model.User;

@WebServlet("/view-users")
public class ViewUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int RECORDS_PER_PAGE = 10;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        String searchQuery = request.getParameter("username");
        int currentPage = 1;
        int totalRecords = 0;
        int totalPages = 0;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            String countQuery = "SELECT COUNT(*) FROM user";
            PreparedStatement countStmt = conn.prepareStatement(countQuery);
            ResultSet countRs = countStmt.executeQuery();
            if (countRs.next()) {
                totalRecords = countRs.getInt(1);
            }
            totalPages = (int) Math.ceil(totalRecords * 1.0 / RECORDS_PER_PAGE);

            String sql = (searchQuery != null && !searchQuery.isEmpty()) ?
                "SELECT * FROM user WHERE username LIKE ? LIMIT ?, ?" :
                "SELECT * FROM user LIMIT ?, ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int start = (currentPage - 1) * RECORDS_PER_PAGE;

            if (searchQuery != null && !searchQuery.isEmpty()) {
                stmt.setString(1, "%" + searchQuery + "%");
                stmt.setInt(2, start);
                stmt.setInt(3, RECORDS_PER_PAGE);
            } else {
                stmt.setInt(1, start);
                stmt.setInt(2, RECORDS_PER_PAGE);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword("******"); // Hide the password
                user.setCellphoneNo(rs.getString("phone"));
                user.setEmail(rs.getString("usermail"));
                user.setName(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                users.add(user);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while fetching users.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("users", users);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("view-users.jsp").forward(request, response);
    }
}
