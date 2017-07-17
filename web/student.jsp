<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="include/header.jsp"%>
<%@include file="include/top.jsp"%>
<!-- 所有学生信息栏 -->
<div class="container">
	<div class="table-responsive" status="choose">
      
	      
        
        <table class="table table-striped table-bordered table-hover table-condensed " id="table">
          <thead>
          <tr>
            <th>学号</th>
            <th>学生姓名</th>
          </tr>
       </thead>
       <tbody>
       
       	<c:forEach items="${student}" var = "c">
       	
          <tr>
          	<td>${c.cno }</td>
          	<td>${c.cname }</td>
          	</tr>
          </tr>
          </c:forEach>
          </tbody>
        </table>

      </div>
</div>
      

<%@include file="include/footer.jsp"%>    