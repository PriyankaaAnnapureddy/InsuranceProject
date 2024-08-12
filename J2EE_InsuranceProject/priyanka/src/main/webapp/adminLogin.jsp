<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
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
                    <li><a href="login.jsp">User Login</a></li>
                    <li><a href="register-user.jsp">User Register</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main>
        <h2>Admin Login</h2>

<form action="AdminServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <input type="submit" value="Login">
            
            <c:if test="${not empty errorMessage}">
                <p class="error-message">${errorMessage}</p>
            </c:if>
        </form>
    </main>

   <!-- 
   <footer>
        <p>&copy; 2024 GP Insurance Company. All rights reserved.</p>
        <p>Contact us: <a href="mailto:info@gpinsurance.com">info@gpinsurance.com</a></p>
    </footer> 
   -->
</body>
</html>
