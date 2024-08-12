<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="css/update-product.css">
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

    <main>
        <h1>Update Product</h1>

        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <form action="UpdateProductServlet" method="post">
            <input type="hidden" name="productId" value="${product.productId}">

            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" value="${product.productName}" required>
            </div>

            <div class="form-group">
                <label for="serialNumber">Serial Number:</label>
                <input type="text" id="serialNumber" name="serialNumber" value="${product.sno}" required>
            </div>

            <div class="form-group">
                <label for="purchaseDate">Purchase Date:</label>
                <input type="date" id="purchaseDate" name="purchaseDate" value="${product.pdate}" required>
            </div>

           <!--   <div class="form-group">
                <label for="warrantyStatus">Warranty Status:</label>
                <select id="warrantyStatus" name="warrantyStatus" required>
                    <option value="Valid" ${product.warrantyStatus == 'Valid' ? 'selected' : ''}>Valid</option>
                    <option value="Expired" ${product.warrantyStatus == 'Expired' ? 'selected' : ''}>Expired</option>
                </select>
            </div>
-->

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Update Product</button>
                <a href="view-products.jsp" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </main>
</body>
</html>
