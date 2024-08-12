<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Products</title>
    <link rel="stylesheet" type="text/css" href="css/view-products.css">
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
        <h1>View Products</h1>
        <div class="container">
            <!-- Search Form -->
            <form action="view-products" method="get">
                <input type="text" name="product_name" placeholder="Search by product or user name" value="${searchQuery}">
                <input type="submit" value="Search">
            </form>

            <!-- Products Table -->
            <table>
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Username</th>
                        <th>Product Name</th>
                        <th>Serial No</th>
                        <th>Purchase Date</th>
                        <th>Claim Count</th>
                       
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.productId}</td>
                            <td>${product.username}</td>
                            <td>${product.productName}</td>
                            <td>${product.sno}</td>
                            <td>${product.pdate}</td>
                            <td>${product.claimCount}</td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination Controls -->
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="view-products?page=${currentPage - 1}&product_name=${searchQuery}">&laquo; Previous</a>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="page">
                    <a href="view-products?page=${page}&product_name=${searchQuery}" class="${page == currentPage ? 'active' : ''}">
                        ${page}
                    </a>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="view-products?page=${currentPage + 1}&product_name=${searchQuery}">Next &raquo;</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
