<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Claims</title>
    <link rel="stylesheet" type="text/css" href="css/view-claims.css">
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
                    <li><a href="login.jsp">User Login</a></li>
                    <li><a href="register-user.jsp">User Register</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="main-content">
        <h1>View Claims</h1>
        <div class="container">
            <!-- Search Form -->
            <form action="view-claims" method="get">
                <input type="text" name="device_name" placeholder="Search by device name" value="${searchQuery}">
                <input type="submit" value="Search">
            </form>

            <!-- Claims Table -->
            <c:if test="${not empty devices}">
                <c:forEach var="device" items="${devices}">
                    <div class="device">
                        <h2>${device.productName} (${device.sno})</h2>
                        <c:choose>
                            <c:when test="${not empty device.claims}">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Date of Claim</th>
                                            <th>Description</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="claim" items="${device.claims}">
                                            <tr>
                                                <td>${claim.dateOfClaim}</td>
                                                <td>${claim.description}</td>
                                                <td>${claim.status}</td>
                                                <td><a href="manage-claims?claimId=${claim.claimId}">Manage</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p>No claims found for this device.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty devices}">
                <p>No devices found.</p>
            </c:if>

            <!-- Pagination Controls -->
            <c:if test="${totalPages > 1}">
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="view-claim?page=${currentPage - 1}&device_name=${searchQuery}">&laquo; Previous</a>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="page">
                        <a href="view-claim?page=${page}&device_name=${searchQuery}" class="${page == currentPage ? 'active' : ''}">
                            ${page}
                        </a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="view-claim?page=${currentPage + 1}&device_name=${searchQuery}">Next &raquo;</a>
                    </c:if>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
