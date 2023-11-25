<%-- 
    Document   : showOrders
    Created on : Nov 12, 2023, 2:36:12 p.m.
    Author     : jonah
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Martin A1 - Shopping Cart</title>
        <link rel="stylesheet" href="main.css">
    <body>
        <h1>Add Items to Cart</h1>
        <% if (request.getAttribute("zeroInput") != null) {
        %>
        <p style='color: red;'>User must add an item to the cart!</p>
        <%
            } %>
        <form action="ProcessOrder" method="GET">
        <button type="submit">Process Order</button>
        
        
        <table>
        <tr><th>Part Number</th><th>Description</th><th>Resolution</th><th>Size</th><th>Refresh Rate</th><th>IPS</th><th>Price</th><th>Quantity</th></tr>
        <c:set var = "index" value = "0"></c:set>
            <c:forEach var="o" items="${orders}">
                <tr>
                    <td>${o.monitor.partNum}</td>
                    <td>${o.monitor.description}</td>
                    <td>${o.monitor.resolution}</td>
                    <td>${o.monitor.size}</td>
                    <td>${o.monitor.refreshRate}</td>
                    <td>${o.monitor.ips}</td>
                    <!--  format currency -->
                    <fmt:setLocale value = "en_US"/>
                    <td><fmt:formatNumber value = "${o.monitor.price}" type = "currency"/></td>
                    
                    <td><input type="number" min ="0" name="quantity${index}" value="${o.quantity}"></td>
                </tr>
                <c:set var = "index"  value= "${index + 1}"></c:set>
            </c:forEach>
            
        </table>
        </form>
        
    </body>
</html>
