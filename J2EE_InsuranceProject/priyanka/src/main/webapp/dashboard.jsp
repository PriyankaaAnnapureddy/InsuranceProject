<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
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
                    <li><a href="index.jsp">Contact</a></li>
                    <li><a href="register-user.jsp">Register</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <div class="container">
        <div class="header">
            <p><b>Logged User: ${sessionScope.username}</b></p>
        </div>

        <h2>Dashboard</h2>
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>
        <c:if test="${param.success eq 'true'}">
            <p class="success-message">Operation completed successfully!</p>
        </c:if>
        <a href="ShowRegisterProductForm" class="button">Register a Product</a>
        <h3>Your Registered Products</h3>
        <table>
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Serial No</th>
                    <th>Purchase Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="device" items="${devices}">
                    <tr>
                        <td>${device.productName}</td>
                        <td>${device.sno}</td>
                        <td>
                            <fmt:formatDate value="${device.pdate}" pattern="yyyy-MM-dd" />
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${device.claimCount < 3}">
                                    <c:forEach var="i" begin="1" end="3">
                                        <c:if test="${i > device.claimCount}">
                                            <a href="SubmitClaimServlet?productId=${device.productId}" class="button">Add Claim ${i}</a>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <!-- Always show View Claim Status if at least one claim is submitted -->
                                    <a href="ViewClaimsServlet?productId=${device.productId}" class="button">Check Claim Status</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
