<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pr.modelo.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identificacion</title>
</head>
<body>

<%
 BeanError error = (BeanError) request.getAttribute("error");
 if (error!=null)
 {
	 out.println("<br><b>"+error.getMensError()+"</b>"); 
 }
%>
<form action="controlador" method="post">
Login: <input type="text" name="login" value="" required>
<br/>
Password: <input type="password" name="password" required>
<br/>
<%
String msg = (String)session.getAttribute("Msg");
%> 
<img src='captcha/<%=msg%>'></img>	
<br/>
captcha: <input type="text" name="capt" value="" required>
<br/>
<input type="hidden" name="accion" value="menu">
<input name="login" value="login" type="submit" style="width: 84px; height: 33px">
</form>

</body>
</html>