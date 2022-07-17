<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Market Front App </title>
</head>
<body>
<h1>All goods: </h1>
<c:forEach items="${productList}" var="item">
    Product id=${item.id} name=${item.name} price=${item.price}
    <hr>
</c:forEach>
</body>
</html>