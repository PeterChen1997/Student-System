<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%><html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Export to Excel</title>
</head>
<body>
    <%
    	System.out.println("执行输出excel 1");
        	System.out.println("执行输出excel 2");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "inline; filename="
                    + "excel.xls");
 
        
    %>
    <table align="left" border="1">
          <thead>
          <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>课程描述</th>
            <th>学分</th>
            <th>授课老师号</th>
            <th>授课老师</th>
            <th>已选人数</th>
          </tr>
       </thead>
       <tbody>
       
       	<c:forEach items="${dcs}" var = "c">
       	
          <tr>
          	<td>${c.cno }</td>
          	<td>${c.cname }</td>
          	<td>${c.description }</td>
          	<td>${c.credit }</td>
          	<td>${c.tno }</td>
          	<td>${c.tname}</td>
          	<td>${c.choosen }/${c.number }</td>
          </tr>
          </c:forEach>
          </tbody>
        </table>
    
    
    
    
    
    
    
    
    
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            
    
</body>
</html>