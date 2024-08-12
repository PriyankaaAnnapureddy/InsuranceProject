<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    session = request.getSession();
    String loggedInUsername = (String) session.getAttribute("username");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Register Product</title>
    <link rel="stylesheet" type="text/css" href="css/reg-prod.css">
    <script type="text/javascript">
        // Convert the Java map to a JavaScript object
        var productSerialMap = {
            <c:forEach var="entry" items="${productSerialMap}">
                "${entry.key}": "${entry.value}"<c:if test="${!entryStatus.last}">,</c:if>
            </c:forEach>
        };

        function updateSerialNumber() {
            var selectedProduct = document.getElementById("product").value;
            var serialNoField = document.getElementById("serial_no");

            // Update the serial number field based on the selected product
            serialNoField.value = productSerialMap[selectedProduct] || "";
        }
    </script>
</head>
<body>
    <header>
        <div class="header-container">
            <div class="logo">
                <a href="index.jsp">
                    <!-- <img src="images/logo.png" alt="GP Insurance"> -->
                    <h1>GP Insurance</h1>
                </a>
            </div>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="register-user.jsp">User Register</a></li>
                    <li><a href="admin-dashboard.jsp">Admin Dashboard</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <h1>Register New Product</h1>

    <form action="RegisterProductServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= loggedInUsername %>" readonly required><br>

        <label for="product">Product:</label>
        <select id="product" name="product_name" onchange="updateSerialNumber()" required>
            <c:forEach var="entry" items="${productSerialMap}">
                <option value="${entry.key}">${entry.key}</option>
            </c:forEach>
        </select><br>

        <label for="serial_no">Serial No:</label>
        <input type="text" id="serial_no" name="serial_no" readonly required><br>

        <label for="purchase_date">Purchase Date:</label>
        <input type="date" id="purchase_date" name="purchase_date" required><br>

        <input type="submit" value="Register Product">
    </form>

    <c:if test="${not empty errorMessage}">
        <p class="error-message">${errorMessage}</p>
    </c:if>
</body>
</html>
