<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Claims</title>
    <link rel="stylesheet" type="text/css" href="css/view-products.css">
    <style>
        /* Additional styles for status colors */
        .status-rejected {
            color: red;
        }
        .status-pending {
            color: orange;
        }
        .status-approved {
            color: green;
        }
    </style>
</head>
<body>
    <header>
        <div class="header-container">
            <div class="logo">
                <a href="index.jsp">
                    <h1>GP Insurance</h1>
                </a>
            </div>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="register-user.jsp">User Register</a></li>
                    <li><a href="index.jsp">Contact</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="content">
        <h2>Your Claims</h2>

        <table class="claims-table">
            <thead>
                <tr>
                    <th>Claim ID</th>
                    <th>Product ID</th>
                    <th>Status</th>
                    <th>Date of Claim</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="claim" items="${claims}">
                    <tr>
                        <td>${claim.claimId}</td>
                        <td>${claim.productId}</td>
                        <td>
                            <span class="<c:choose>
                                <c:when test="${claim.status == 'Rejected'}">status-rejected</c:when>
                                <c:when test="${claim.status == 'Pending'}">status-pending</c:when>
                                <c:when test="${claim.status == 'Approved'}">status-approved</c:when>
                            </c:choose>">${claim.status}</span>
                        </td>
                        <td>${claim.dateOfClaim}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
