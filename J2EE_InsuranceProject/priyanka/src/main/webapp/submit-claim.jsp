<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submit a Claim</title>
    <link rel="stylesheet" type="text/css" href="css/submit.css">
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
                    <li><a href="indec.jsp">Contact</a></li>
                    <li><a href="view-claim.jsp">View Claims</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="main-content">
        <h1>Submit a Claim</h1>
        <form action="SubmitClaimServlet" method="post">
            <label for="device_id">Select Device:</label>
            <select id="device_id" name="productid" required>
                <c:forEach var="device" items="${devices}">
                    <option value="${device.productId}">${device.productName} (${device.sno})</option>
                </c:forEach>
            </select><br>
            <label for="date_of_claim">Date of Claim:</label>
            <input type="date" id="date_of_claim" name="date_of_claim" required><br>
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea><br>
            <input type="submit" value="Submit Claim">
        </form>
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>
        <c:if test="${param.success eq 'true'}">
            <p class="success-message">Claim submitted successfully!</p>
        </c:if>
    </div>
</body>
</html>
