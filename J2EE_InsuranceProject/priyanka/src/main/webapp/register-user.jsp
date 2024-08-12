<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register User</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
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
                    <li><a href="login.jsp">Login</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main>
        <h2>Register New User</h2>

        <c:if test="${not empty successMessage}">
            <p class="message success-message">${successMessage}</p>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <p class="message error-message">${errorMessage}</p>
        </c:if>

        <form action="RegisterUserServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            
            <label for="cellphone">Cellphone No:</label>
            <input type="text" id="cellphone" name="cellphone"><br>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>
            
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required><br>
            
            <label for="address">Address:</label>
            <textarea id="address" name="address" required></textarea><br>
            
            <input type="submit" value="Register">
        </form>
    </main>

   <!--  <footer>
        <p>&copy; 2024 GP Insurance Company. All rights reserved.</p>
        <p>Contact us: <a href="mailto:info@gpinsurance.com">info@gpinsurance.com</a></p>
    </footer> -->
</body>
</html>
