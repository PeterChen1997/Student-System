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
  <h1>本学期可供选择的课程</h1>
  <p>学校教育应该适应学生的个别差异，赋予每个学生选择性发展的权利，引导和促进学生个性的生动发展。</p>
</div>
<div class="jumbotron " status="choosen">
  <h1>本学期已经选择的课程</h1>
  <p>路漫漫其修远兮，吾将上下而求索</p>
</div>
<div class="jumbotron " status="notice">
  <h1>重要通知</h1>
  <p>选上一门课是一种缘分，没选上又何尝不是呢。</p>
</div>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-2  dataType">
      <ul class="nav nav-pills nav-stacked">
        <li class=" " role="presentation"><a href="#" status="notice">通知</a></li>
        <li role="presentation" class=""><a href="#" status="choosen">已选课程</a></li>
        <li role="presentation" class="active"><a href="#" status="choose">所有课程</a></li>
      </ul>
    </div>



    <div class="col-md-9 col-md-offset-1 ">
    
    <!-- 通知栏 -->
      <div class="table-responsive" status="notice">
	     <h2>通知栏：</h2>
	     <c:forEach items="${notice}" var = "c">
			<p>同学你好，你选修的由${c.tname }老师教授的${c.cname }课程由于开班人数不够，暂不予以开班授课，请重新选课。</p>
          </c:forEach>

      </div>
      
      
      <!-- 未选课程栏 -->
      <div class="table-responsive" status="choose">
      
	      <!-- 搜索框 -->
	      
	      <div class="row">
			  
			  <div class="col-lg-4 col-lg-offset-8">
			  <form action="foresearch" method="post">
			    <div class="input-group">
			      <input type="text"  class="form-control" placeholder="请输入要查询的内容 " name="keyword" value="${param.keyword}">
			      <span class="input-group-btn">
			        <button class="btn btn-default" type="submit">搜索</button>
			      </span>
			    </div><!-- /input-group -->
			    </form>
			  </div><!-- /.col-lg-6 -->
			  
			</div><!-- /.row -->
	      	
	      
	      
        <br>
        
        <table class="table table-striped table-bordered table-hover table-condensed " id="table">
          <thead>
          <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>授课老师</th>
            <th>学分</th>
            <th>简介</th>
            <th>已选人数</th>
            <th>选修</th>
          </tr>
       </thead>
       <tbody>
       
       	<c:forEach items="${cs}" var = "c">
       	
          <tr>
          	<td>${c.cno }</td>
          	<td>${c.cname }</td>
          	<td>${c.tname }</td>
          	<td>${c.credit }</td>
          	<td>${c.description }</td>
          	<td>${c.choosen }/${c.number }</td>
          	<td><a chooseLink="true" href="admin_course_choose?cid=${c.cno }"><span class="glyphicon glyphicon-star"></</span>选修</a></td>
          </tr>
          </c:forEach>
          </tbody>
        </table>

      </div>
      
      
      <!-- 已选课程栏 -->
      <div class="table-responsive" status="choosen">
	  
	      
        <table class="table table-striped table-bordered table-hover table-condensed " id="table">
          <thead>
          <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>授课老师</th>
            <th>学分</th>
            <th>简介</th>
            <th>退选</th>
          </tr>
       </thead>
       <tbody>
       
       	<c:forEach items="${choosen}" var = "a">
       	
          <tr>
          	<td>${a.cno }</td>
          	<td>${a.cname }</td>
          	<td>${a.tname }</td>
          	<td>${a.credit }</td>
          	<td>${a.description }</td>
          	<td><a deleteLink="true" href="admin_course_delete?cid=${a.cno }"><span class="glyphicon glyphicon-trash"></</span>退选</a></td>
          </tr>
          </c:forEach>
          </tbody>
        </table>

      </div>
    </div>


