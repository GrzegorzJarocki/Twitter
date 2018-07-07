<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <%@ include file="jspf/head_config.jspf" %>

    <title>Warsztat 6b</title>
</head>
<body>
<%@ include file="jspf/header.jspf" %>
<%@ include file="jspf/main_menu.jspf" %>
<p class='error'>${msg}</p>
<form:form method="post" modelAttribute="loginData">
    <p>Email <form:input path="email" /></p>
    <p>Password <form:password path="password" /></p>
    <p><input type="submit"/></p>
    <form:errors></form:errors>
</form:form>
<%@ include file="jspf/footer.jspf" %>
</body>
</html>
