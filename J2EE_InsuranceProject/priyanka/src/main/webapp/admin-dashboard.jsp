<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/admin-dashboard.css">
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
        <h1>Admin Dashboard</h1>
        <div class="container">
            <div class="link-container">
                <a href="view-users">View Users</a>
                <div class="link-content">
                    <p>Manage and view all registered users. You can search, filter, and update user details.</p>
                </div>
            </div>
            <div class="link-container">
                <a href="view-products">View Products</a>
                <div class="link-content">
                    <p>View all products available in the system. Manage product details, including prices and descriptions.</p>
                </div>
            </div>
            <div class="link-container">
                <a href="manage-claims">Manage Claims</a>
                <div class="link-content">
                    <p>Oversee and process insurance claims. Review  and manage claim statuses and take necessary actions.</p>
                </div>
            </div>
            <div class="link-container">
                <a href="add-products.jsp">Add Products</a>
                <div class="link-content">
                    <p>Update product information such as product name, serial number </p>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
