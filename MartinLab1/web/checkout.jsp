<%-- 
    Document   : checkout
    Created on : Nov 12, 2023, 9:16:39 p.m.
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
    </head>
    <body>
        <h1>Checkout Page</h1>
        <form action="HandleCheckout">
        <table>
            <fmt:setLocale value = "en_US"/>
            <tr><th>Part ID</th><th>Description</th><th>Price</th><th>Quantity</th><th>Cost</th></tr>
            <c:set var = "subtotal" value = "0"></c:set>
            <c:forEach var="o" items="${newOrders}">
                <tr>
                <td>${o.monitor.partNum}</td>
                <td>${o.monitor.description}</td>
                <td><fmt:formatNumber value = "${o.monitor.price}" type = "currency"/></td>
                <td>${o.quantity}</td>
                <td><fmt:formatNumber value = "${o.monitor.price * o.quantity}" type = "currency"/></td>
                </tr>
                <c:set var = "subtotal" value = "${subtotal + (o.quantity * o.monitor.price)}"></c:set>
            </c:forEach>
                
                <!-- subtotal -->
                <tr>
                <td colspan="4" style="text-align: right; font-weight: bold;">Subtotal:</td>
                <td colspan="1" ><fmt:formatNumber value = "${subtotal}" type = "currency"/></td>
                </tr>
                
                <!-- HST-->
                <tr>
                <td colspan="4" style="text-align: right; font-weight: bold;">HST:</td>
                <td colspan="1" ><fmt:formatNumber value = "${subtotal * 0.15}" type = "currency"/></td>
                </tr>    
                
                <!-- total -->
                <tr>
                <td colspan="4" style="text-align: right; font-weight: bold;">Total:</td>
                <td colspan="1"><fmt:formatNumber value = "${subtotal * 1.15}" type = "currency"/></td>
                </tr>   
                
                <input type="hidden" name="total" value="${subtotal * 1.15}">
                
        </table>
        <button name="back">Go Back</button>
        <button name="purchase" value="something">Purchase</button>
        </form>
    </body>
</html>
