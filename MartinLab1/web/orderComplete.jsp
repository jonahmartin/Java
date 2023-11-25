<%-- 
    Document   : orderComplete
    Created on : Nov 13, 2023, 10:32:32 p.m.
    Author     : jonah
--%>
<!-- for formatting currency-->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
        <h1>Your order is being Processed</h1>
         <fmt:setLocale value = "en_US"/>
        <div style="background: lightgray; border-radius: 10px; padding: 5px; width: fit-content;">
            Total = <fmt:formatNumber value = "${total}" type = "currency"/>
        </div>
        
    </body>
</html>
