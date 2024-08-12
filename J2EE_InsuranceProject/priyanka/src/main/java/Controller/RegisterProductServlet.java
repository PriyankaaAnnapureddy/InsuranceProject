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
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/RegisterProductServlet")
public class RegisterProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String productName = request.getParameter("product_name");
        String serialNo = request.getParameter("serial_no");
        String purchaseDateStr = request.getParameter("purchase_date");

        try {
            // Convert the purchase date to the correct format
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date purchaseDate = inputDateFormat.parse(purchaseDateStr);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedPurchaseDate = outputDateFormat.format(purchaseDate);

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Priya@504");

            // Check if the username exists
            String checkUserSql = "SELECT COUNT(*) FROM user WHERE username = ?";
            PreparedStatement checkUserStmt = conn.prepareStatement(checkUserSql);
            checkUserStmt.setString(1, username);
            ResultSet rs = checkUserStmt.executeQuery();
            rs.next();
            int userCount = rs.getInt(1);

            if (userCount == 0) {
                // User does not exist
            	System.out.println("");
                request.setAttribute("errorMessage", "Username does not exist.In order to register a product, user must have a valid account");
                request.getRequestDispatcher("register-product.jsp").forward(request, response);
                return;
            }

            // Insert product data into the database
            String sql = "INSERT INTO product (username, productname, sno, pdate) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, productName);
            stmt.setString(3, serialNo);
            stmt.setString(4, formattedPurchaseDate);
            stmt.executeUpdate();

            conn.close();
            response.sendRedirect("DashboardServlet");

//            response.sendRedirect("register-product.jsp");
        } catch (SQLException e) {
            if (e instanceof com.mysql.cj.jdbc.exceptions.MysqlDataTruncation) {
                request.setAttribute("errorMessage", "Data truncation error: " + e.getMessage());
            } else {
                request.setAttribute("errorMessage", "SQL Error: " + e.getMessage());
            }
            request.getRequestDispatcher("register-product.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("register-product.jsp").forward(request, response);
        }
    }
}
