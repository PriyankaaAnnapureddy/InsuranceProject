<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Users</title>
    <link rel="stylesheet" type="text/css" href="css/view-users.css">
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
    
    <div class="main-content">
        <h1>View Users</h1>
        <div class="container">
            <!-- Search Form -->
            <form action="view-users" method="get">
                <input type="text" name="username" placeholder="Search by username" value="${param.username}">
                <input type="submit" value="Search">
            </form>
            
            <!-- Users Table -->
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Cellphone No</th>
                        <th>Email</th>
                        <th>Name</th>
                        <th>Address</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>${user.cellphoneNo}</td>
                            <td>${user.email}</td>
                            <td>${user.name}</td>
                            <td>${user.address}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <!-- Pagination Controls -->
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="view-users?page=${currentPage - 1}&username=${param.username}">&laquo; Previous</a>
                </c:if>
                
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="view-users?page=${i}&username=${param.username}" class="${currentPage == i ? 'active' : ''}">${i}</a>
                </c:forEach>
                
                <c:if test="${currentPage < totalPages}">
                    <a href="view-users?page=${currentPage + 1}&username=${param.username}">Next &raquo;</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
