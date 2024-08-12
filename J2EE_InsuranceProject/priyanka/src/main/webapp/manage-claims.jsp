<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Claims</title>
    <link rel="stylesheet" type="text/css" href="css/manage-claims.css">
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

    <main class="main-content">
        <h1>Manage Claims</h1>
        <div class="container">
            <c:if test="${not empty claims}">
                <table>
                    <thead>
                        <tr>
                            <th>Claim ID</th>
                            <th>Product ID</th>
                            <th>Username</th>
                            <th>Status</th>
                            <th>Claim Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="claim" items="${claims}">
                            <tr>
                                <td>${claim.claimId}</td>
                                <td>${claim.productId}</td>
                                <td>${claim.username}</td>
                                <td>${claim.status}</td>
                                <td>${claim.dateOfClaim}</td>
                                <td>
                                    <form action="manage-claims" method="post">
                                        <input type="hidden" name="claimId" value="${claim.claimId}">
                                        <select name="status">
                                            <option value="Pending" ${claim.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                            <option value="Approved" ${claim.status == 'Approved' ? 'selected' : ''}>Approved</option>
                                            <option value="Rejected" ${claim.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                                        </select>
                                        <input type="submit" value="Update">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty claims}">
                <p>No claims found.</p>
            </c:if>
        </div>
    </main>
</body>
</html>
