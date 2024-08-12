<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Status</title>
    <link rel="stylesheet" type="text/css" href="css/user-db.css">
</head>
<body>
    <header>
        <div class="header-container">
            <div class="logo">
                <a href="index.jsp">
                    <!--  <img src="images/logo.png" alt="GP Insurance Logo" class="logo">-->
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

    <div class="container">
        <h2>Product Status</h2>
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>
        <c:if test="${not empty product}">
            <table>
                <thead>
                    <tr>
                        <th>Field</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Product Name</td>
                        <td>${product.productName}</td>
                    </tr>
                    <tr>
                        <td>Serial Number</td>
                        <td>${product.sno}</td>
                    </tr>
                    <tr>
                        <td>Purchase Date</td>
                        <td>${product.pdate}</td>
                    </tr>
                    <tr>
                        <td>Warranty Status</td>
                        <td>${product.warrantyStatus}</td>
                    </tr>
                </tbody>
            </table>

            <h3>Claim Status</h3>
            <table>
                <thead>
                    <tr>
                        <th>Claim ID</th>
                        <th>Date of Claim</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="claim" items="${claims}">
                        <tr>
                            <td>${claim.claimId}</td>
                            <td>${claim.dateOfClaim}</td>
                            <td>${claim.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <a href="dashboard.jsp" class="button">Back to Dashboard</a>
    </div>
</body>
</html>
