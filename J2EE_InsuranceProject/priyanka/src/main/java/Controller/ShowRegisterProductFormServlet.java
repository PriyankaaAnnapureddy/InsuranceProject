package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowRegisterProductForm")
public class ShowRegisterProductFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> productSerialMap = new HashMap<>();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            // Fetch product names and their corresponding serial numbers
            String sql = "SELECT productname, sno FROM product"; // Adjust table name if necessary
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productSerialMap.put(rs.getString("productname"), rs.getString("sno"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the product serial map as a request attribute
        request.setAttribute("productSerialMap", productSerialMap);

        // Forward the request to the JSP page
        request.getRequestDispatcher("register-product.jsp").forward(request, response);
    }
}
