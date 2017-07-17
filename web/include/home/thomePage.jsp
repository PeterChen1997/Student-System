<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<script>
	$(function(){
		
		$("div[status]").hide();
		$("div[status=choose]").show();
		console.log("运行隐藏和显示表格");
		
		$("a[status]").click(function(){
			var status = $(this).attr("status");
			console.log(status);
			$("div[status]").hide();
			$("div[status="+status+"]").show();
			//由于双引号没加，造成编译错误
			$("div.dataType li").removeClass("active");
			$(this).parent("li").addClass("active");
		});
		
	});
	
	
	
	
</script>

<div class="container">
<div class="jumbotron " status="choose">
  <h1>您的课程选修情况</h1>
  <p>您可以查看你的课程选修情况，并且查看选修学生名单。</p>
</div>

<div class="jumbotron " status="notice">
  <h1>课程更改通知</h1>
  <p>教一门课是一种缘分，不教又何尝不是呢。</p>
</div>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-2  dataType">
      <ul class="nav nav-pills nav-stacked">
        <li><a href="#" status="notice">课程更改通知</a></li>
        <li class="active"><a href="#" status="choose">您的课程选修情况</a></li>
      </ul>
    </div>



    <div class="col-md-9 col-md-offset-1 ">
    
    <!-- 通知栏 -->
      <div class="table-responsive" status="notice">
	     <h2>通知栏：</h2>
	     <c:forEach items="${tnotice}" var = "c">
			<p>老师您好，你的${c.cname }课程由于选修人数不足，咱不予以开班授课。</p>
          </c:forEach>

      </div>
      
      <!-- 所有课程栏 -->
      <div class="table-responsive" status="choose">
      
	      
        
        <table class="table table-striped table-bordered table-hover table-condensed " id="table">
          <thead>
          <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>学分</th>
            <th>简介</th>
            <th>已选人数</th>
            <th>修改课程描述</th>
            <th>选修名单</th>
          </tr>
       </thead>
       <tbody>
       
       	<c:forEach items="${tcs}" var = "c">
       	
          <tr>
          	<td>${c.cno }</td>
          	<td>${c.cname }</td>
          	<td>${c.credit }</td>
          	<td>${c.description }</td>
          	<td>${c.choosen }/${c.number }</td>
          	<td><a editLink="true"  href="admin_course_edit?cid=${c.cno }"><span class="glyphicon glyphicon-edit"></</span>修改描述</a></td>
          	<td><a href="admin_course_student?cid=${c.cno }"><span class="glyphicon glyphicon-list"></</span>学生名单</a></td>
          </tr>
          </tr>
          </c:forEach>
          </tbody>
        </table>

      </div>
      
      </div>
    </div>
</div>
